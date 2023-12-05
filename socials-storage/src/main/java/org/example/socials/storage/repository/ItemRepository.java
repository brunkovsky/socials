package org.example.socials.storage.repository;

import org.example.socials.storage.model.SocialItem;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ItemRepository extends ElasticsearchRepository<SocialItem, String> {

    List<SocialItem> findByOrderByCreatedAtDesc();

    @Query("{\"match\": {\"message\": {\"query\": \"?0\"}}}")
    List<SocialItem> findBy(String query);

}
