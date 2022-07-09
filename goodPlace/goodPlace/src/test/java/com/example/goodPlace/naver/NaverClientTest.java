package com.example.goodPlace.naver;

import com.example.goodPlace.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void SearchLocalTest() {
        
        var search = new SearchLocalReq();
        search.setQuery("갈비집");
        
        var result = naverClient.localSearch(search);
        System.out.println("result = " + result);
    }
}
