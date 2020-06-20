
package com.example.MyApp;

import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

@RestController
public class Controller {
    @GetMapping("/hi")
    public String sayHello()
    {
        return "Welcome to Spring Boot";
    }
    @GetMapping("/ho")
    public String yo()
    {
        return "YO YO";
    }
    @GetMapping("/search")
    public String func(@RequestParam String q)
    {
        return q+" is Parameter";
    }
    @GetMapping("/search/{id}")
    public String search_an_id(@PathVariable int id)
    {
        return "PQR";
    }

    @PostMapping("/users ")
    public boolean posttest(@RequestBody int z)
    {
        return false;
    }
//USE SWAGGER
//ngrog
}
