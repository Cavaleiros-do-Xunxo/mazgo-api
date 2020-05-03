package net.notfab.mazgo.repositories;

import net.notfab.mazgo.entities.History;
import net.notfab.mazgo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HistoryRepository extends PagingAndSortingRepository<History, String> {

    Page<History> findAllByProduct(Product product, Pageable pageable);

}
