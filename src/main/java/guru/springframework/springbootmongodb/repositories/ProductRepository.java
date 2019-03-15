package guru.springframework.springbootmongodb.repositories;

import guru.springframework.springbootmongodb.domain.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
}
