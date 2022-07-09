package com.example.goodPlace.favorplace.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FavorPlaceServiceTest {

    @Autowired
    private FavorPlaceService favorPlaceService;

    @Test
    public void searchTest() {
        var result = favorPlaceService.search("갈비집");
        System.out.println("result = " + result);
        Assertions.assertThat(result).isNotNull();
    }
}
