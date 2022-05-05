package com.example.goodPlace.favorRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FavorPlaceRepositoryTest {

    @Autowired
    private FavorPlaceRepository favorPlaceRepository;

    @Test
    public void 한개찾기테스트() {
        var place = create();
        var expected = favorPlaceRepository.save(place);

        Assertions.assertThat(expected).isNotNull();
        Assertions.assertThat(1).isEqualTo(expected.getId());
    }

    @Test
    public void 모두찾기테스트() {

    }

    @Test
    public void 하나삭제테스트() {

    }

    @Test
    public void 하나저장테스트() {

    }


    private FavorPlaceEntity create() {
        var FavorList = new FavorPlaceEntity();
        FavorList.setM_sTitle("title");
        FavorList.setM_sCategory("category");
        FavorList.setM_sAddress("address");
        FavorList.setM_sRoadAddress("roadAddress");
        FavorList.setM_sHomePage("");
        FavorList.setM_sImage("");
        FavorList.setM_bVisited(false);
        FavorList.setM_RecentVisit(null);
        FavorList.setM_iVisit(0);
        return FavorList;
    }

}