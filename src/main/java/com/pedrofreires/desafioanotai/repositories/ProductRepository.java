package com.pedrofreires.desafioanotai.repositories;

import com.pedrofreires.desafioanotai.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}

