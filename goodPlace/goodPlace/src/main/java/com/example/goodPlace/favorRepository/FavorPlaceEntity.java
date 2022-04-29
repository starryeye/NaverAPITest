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

    private String m_sCategory;            // 즐겨찾는 장소 카테고리
    private String m_sTitle;               // 장소명

    private String m_sHomePage;            // 홈페이지 주소
    private String m_sImage;               // 이미지

    private String m_sAddress;             // 주소
    private String m_sRoadAddress;         // 도로명 주소

    private int m_iVisit;                  // 방문 횟수
    private boolean m_bVisited;            // 방문 유무
    private LocalDateTime m_RecentVisit;   // 최근 방문 날짜
}
