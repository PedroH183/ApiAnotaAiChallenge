package com.pedrofreires.desafioanotai.domain.category;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    private String id;
    private String title;
    private String ownerId;
    private String description;


    public Category(CategoryDTO categoryDTO){
        this.title = categoryDTO.title();
        this.ownerId = categoryDTO.ownerId();
        this.description = categoryDTO.description();
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("id" , this.id);
        json.put("type" , "category");
        json.put("title" , this.title);
        json.put("ownerId" , this.ownerId);
        json.put("description" , this.description);

        return json.toString(); // json.stringfy()
    }

}
