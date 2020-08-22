package com.example.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
@ApiModel(value = "这个叫咖啡")
public class Coffee {
    @ApiModelProperty(value = "这个是咖啡的价格")
    private BigDecimal price;
    public  String name;
    protected Integer cup;
}
