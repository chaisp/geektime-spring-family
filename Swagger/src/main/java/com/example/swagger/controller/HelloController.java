package com.example.swagger.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;

@RestController
@ApiOperation(value = "1111")
public class HelloController {
    @GetMapping("/hello")
    public  String helloController(){
        return "hello Swagger";
    }
    @PostMapping("/test")
    public String getName(@ApiParam(value = "测试") String name){
        return name;
    }
}
