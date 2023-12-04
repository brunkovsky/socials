package org.example.socials.storage.repository;

import org.example.socials.storage.model.SocialItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepository extends ElasticsearchRepository<SocialItem, String> {
}
