package org.example.socials.storage.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.socials.storage.model.SocialItem;
import org.example.socials.storage.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@AllArgsConstructor
public class SocialsItemService {

    private final ItemRepository itemRepository;

    //TODO: to refactor in accordance with correct algorithm
    public void writeSocialItems(List<SocialItem> items) {
        List<SocialItem> existingItems = getExistingSocialItems(items);
        List<SocialItem> itemsToStore = defineSocialItemsToStore(items, existingItems);
        if (!itemsToStore.isEmpty()) { //TODO: not necessary?
            itemRepository.saveAll(itemsToStore);
            log.debug("{} new items stored in the repository", itemsToStore.size());
        }
    }

    public List<SocialItem> search() {
        List<SocialItem> socialItems = new ArrayList<>();
        itemRepository.findAll().iterator().forEachRemaining(socialItems::add);
        return socialItems;
    }

    public void deleteById(String id) {
        itemRepository.deleteById(id);
    }

    public void truncateAllIndex() {
        itemRepository.deleteAll();
    }

    private List<SocialItem> getExistingSocialItems(List<SocialItem> socialItems) {
        return StreamSupport.stream(itemRepository
                        .findAllById(socialItems.stream()
                                .map(SocialItem::getId)
                                .collect(Collectors.toList())).spliterator(), false)
                .collect(Collectors.toList());
    }

    private List<SocialItem> defineSocialItemsToStore(List<SocialItem> socialItems,
                                                      List<SocialItem> existingSocialItems) {
        return socialItems.stream()
                .filter(item -> !existingSocialItems.contains(item))
                .collect(Collectors.toList());
    }

}
