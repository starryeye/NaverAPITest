package com.example.goodPlace.repository;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {

    Optional<T> findById(int id); // 찾기
    List<T> findAll(); // 모두 찾아 리스트

    T save(T entity); // 저장
    void deleteById(int id); // 삭제
}
