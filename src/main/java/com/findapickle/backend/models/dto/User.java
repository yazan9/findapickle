package com.findapickle.backend.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
    private long id;

    private String username;

    private String email;

    private String avatar;

    private String facebookId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String password;
}