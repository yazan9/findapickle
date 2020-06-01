package com.findapickle.backend.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "listsItems")
public class ListItem implements Serializable{
    private static final long serialVersionUID = 5313493413859894404L;

    @Id
    @ManyToOne
    private ShoppingList shoppingList;

    @Id
    @ManyToOne
    private Item item;

    public ListItem(ShoppingList shoppingList, Item item) {
        this.shoppingList = shoppingList;
        this.item = item;
    }

    public ListItem() {
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
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
        ListItem that = (ListItem) o;
        return Objects.equals(shoppingList, that.shoppingList) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingList, item);
    }
}