package com.example.goodPlace.favorRepository;

import com.example.goodPlace.repository.PlaceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavorPlaceEntity extends PlaceEntity {

    private String Category;            // 즐겨찾는 장소 카테고리
    private String Title;               // 장소명

    private String HomePage;            // 홈페이지 주소
    private String Image;               // 이미지

    private String Address;             // 주소
    private String RoadAddress;         // 도로명 주소

    private int Visit;                  // 방문 횟수
    private boolean Visited;            // 방문 유무
    private LocalDateTime RecentVisit;   // 최근 방문 날짜
}
