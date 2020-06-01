package com.findapickle.backend.models.dto;

public class StoreDTO{
    private long id;

    private String name;

    public StoreDTO(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}