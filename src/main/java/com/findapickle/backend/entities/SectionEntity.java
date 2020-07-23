package com.findapickle.backend.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "sections")
public class SectionEntity implements Serializable{
    private static final long serialVersionUID = -748956247024967639L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne private LocationEntity location;

    @ManyToMany
    @JoinTable(name="sections_items", joinColumns = @JoinColumn(name="section_id"), inverseJoinColumns = @JoinColumn(name="item_id"))
    private List<ItemEntity> items = new ArrayList<>();

    public void addItem(ItemEntity item){
        SectionItemEntity sectionItem = new SectionItemEntity(this, item);
        item.getSections().add(sectionItem);
    }

    public void removeItem(ItemEntity item) {
        SectionItemEntity sectionItem = new SectionItemEntity(this, item);
        item.getSections().remove(sectionItem);
        items.remove(sectionItem);
        sectionItem.setSection(null);
        sectionItem.setItem(null);
    }

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
        SectionEntity other = (SectionEntity) obj;
        if (id != other.id)
            return false;
        return true;
    }
}