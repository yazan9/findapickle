//package com.findapickle.backend.entities;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@Table(name = "sectionsItems")
//public class SectionItemEntity implements Serializable {
//    private static final long serialVersionUID = 5313493413859894403L;
//
//    @Id
//    @ManyToOne
//    private SectionEntity section;
//
//    @Id
//    @ManyToOne
//    private ItemEntity item;
//
//    public SectionItemEntity(SectionEntity section, ItemEntity item) {
//        this.section = section;
//        this.item = item;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        SectionItemEntity that = (SectionItemEntity) o;
//        return Objects.equals(section, that.section) && Objects.equals(item, that.item);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(section, item);
//    }
//}