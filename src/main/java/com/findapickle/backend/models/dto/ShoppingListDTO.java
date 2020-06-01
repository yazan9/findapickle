package com.findapickle.backend.models.dto;

import java.time.LocalDateTime;

public class ShoppingListDTO {
    public long id;

    public String name;

    public String description;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public long user_id;
}