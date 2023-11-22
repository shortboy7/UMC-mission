package com.umc.practice.global.ResponseType.exception;

import com.umc.practice.global.ResponseType.code.BaseErrorCode;
import com.umc.practice.global.ResponseType.code.ErrorReasonDTO;

public class GeneralException extends RuntimeException{
    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
