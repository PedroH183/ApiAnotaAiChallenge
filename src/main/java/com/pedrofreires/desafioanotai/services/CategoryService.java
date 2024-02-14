package com.pedrofreires.desafioanotai.services;

import org.springframework.stereotype.Service;
import com.pedrofreires.desafioanotai.services.aws.MessageDTO;
import com.pedrofreires.desafioanotai.domain.category.Category;
import com.pedrofreires.desafioanotai.services.aws.AwsSnsService;
import com.pedrofreires.desafioanotai.domain.category.CategoryDTO;
import com.pedrofreires.desafioanotai.repositories.CategoryRepository;
import com.pedrofreires.desafioanotai.domain.category.exceptions.CategoryNotFound;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final AwsSnsService snsService;
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository, AwsSnsService snsService){
        this.repository = repository;
        this.snsService = snsService;
    }

    public Category create(CategoryDTO categoryData){
        Category newCategory = new Category( categoryData );
        this.repository.save(newCategory);

        this.snsService.publish(new MessageDTO(newCategory.toString()));
        return newCategory;
    }

    public List<Category> getAll() {
         return this.repository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData) {
        Category category = this.repository.findById(id)
                .orElseThrow(CategoryNotFound::new);

        if( categoryData.title() != null) category.setTitle(categoryData.title());
        if(categoryData.description() != null) category.setDescription(categoryData.description());

        this.repository.save(category);
        this.snsService.publish(new MessageDTO(category.toString()));

        return category;
    }

    public void delete(String id) {
        Category category = this.repository.findById(id)
                .orElseThrow(CategoryNotFound::new);

        this.repository.delete(category);
    }

    public Optional<Category> getView(String id){
        return this.repository.findById(id);
    }
}
