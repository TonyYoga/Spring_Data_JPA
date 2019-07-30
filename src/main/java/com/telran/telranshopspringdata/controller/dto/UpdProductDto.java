package com.telran.telranshopspringdata.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UpdProductDto {
    private String prodId;
    private BigDecimal price;
}
