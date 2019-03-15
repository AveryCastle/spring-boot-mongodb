package guru.springframework.springbootmongodb.controllers;

import guru.springframework.springbootmongodb.commands.ProductForm;
import guru.springframework.springbootmongodb.converters.ProductToProductForm;
import guru.springframework.springbootmongodb.domain.Product;
import guru.springframework.springbootmongodb.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductToProductForm productToProductForm;

    @GetMapping("/")
    public String redirectToList() {
        return "redirect:/product/list";
    }

    @GetMapping({"/product/list", "/product"})
    public String listProducts(Model model) {
        model.addAttribute("products", productService.listAll());
        return "product/list";
    }

    @GetMapping("/product/show/{id}")
    public String getProduct(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product/show";
    }

    @GetMapping("/product/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Product product = productService.getById(id);
        ProductForm productForm = productToProductForm.convert(product);

        model.addAttribute("productForm", productForm);
        return "product/productform";
    }

    @GetMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "product/productform";
    }

    @PostMapping(value = "/product")
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "product/productform";
        }

        Product savedProduct = productService.saveOrUpdateProductForm(productForm);

        return "redirect:/product/show/" + savedProduct.getId();
    }

    @GetMapping("/product/delete/{id}")
    public String delete(@PathVariable String id) {
        productService.delete(id);
        return "redirect:/product/list";
    }
}
