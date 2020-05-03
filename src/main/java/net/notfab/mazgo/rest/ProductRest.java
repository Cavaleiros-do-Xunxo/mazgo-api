package net.notfab.mazgo.rest;

import net.notfab.mazgo.entities.History;
import net.notfab.mazgo.entities.HistoryAction;
import net.notfab.mazgo.entities.Product;
import net.notfab.mazgo.entities.dto.ProductBody;
import net.notfab.mazgo.entities.dto.ProductUpdate;
import net.notfab.mazgo.internal.paging.PagedResponse;
import net.notfab.mazgo.internal.paging.Paginator;
import net.notfab.mazgo.repositories.HistoryRepository;
import net.notfab.mazgo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/products")
public class ProductRest {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private HistoryRepository historyRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@BeanParam Paginator paginator) {
        Page<Product> page = repository.findAll(paginator.toPageable());
        return Response
                .ok(new PagedResponse<>(page))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String id) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Unknown product");
        }
        return Response
                .ok(product)
                .build();
    }

    @GET
    @Path("/{id}/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHistory(@PathParam("id") String id, @BeanParam Paginator paginator) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Unknown product");
        }
        Pageable pageable = paginator.toPageable(Sort.by(Sort.Order.desc("timestamp")));
        Page<History> page = historyRepository.findAllByProduct(product.get(), pageable);
        return Response
                .ok(new PagedResponse<>(page))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ProductBody body) {
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
        return Response.ok(repository.save(product)).build();
    }

    @PATCH
    @Path("/{idf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateByIdentifier(@PathParam("idf") String idf, ProductUpdate update) {
        Optional<Product> optionalProduct = repository.findByIdentifier(idf);
        if (optionalProduct.isEmpty()) {
            throw new IllegalArgumentException("Unknown product");
        }
        if (update.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be below 0");
        }
        Product product = optionalProduct.get();
        HistoryAction action;
        int difference;
        if (update.getQuantity() == product.getQuantity()) {
            return Response.accepted().build();
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
        return Response
                .accepted()
                .build();
    }

}
