package com.findapickle.backend.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sectionsItems")
public class SectionItem implements Serializable {
    private static final long serialVersionUID = 5313493413859894403L;

    @Id
    @ManyToOne
    private Section section;

    @Id
    @ManyToOne
    private Item item;

    public SectionItem(Section section, Item item) {
        this.section = section;
        this.item = item;
    }

    public SectionItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SectionItem that = (SectionItem) o;
        return Objects.equals(section, that.section) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section, item);
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}