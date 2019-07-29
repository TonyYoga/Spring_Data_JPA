package com.telran.telranshopspringdata.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private Timestamp date;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;
    @OneToMany(mappedBy = "order")
    private List<ProductOrderEntity> products;
}
