package com.umc.practice.global.ResponseType.exception.handler;

import com.umc.practice.global.ResponseType.code.BaseErrorCode;
import com.umc.practice.global.ResponseType.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode code) {
        super(code);
    }
}