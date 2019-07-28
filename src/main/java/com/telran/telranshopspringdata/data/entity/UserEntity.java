package com.telran.telranshopspringdata.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String email;
    private String name;
    @Column(unique = true)
    private String phone;
    @Column(precision = 10,scale = 2)
    private BigDecimal balance;
    @OneToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCartEntity shoppingCart;
    @OneToMany(mappedBy = "owner")
    private List<OrderEntity> orders;
}
