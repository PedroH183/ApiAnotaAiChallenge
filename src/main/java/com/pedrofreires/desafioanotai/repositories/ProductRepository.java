package com.pedrofreires.desafioanotai.repositories;

import org.springframework.stereotype.Repository;
import com.pedrofreires.desafioanotai.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}

