package com.example.goodPlace.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T> {

    private final List<T> db = new ArrayList<>(); // 메모리 테이블
    private int index = 0; // id

    @Override
    public Optional<T> findById(int id) { //id로 찾기
        return db.stream()
                .filter(it->it.getId() == id)
                .findFirst();
    }

    @Override
    public T save(T entity) { //테이블 적재

        var foundEntity = db.stream()
                .filter(it -> it.getId() == entity.getId())
                .findFirst();

        if(foundEntity.isEmpty()) {
            // DB에 없는 경우
            entity.setId(++index); // id 생성
        }else {
            // DB에 있는 경우
            int id = foundEntity.get().getId();
            entity.setId(id);
            deleteById(id); //삭제
        }

        // 테이블 적재
        db.add(entity);

        return entity;
    }

    @Override
    public void deleteById(int id) {
        var foundEntity = db.stream()
                .filter(it -> it.getId() == id)
                .findFirst();

        if(foundEntity.isPresent()) {
            db.remove(foundEntity.get());
        }
    }

    @Override
    public List<T> findAll() {
        return db;
    }
}
