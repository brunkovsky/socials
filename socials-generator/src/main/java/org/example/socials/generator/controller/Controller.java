package org.example.socials.generator.controller;

import lombok.AllArgsConstructor;
import org.example.socials.generator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/socials/generator")
public class Controller {

    @Autowired
    private GeneratorService service;


    @PostMapping("/executor/{exchange}")
    public void forceExecute(@PathVariable("exchange") String exchange) {
        service.forceExecute(exchange);
    }


    @GetMapping("/redirect")
    public void forceExecute(@RequestParam Map<String,String> allRequestParams) {
        System.out.println("========");
        System.out.println(allRequestParams);
        System.out.println("========");
    }

}
