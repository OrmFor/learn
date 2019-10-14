package com.design.learn.springdemo.factorybeandemo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class Car {

    private String brand;

    private int maxSpeed;

    private BigDecimal price;

}
