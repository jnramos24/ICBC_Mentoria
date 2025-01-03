package com.example.resoluciones_mentoria.controller;

import com.example.resoluciones_mentoria.service.ComissionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comission")
public class ComissionController {

    private final ComissionService comissionService;

    public ComissionController(ComissionService comissionService){
        this.comissionService = comissionService;
    }

    @GetMapping("/{userType}/{salesAmount}")
    public double getComission(@PathVariable String userType, @PathVariable double salesAmount){
        return ComissionService.comissionCalculator(userType, salesAmount);
    }

}
