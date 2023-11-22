package com.example.tenisu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiInfoController {

    @GetMapping("/")
    public String getApiInfo() {
        return "api-info";
    }
}
