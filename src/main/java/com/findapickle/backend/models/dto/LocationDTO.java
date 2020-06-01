package com.findapickle.backend.models.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDTO {
    public long id;

    public String name;

    public String address;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public LocationDTO(){}
}