package com.umc.practice.service;

import com.umc.practice.global.ResponseType.code.status.ErrorStatus;
import com.umc.practice.global.ResponseType.exception.handler.TempHandler;
import org.springframework.stereotype.Service;

@Service
public class TempQueryService {
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}
