package com.example.goodPlace.favorplace.service;

import com.example.goodPlace.favorplace.dto.FavorPlaceDto;
import com.example.goodPlace.favorplace.entity.FavorPlaceEntity;
import com.example.goodPlace.favorplace.repository.FavorPlaceRepository;
import com.example.goodPlace.naver.NaverClient;
import com.example.goodPlace.naver.dto.SearchImageReq;
import com.example.goodPlace.naver.dto.SearchLocalReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavorPlaceService {

    private final NaverClient naverClient;
    private final FavorPlaceRepository favorPlaceRepository;

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

    public FavorPlaceDto add(FavorPlaceDto favorPlaceDto) {
        var entity = dtoToEntity(favorPlaceDto);

        var savedEntity =  favorPlaceRepository.save(entity);

        return entityToDto(savedEntity);
    }

    private FavorPlaceEntity dtoToEntity(FavorPlaceDto favorPlaceDto) {
        var entity = new FavorPlaceEntity();

        entity.setId(favorPlaceDto.getIndex());
        entity.setTitle(favorPlaceDto.getTitle());
        entity.setCategory(favorPlaceDto.getCategory());
        entity.setAddress(favorPlaceDto.getAddress());
        entity.setRoadAddress(favorPlaceDto.getRoadAddress());
        entity.setHomePage(favorPlaceDto.getHomePage());
        entity.setImage(favorPlaceDto.getImage());
        entity.setVisit(favorPlaceDto.isVisit());
        entity.setVisitCount(favorPlaceDto.getVisitCount());
        entity.setRecentVisitDate(favorPlaceDto.getRecentVisitDate());

        return entity;
    }

    private FavorPlaceDto entityToDto(FavorPlaceEntity favorPlaceEntity) {
        var dto = new FavorPlaceDto();

        dto.setIndex(favorPlaceEntity.getId());
        dto.setTitle(favorPlaceEntity.getTitle());
        dto.setCategory(favorPlaceEntity.getCategory());
        dto.setAddress(favorPlaceEntity.getAddress());
        dto.setRoadAddress(favorPlaceEntity.getRoadAddress());
        dto.setHomePage(favorPlaceEntity.getHomePage());
        dto.setImage(favorPlaceEntity.getImage());
        dto.setVisit(favorPlaceEntity.isVisit());
        dto.setVisitCount(favorPlaceEntity.getVisitCount());
        dto.setRecentVisitDate(favorPlaceEntity.getRecentVisitDate());

        return dto;
    }

    public List<FavorPlaceDto> findAll() {
        return favorPlaceRepository.findAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }

    public void delete(int index) {
        favorPlaceRepository.deleteById(index);
    }

    public void addVisit(int index) {
        var favorPlaceItem = favorPlaceRepository.findById(index);

        if(favorPlaceItem.isPresent()) {
            var item = favorPlaceItem.get();
            item.setVisit(true);
            item.setVisitCount(item.getVisitCount() + 1);
        }
    }
}
