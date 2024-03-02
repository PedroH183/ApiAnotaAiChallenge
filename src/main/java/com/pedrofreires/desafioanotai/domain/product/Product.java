package com.pedrofreires.desafioanotai.domain.product;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private Integer price;
    private String ownerId;
    private String categoryId;
    private String description;

    public Product( ProductDTO productData ){
        this.title = productData.title();
        this.price = productData.price();
        this.ownerId = productData.ownerId();
        this.description = productData.description();
        this.categoryId = productData.categoryId();
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("id" , this.id);
        json.put("type" , "product");
        json.put("title" , this.title);
        json.put("price" , this.price);
        json.put("ownerId" , this.ownerId);
        json.put("categoryId" , this.categoryId);
        json.put("description" , this.description);

        return json.toString(); // json.stringfy()
    }

    public String toDeleteString(){
        JSONObject json = new JSONObject();
        json.put("id" , this.id);
        json.put("type" , "product");
        json.put("ownerId" , this.ownerId);
        json.put("keyToDelete", true);

        return json.toString();
    }
}
