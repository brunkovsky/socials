package org.example.socials.storage.controller;

import lombok.AllArgsConstructor;
import org.example.socials.storage.model.SocialItem;
import org.example.socials.storage.service.SocialsItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/socials/storage")
public class SocialsItemController {

    private final SocialsItemService service;


    @GetMapping("/search")
    public ResponseEntity<List<SocialItem>> search(@RequestParam(required = false) String orderBy,
                                                   @RequestParam(required = false) Boolean desc) {
//        SocialsItemService.OrderBy[] values = SocialsItemService.OrderBy.values();
//        if (isAllowedParameter(orderBy, values)
//                && isAllowedParameter(desc, Boolean.TRUE, Boolean.FALSE)) {
            return new ResponseEntity<>(service.search(orderBy, desc), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void truncateAllIndex() {
        service.truncateAllIndex();
    }

    @SafeVarargs
    private <T> boolean isAllowedParameter(T value, T... options) {
        if (value == null) {
            return true;
        }
        for (T option : options) {
            if (option.equals(value)) {
                return true;
            }
        }
        return false;
    }

}
