/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@SpringBootApplication
@RestController
public class Warehouseapplication {
    public static void main(String[] args) {
        SpringApplication.run(Warehouseapplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "WElcome to cyangugu System ..";
    }

    
    
}
