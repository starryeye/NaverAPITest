package com.example.goodPlace.favorplace.repository;

import com.example.goodPlace.favorplace.entity.FavorPlaceEntity;
import com.example.goodPlace.repository.MemoryDbRepositoryAbstract;
import org.springframework.stereotype.Repository;

@Repository
public class FavorPlaceRepository extends MemoryDbRepositoryAbstract<FavorPlaceEntity> {
}
