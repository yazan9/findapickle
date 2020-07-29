package com.findapickle.backend.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "shoppingLists")
public class ShoppingListEntity implements Serializable {
    private static final long serialVersionUID = -748956247024967641L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne private UserEntity user;

    @ManyToMany
    @JoinTable(name="lists_items", joinColumns = @JoinColumn(name="shopping_list_id"), inverseJoinColumns = @JoinColumn(name="item_id"))
    private List<ItemEntity> items = new ArrayList<>();

//    public void addItem(ItemEntity item){
//        ListItemEntity listItem = new ListItemEntity(this, item);
//        item.getShoppingLists().add(listItem);
//    }
//
//    public void removeItem(ItemEntity item) {
//        ListItemEntity listItem = new ListItemEntity(this, item);
//        item.getShoppingLists().remove(listItem);
//        items.remove(listItem);
//        listItem.setShoppingList(null);
//        listItem.setItem(null);
//    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShoppingListEntity other = (ShoppingListEntity) obj;
        if (id != other.id)
            return false;
        return true;
    }
}