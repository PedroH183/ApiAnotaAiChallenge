package com.pedrofreires.desafioanotai.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.pedrofreires.desafioanotai.domain.product.Product;
import com.pedrofreires.desafioanotai.domain.product.ProductDTO;
import com.pedrofreires.desafioanotai.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    /**
     * Meu Controller é responsável apenas por comunicar com o service a logica
     * de manipulação com o banco de dados fica em service.
     * */
    private final ProductService service;

    public ProductController(ProductService service){ this.service = service;}

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productData){
        Product newProduct = this.service.create(productData);
        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = this.service.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO requestData){
        Product updatedProduct = this.service.update(id, requestData);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
