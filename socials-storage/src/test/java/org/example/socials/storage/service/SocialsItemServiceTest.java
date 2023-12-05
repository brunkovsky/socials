package org.example.socials.storage.service;

import org.example.socials.storage.model.SocialItem;
import org.example.socials.storage.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SocialsItemServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    SocialsItemService socialsItemService;


    @BeforeEach
    void setUp() {
        given(itemRepository.findAll()).willReturn(TestData.SOCIAL_ITEM_STREAM);
    }

    //    @Test
    void writeSocialItems() {
    }

    @Test
    void whenSearchParametersAreNullThenReturnAllDbRecordsNoOrder() {
        List<SocialItem> socialItemList = socialsItemService.search(null, false);
        Assertions.assertEquals(TestData.SOCIAL_ITEM_STREAM.size(), socialItemList.size());
    }

    @Test
    void whenSearchParametersAreCreatedAndFalseThenReturnAllDbRecordsOrderedByCreatedAtAsc() {
        List<SocialItem> socialItemList = socialsItemService.search("createdAt", false);
        Assertions.assertEquals(TestData.ALL_SOCIAL_ITEMS_FROM_ELASTIC_SORTED_BY_CREATED_AT, socialItemList);
    }

    @Test
    void whenSearchParametersAreCreatedAndTrueThenReturnAllDbRecordsOrderedByCreatedAtDesc() {
        List<SocialItem> socialItemList = socialsItemService.search("createdAt", true);
        Assertions.assertEquals(TestData.ALL_SOCIAL_ITEMS_FROM_ELASTIC_SORTED_BY_CREATED_AT_DESC, socialItemList);
    }

    //    @Test
    void deleteById() {
    }

    //    @Test
    void truncateAllIndex() {
    }

    interface TestData {
        SocialItem facebook1SocialItem = new SocialItem("1", "FACEBOOK", "facebook1",
                "https://facebook.com/post1", "facebookPost1Title",
                "facebook1Message", null, "https://facebook.com/post1/photo",
                null, Instant.ofEpochSecond(1500000000), false);        // 2017-07-14T02:40:00Z

        SocialItem facebook2SocialItem = new SocialItem("2", "FACEBOOK", "facebook1",
                "https://facebook.com/post2", "facebookPost2Title",
                "facebook1Message", null, "https://facebook.com/post2/photo",
                null, Instant.ofEpochSecond(1500000010), false);        // 2017-07-14T02:40:10Z

        SocialItem instagram1SocialItem = new SocialItem("3", "INSTAGRAM", "instagram1",
                "https://instagram.com/post1", "instagramPost1Title",
                "instagram1Message", null, "https://instagram.com/post1/photo",
                null, Instant.ofEpochSecond(1500000020), false);        // 2017-07-14T02:40:20Z

        SocialItem instagram2SocialItem = new SocialItem("4", "INSTAGRAM", "instagram2",
                "https://instagram.com/post2", "instagramPost2Title",
                "instagram2Message", null, "https://instagram.com/post2/photo",
                null, Instant.ofEpochSecond(1500000030), false);        // 2017-07-14T02:40:30Z

        SocialItem youtube1SocialItem = new SocialItem("5", "YOUTUBE", "youtube1",
                "https://youtube.com/post1", "youtubePost1Title",
                "youtube1Message", null, "https://youtube.com/post1/photo",
                "https://youtube.com/post1/video", Instant.ofEpochSecond(1500000040),  // 2017-07-14T02:40:40Z
                false);

        SocialItem youtube2SocialItem = new SocialItem("6", "YOUTUBE", "youtube2",
                "https://youtube.com/post2", "youtubePost2Title",
                "youtube2Message", null, "https://youtube.com/post2/photo",
                "https://youtube.com/post2/video", Instant.ofEpochSecond(1500000050),  // 2017-07-14T02:40:50Z
                false);

        List<SocialItem> SOCIAL_ITEM_STREAM = List.of(facebook1SocialItem, facebook2SocialItem,
                instagram1SocialItem, instagram2SocialItem,
                youtube1SocialItem, youtube2SocialItem);

        List<SocialItem> ALL_SOCIAL_ITEMS_FROM_ELASTIC_SORTED_BY_CREATED_AT = SOCIAL_ITEM_STREAM.stream()
                .sorted(Comparator.comparing(SocialItem::getCreatedAt))
                .collect(Collectors.toList());

        List<SocialItem> ALL_SOCIAL_ITEMS_FROM_ELASTIC_SORTED_BY_CREATED_AT_DESC = SOCIAL_ITEM_STREAM.stream()
                .sorted(Comparator.comparing(SocialItem::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

}