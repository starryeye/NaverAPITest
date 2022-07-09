package com.example.goodPlace.naver;

import com.example.goodPlace.naver.dto.SearchImageReq;
import com.example.goodPlace.naver.dto.SearchImageRes;
import com.example.goodPlace.naver.dto.SearchLocalReq;
import com.example.goodPlace.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq){

        //Http 요청 uri 만들기
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        //Http 요청 헤더 만들기
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //만들어준 헤더를 httpEntity 에 담는다.
        var httpEntity = new HttpEntity<>(headers);

        //Http 응답 타입은.. SearchLocalRes 이다.
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};


        //만든 uri, httpEntity를 GET방식, 응답 타입을 정의하여 요청 보내고 responseEntity 변수로 받는다.
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        //받은 Http 응답 데이터의 바디부를 리턴한다.
        return responseEntity.getBody();
    }

    public SearchImageRes searchImage(SearchImageReq searchImageReq){

        //Http 요청 uri 만들기
        var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        //Http 요청 헤더 만들기
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //만들어준 헤더를 httpEntity 에 담는다.
        var httpEntity = new HttpEntity<>(headers);

        //Http 응답 타입은.. SearchLocalRes 이다.
        var responseType = new ParameterizedTypeReference<SearchImageRes>(){};


        //만든 uri, httpEntity를 GET방식, 응답 타입을 정의하여 요청 보내고 responseEntity 변수로 받는다.
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        //받은 Http 응답 데이터의 바디부를 리턴한다.
        return responseEntity.getBody();
    }
}
