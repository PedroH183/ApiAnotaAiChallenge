package com.pedrofreires.desafioanotai.domain.product;
import com.pedrofreires.desafioanotai.domain.category.Category;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private Integer price;
    private String ownerId;
    private Category category;
    private String description;
}
