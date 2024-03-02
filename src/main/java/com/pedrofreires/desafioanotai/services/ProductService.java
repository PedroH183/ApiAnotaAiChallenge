package com.pedrofreires.desafioanotai.services;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.pedrofreires.desafioanotai.domain.product.Product;
import com.pedrofreires.desafioanotai.services.aws.MessageDTO;
import com.pedrofreires.desafioanotai.domain.product.ProductDTO;
import com.pedrofreires.desafioanotai.services.aws.AwsSnsService;
import com.pedrofreires.desafioanotai.repositories.ProductRepository;
import com.pedrofreires.desafioanotai.domain.product.exceptions.ProductNotFound;
import com.pedrofreires.desafioanotai.domain.category.exceptions.CategoryNotFound;

@Service
public class ProductService {

    private final AwsSnsService snsService;
    private final ProductRepository repository;
    private final CategoryService categoryService;

    public ProductService (CategoryService categoryService, ProductRepository repository, AwsSnsService snsService){
        this.repository = repository;
        this.snsService = snsService;
        this.categoryService = categoryService;
    }

    public Product create(ProductDTO productData){

        this.categoryService.getView(productData.categoryId()).orElseThrow(ProductNotFound::new);
        Product newProduct = new Product( productData );

        this.repository.save(newProduct);
        this.snsService.publish(new MessageDTO(newProduct.toString())); // enviando mensagem via sns

        return newProduct;
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    public Product update(String id, ProductDTO requestData) {
        Product updatedProduct = this.repository
                .findById(id).orElseThrow(ProductNotFound::new);

        if( requestData.categoryId() != null ){
            this.categoryService.getView(requestData.categoryId()).orElseThrow(CategoryNotFound::new);
            updatedProduct.setCategoryId(requestData.categoryId());
        }

        if( requestData.price() != null) updatedProduct.setPrice(requestData.price());
        if( requestData.title() != null) updatedProduct.setTitle(requestData.title());
        if( requestData.description() != null) updatedProduct.setDescription(requestData.description());

        this.repository.save(updatedProduct);
        this.snsService.publish(new MessageDTO(updatedProduct.toString())); // enviando mensagem via sns

        return updatedProduct;
    }

    public void delete(String id){
        Product deletedProduct = this.repository
                .findById(id).orElseThrow(ProductNotFound::new);

        this.repository.delete(deletedProduct);
        this.snsService.publish(new MessageDTO(deletedProduct.toDeleteString())); // enviando mensagem via sns
    }
}
