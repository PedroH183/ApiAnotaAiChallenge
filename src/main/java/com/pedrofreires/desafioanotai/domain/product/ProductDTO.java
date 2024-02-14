package com.pedrofreires.desafioanotai.domain.product;

public record ProductDTO(
        String title, String ownerId, Integer price, String description, String categoryId) {
}
