package com.umc.practice.controller;

import com.umc.practice.converter.StoreConverter;
import com.umc.practice.domain.Store;
import com.umc.practice.dto.StoreRequestDTO;
import com.umc.practice.dto.StoreResponseDTO;
import com.umc.practice.dto.StoreResponseDTO.NewStore;
import com.umc.practice.service.StoreService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/")
    public ResponseEntity<StoreResponseDTO.NewStore> newStore(
            @RequestBody @Valid StoreRequestDTO.NewStore request) {
        NewStore newStore = storeService.enrollNewStore(request);
        return ResponseEntity.ok(newStore);
    }
}
