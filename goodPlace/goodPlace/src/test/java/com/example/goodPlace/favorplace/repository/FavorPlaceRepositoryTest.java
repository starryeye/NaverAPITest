package com.example.goodPlace.favorplace.repository;

import com.example.goodPlace.favorplace.entity.FavorPlaceEntity;
import com.example.goodPlace.favorplace.repository.FavorPlaceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FavorPlaceRepositoryTest {

    @Autowired
    private FavorPlaceRepository favorPlaceRepository;

    @Test
    public void findByIdTest() {
        var place = create();
        favorPlaceRepository.save(place);

        var expected = favorPlaceRepository.findById(place.getId());

        //expected는 optional 이므로 not null 의미가 없다.
        assertThat(expected.isPresent()).isEqualTo(true);

        assertThat(place).isEqualTo(expected.get());
    }

    @Test
    public void findAllTest() {
        var place1 = create();
        favorPlaceRepository.save(place1);

        var place2 = create();
        favorPlaceRepository.save(place2);

        //favorPlaceRepository 의 entity 개수..
        int count = favorPlaceRepository.findAll().size();

        assertThat(2).isEqualTo(count);

    }

    @Test
    public void deleteTest() {
        var place = create();
        favorPlaceRepository.save(place);

        favorPlaceRepository.deleteById(place.getId());

        //favorPlaceRepository 의 entity 개수..
        int count = favorPlaceRepository.findAll().size();

        assertThat(0).isEqualTo(count);
    }

    @Test
    public void saveTest() {
        var favorPlaceEntity = create();
        var result = favorPlaceRepository.save(favorPlaceEntity);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(favorPlaceEntity);
    }

    @Test
    public void updateTest() {
        var favorPlaceEntity = create();
        var savedEntity = favorPlaceRepository.save(favorPlaceEntity);

        savedEntity.setTitle("update test");
        var updateEntity = favorPlaceRepository.save(savedEntity);

        assertThat(updateEntity.getTitle()).isEqualTo("update test");

        //favorPlaceRepository 의 entity 개수..
        int count = favorPlaceRepository.findAll().size();

        assertThat(1).isEqualTo(count);
    }



    private FavorPlaceEntity create() {
        var favorList = new FavorPlaceEntity();

        favorList.setTitle("title");
        favorList.setCategory("category");
        favorList.setAddress("address");
        favorList.setRoadAddress("roadAddress");
        favorList.setHomePage("");
        favorList.setImage("");
        favorList.setVisit(false);
        favorList.setRecentVisitDate(null);
        favorList.setVisitCount(0);

        return favorList;
    }

}