package net.notfab.mazgo.rest;

import net.notfab.mazgo.entities.History;
import net.notfab.mazgo.internal.paging.PagedResponse;
import net.notfab.mazgo.internal.paging.Paginator;
import net.notfab.mazgo.repositories.HistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/history")
public class HistoryRest {

    private final HistoryRepository repository;

    public HistoryRest(HistoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public PagedResponse<History> getAll(Paginator paginator) {
        Pageable pageable = paginator.toPageable(Sort.by(Sort.Order.desc("timestamp")));
        Page<History> page = repository.findAll(pageable);
        return new PagedResponse<>(page);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Optional<History> getById(@PathVariable String id) {
        return repository.findById(id);
    }

}
