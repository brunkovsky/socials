package org.example.socials.storage.controller;

import lombok.AllArgsConstructor;
import org.example.socials.storage.model.SocialItem;
import org.example.socials.storage.service.SocialsItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/socials/storage")
public class SocialsItemController {

    private final SocialsItemService service;


    @GetMapping("/search")
    public List<SocialItem> search() {
        return service.search();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void truncateAllIndex() {
        service.truncateAllIndex();
    }

}
