package com.telran.telranshopspringdata.data.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
