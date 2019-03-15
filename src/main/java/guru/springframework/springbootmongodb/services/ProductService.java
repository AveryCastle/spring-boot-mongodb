package guru.springframework.springbootmongodb.services;

import guru.springframework.springbootmongodb.commands.ProductForm;
import guru.springframework.springbootmongodb.converters.ProductFormToProduct;
import guru.springframework.springbootmongodb.domain.Product;
import guru.springframework.springbootmongodb.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductFormToProduct productFormToProduct;

    public List<Product> listAll() {
        final List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Product getById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }

    public Product saveOrUpdateProductForm(ProductForm productForm) {
        Product savedProduct = saveOrUpdate(productFormToProduct.convert(productForm));
        return savedProduct;
    }
}
