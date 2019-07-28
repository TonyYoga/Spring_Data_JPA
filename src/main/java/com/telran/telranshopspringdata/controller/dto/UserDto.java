package com.telran.telranshopspringdata.controller.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String email;
    private String name;
    private String phone;
    private BigDecimal balance;
}
