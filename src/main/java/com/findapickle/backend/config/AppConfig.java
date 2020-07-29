package com.findapickle.backend.config;

import com.findapickle.backend.entities.UserEntity;
import com.findapickle.backend.models.dto.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    @Bean
    @Primary
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "UsersMapper")
    public ModelMapper uerMapper(){
        ModelMapper userMapper =  new ModelMapper();
        userMapper.typeMap(UserEntity.class, User.class).addMappings(mapping -> {
            mapping.skip(User::setPassword);
        });
        userMapper.typeMap(User.class, UserEntity.class).addMappings(mapping -> {
            mapping.skip(UserEntity::setCreatedAt);
            mapping.skip(UserEntity::setUpdatedAt);
        });
        return userMapper;
    }
}
