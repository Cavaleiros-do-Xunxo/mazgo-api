package net.notfab.mazgo.internal.paging;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.ws.rs.QueryParam;

@Getter
public class Paginator {

    private int limit = 50;
    private int page = 0;

    @QueryParam("limit")
    public void setLimit(int limit) {
        if (limit > 100) {
            throw new IllegalArgumentException("Limit cannot be above 100");
        }
        if (limit == 0) {
            return;
        }
        this.limit = limit;
    }

    @QueryParam("page")
    public void setPage(int page) {
        if (page > 1000) {
            throw new IllegalArgumentException("Page cannot be above 1000");
        }
        this.page = page;
    }

    public Pageable toPageable() {
        return PageRequest.of(this.page, this.limit);
    }

    public Pageable toPageable(Sort sort) {
        return PageRequest.of(this.page, this.limit, sort);
    }

}
