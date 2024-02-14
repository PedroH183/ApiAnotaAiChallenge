package com.pedrofreires.desafioanotai.repositories;

import org.springframework.stereotype.Repository;
import com.pedrofreires.desafioanotai.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
