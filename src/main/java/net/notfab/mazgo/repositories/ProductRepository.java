package net.notfab.mazgo.repositories;

import net.notfab.mazgo.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

    Optional<Product> findByIdentifier(String identifier);
    
}
