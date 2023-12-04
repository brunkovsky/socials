package org.example.socials.generator.controller;

import lombok.AllArgsConstructor;
import org.example.socials.generator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
