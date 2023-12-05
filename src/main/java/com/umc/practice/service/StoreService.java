package com.umc.practice.service;

import com.umc.practice.converter.StoreConverter;
import com.umc.practice.domain.Store;
import com.umc.practice.dto.StoreRequestDTO;
import com.umc.practice.dto.StoreResponseDTO;
import com.umc.practice.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreResponseDTO.NewStore enrollNewStore(StoreRequestDTO.NewStore req) {
        Store store = this.storeRepository
                .save(StoreConverter
                        .toStoreEntity(req));
        return new StoreResponseDTO.NewStore(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getScore()
        );
    }
}
