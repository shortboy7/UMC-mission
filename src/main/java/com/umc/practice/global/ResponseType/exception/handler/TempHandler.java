package com.umc.practice.global.ResponseType.exception.handler;

import com.umc.practice.global.ResponseType.code.BaseErrorCode;
import com.umc.practice.global.ResponseType.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
