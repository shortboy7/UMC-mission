package com.umc.practice.controller;

import com.umc.practice.dto.StoreRequestDTO;
import com.umc.practice.dto.StoreResponseDTO;
import com.umc.practice.dto.StoreResponseDTO.NewMission;
import com.umc.practice.dto.StoreResponseDTO.NewReview;
import com.umc.practice.dto.StoreResponseDTO.NewStore;
import com.umc.practice.service.StoreService;
import com.umc.practice.validator.ExistStore;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    @PostMapping("/{storeId}/review")
    public ResponseEntity<StoreResponseDTO.NewReview> newReview(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid StoreRequestDTO.NewStoreReview request) {
        NewReview newReview = this.storeService.enrollReview(storeId, request);
        return ResponseEntity.ok(newReview);
    }
    @PostMapping("/{storeId}/mission")
    public ResponseEntity<StoreResponseDTO.NewMission> newMission(
            @ExistStore @PathVariable("storeId") Long storeId,
            @RequestBody @Valid StoreRequestDTO.NewStoreMission request) {
        NewMission newMission = this.storeService.enrollMission(storeId, request);
        return ResponseEntity.ok(newMission);
    }
}
