package com.example.goodPlace.db;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository<T> {

    Optional<T> findById(int id); // 찾기
    T save(T entity); // 저장
    void deleteById(int id); // 삭제
    List<T> findAll(); // 모두 찾아 리스트
}
