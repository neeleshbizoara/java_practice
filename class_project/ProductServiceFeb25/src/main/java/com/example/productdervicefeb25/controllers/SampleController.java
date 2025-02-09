package com.example.productdervicefeb25.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/render")
public class SampleController {

//    @GetMapping("/hello")
//    public String sayHello() {
//        return "Welcome to First App.";
//    }

    @GetMapping("/hello/{numberOfTimes}")
    public String sayHello(@PathVariable("numberOfTimes") int number) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < number; i++) {
            sb.append("Hello EveryOne!!").append("<br/>");
        }
        return sb.toString();
    }

    @GetMapping("/bye")
    public String sayBye() {
        return "Bye";
    }
}
