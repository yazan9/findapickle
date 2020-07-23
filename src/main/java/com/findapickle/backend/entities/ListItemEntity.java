package com.findapickle.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "listsItems")
public class ListItemEntity implements Serializable{
    private static final long serialVersionUID = 5313493413859894404L;

    @Id
    @ManyToOne
    private ShoppingListEntity shoppingList;

    @Id
    @ManyToOne
    private ItemEntity item;

    public ListItemEntity(ShoppingListEntity shoppingList, ItemEntity item) {
        this.shoppingList = shoppingList;
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListItemEntity that = (ListItemEntity) o;
        return Objects.equals(shoppingList, that.shoppingList) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingList, item);
    }
}