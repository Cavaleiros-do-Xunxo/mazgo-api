package net.notfab.mazgo.internal.paging;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
public class PagedResponse<T> {

    private int page;
    private int limit;
    private boolean last;

    private List<T> items = new ArrayList<>();

    public PagedResponse(Page<T> page) {
        this.page = page.getNumber();
        this.limit = page.getPageable().getPageSize();
        this.last = page.isLast();
        this.items = page.getContent();
    }

}
