package com.telran.telranshopspringdata.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "products")
@Builder
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @Column(name = "name",unique = true)
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductEntity> products;
}
