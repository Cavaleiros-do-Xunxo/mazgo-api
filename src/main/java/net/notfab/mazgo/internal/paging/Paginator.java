package net.notfab.mazgo.internal.paging;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
public class Paginator {

    private int limit = 50;
    private int page = 0;

    public void setLimit(int limit) {
        if (limit > 100) {
            throw new IllegalArgumentException("Limit cannot be above 100");
        }
        if (limit == 0) {
            return;
        }
        this.limit = limit;
    }

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
