package net.notfab.mazgo.rest;

import net.notfab.mazgo.entities.History;
import net.notfab.mazgo.entities.Product;
import net.notfab.mazgo.internal.paging.PagedResponse;
import net.notfab.mazgo.internal.paging.Paginator;
import net.notfab.mazgo.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/history")
public class HistoryRest {

    @Autowired
    private HistoryRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@BeanParam Paginator paginator) {
        Pageable pageable = paginator.toPageable(Sort.by(Sort.Order.desc("timestamp")));
        Page<History> page = repository.findAll(pageable);
        return Response
                .ok(new PagedResponse<>(page))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String id) {
        Optional<History> product = repository.findById(id);
        return Response
                .ok(product)
                .build();
    }

}
