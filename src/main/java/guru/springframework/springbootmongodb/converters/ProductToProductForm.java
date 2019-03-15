package guru.springframework.springbootmongodb.converters;

import guru.springframework.springbootmongodb.commands.ProductForm;
import guru.springframework.springbootmongodb.domain.Product;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {

    @Override
    public ProductForm convert(Product source) {
        ProductForm productForm = new ProductForm();
        productForm.setId(source.getId().toHexString());
        productForm.setDescription(source.getDescription());
        productForm.setPrice(source.getPrice());
        productForm.setImageUrl(source.getImageUrl());
        return productForm;
    }
}
