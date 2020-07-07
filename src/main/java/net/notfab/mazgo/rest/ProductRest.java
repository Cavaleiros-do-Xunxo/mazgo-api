package net.notfab.mazgo.rest;

import lombok.extern.slf4j.Slf4j;
import net.notfab.mazgo.entities.History;
import net.notfab.mazgo.entities.HistoryAction;
import net.notfab.mazgo.entities.Product;
import net.notfab.mazgo.entities.dto.ProductBody;
import net.notfab.mazgo.entities.dto.ProductUpdate;
import net.notfab.mazgo.internal.paging.PagedResponse;
import net.notfab.mazgo.internal.paging.Paginator;
import net.notfab.mazgo.repositories.HistoryRepository;
import net.notfab.mazgo.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductRest {

    private final ProductRepository repository;
    private final HistoryRepository historyRepository;

    public ProductRest(ProductRepository repository, HistoryRepository historyRepository) {
        this.repository = repository;
        this.historyRepository = historyRepository;
    }

    @GetMapping
    public PagedResponse<Product> getAll(Paginator paginator) {
        Page<Product> page = repository.findAll(paginator.toPageable());
        return new PagedResponse<>(page);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Optional<Product> getById(@PathVariable String id) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Unknown product");
        }
        return product;
    }

    @GetMapping
    @RequestMapping("/{id}/history")
    public Page<History> getHistory(@PathVariable("id") String id, Paginator paginator) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Unknown product");
        }
        Pageable pageable = paginator.toPageable(Sort.by(Sort.Order.desc("timestamp")));
        return historyRepository.findAllByProduct(product.get(), pageable);
    }

    @PostMapping
    public Product create(@RequestBody ProductBody body) {
        if (body.getName() == null) {
            throw new IllegalArgumentException("Missing product name");
        }
        if (body.getIdentifier() == null) {
            throw new IllegalArgumentException("Missing product identifier");
        }
        if (repository.findByIdentifier(body.getIdentifier()).isPresent()) {
            throw new IllegalArgumentException("Identifier is already taken");
        }
        Product product = new Product();
        product.setName(body.getName());
        product.setIdentifier(body.getIdentifier());
        product.setQuantity(body.getQuantity());
        product.setImage(body.getImage());
        return repository.save(product);
    }

    @PatchMapping
    public Product updateByIdentifier(@RequestBody ProductUpdate update) {
        if (update.getIdentifier() == null) {
            throw new IllegalArgumentException("Unknown product");
        }
        log.info("[Detection] " + update);
        Optional<Product> optionalProduct = repository.findByIdentifier(update.getIdentifier());
        if (optionalProduct.isEmpty()) {
            throw new IllegalArgumentException("Unknown product");
        }
        if (update.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be below 0");
        }
        if (!update.getImage().startsWith("https://")) {
            update.setImage("https://mazgo.s3.amazonaws.com/" + update.getImage());
        }
        Product product = optionalProduct.get();
        log.info("[Detection] " + product.getIdentifier() + "/" + product.getId());
        HistoryAction action;
        int difference;
        if (update.getQuantity() == product.getQuantity()) {
            return product;
        } else if (update.getQuantity() > product.getQuantity()) {
            action = HistoryAction.ADD;
            difference = update.getQuantity() - product.getQuantity();
        } else {
            action = HistoryAction.REMOVE;
            difference = product.getQuantity() - update.getQuantity();
        }
        History history = new History();
        history.setAction(action);
        history.setProduct(product);
        history.setImage(update.getImage());
        history.setQuantity(difference);
        historyRepository.save(history);
        product.setQuantity(update.getQuantity());
        repository.save(product);
        return product;
    }

}
