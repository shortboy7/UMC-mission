package com.umc.practice.validator;

import com.umc.practice.global.ResponseType.code.status.ErrorStatus;
import com.umc.practice.global.ResponseType.exception.GeneralException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CheckPageValidator implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CheckPage.class) && parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String paramName = parameter.getParameterName();
        String paramValue = webRequest.getParameter(paramName);

        Integer value = Integer.valueOf(paramValue);
        if (value <= 0) {
            throw new GeneralException(ErrorStatus.PAGE_SHOULD_BE_POSITIVE);
        }
        return value - 1;
    }
}
