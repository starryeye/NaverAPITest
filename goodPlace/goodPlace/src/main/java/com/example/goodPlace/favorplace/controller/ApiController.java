package com.example.goodPlace.favorplace.controller;

import com.example.goodPlace.favorplace.dto.FavorPlaceDto;
import com.example.goodPlace.favorplace.service.FavorPlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class ApiController {

    private final FavorPlaceService favorPlaceService;

    @GetMapping("/search")
    public FavorPlaceDto search(@RequestParam String query) {
        return favorPlaceService.search(query);
    }

    @PostMapping("")
    public FavorPlaceDto add(@RequestBody FavorPlaceDto favorPlaceDto) {
        log.info("DTO={}", favorPlaceDto);
        return favorPlaceService.add(favorPlaceDto);
    }

    @GetMapping("/all")
    public List<FavorPlaceDto> findAll() {
        return favorPlaceService.findAll();
    }

    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index){
        favorPlaceService.delete(index);
    }

    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index) {
        favorPlaceService.addVisit(index);
    }
}
