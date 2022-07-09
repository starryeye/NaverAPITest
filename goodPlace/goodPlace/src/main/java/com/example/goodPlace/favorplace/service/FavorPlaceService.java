package com.example.goodPlace.favorplace.service;

import com.example.goodPlace.favorplace.dto.FavorPlaceDto;
import com.example.goodPlace.naver.NaverClient;
import com.example.goodPlace.naver.dto.SearchImageReq;
import com.example.goodPlace.naver.dto.SearchLocalReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavorPlaceService {

    private final NaverClient naverClient;

    public FavorPlaceDto search(String query) {

        //지역 검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if(searchLocalRes.getTotal() > 0) {
            var localItem = searchLocalRes.getItems()
                    .stream()
                    .findFirst()
                    .get();

            //이미지 검색
            var imageQuery = localItem.getTitle()
                    .replaceAll("<[^>]*>", ""); //이상한 문자 삭제처리
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0) {
                var imageItem = searchImageRes.getItems()
                        .stream()
                        .findFirst()
                        .get();


                //결과 리턴
                var result = new FavorPlaceDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePage(localItem.getLink());
                result.setImage(imageItem.getLink());

                return result;
            }
        }

        return new FavorPlaceDto();

    }
}
