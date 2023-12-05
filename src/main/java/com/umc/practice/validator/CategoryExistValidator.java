package com.umc.practice.validator;

import com.umc.practice.domain.FoodCategory;
import com.umc.practice.global.ResponseType.code.status.ErrorStatus;
import com.umc.practice.repository.FoodCategoryRepository;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryExistValidator implements
        ConstraintValidator<ExistCategories, List<Long>> {
    private final FoodCategoryRepository foodCategoryRepository;
    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        List<FoodCategory> categories = foodCategoryRepository.findAllById(values);
        boolean isValid = (categories.size() == values.size());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.NO_SUCH_FOOD_CATEGORY.toString())
                    .addConstraintViolation();
        }
        return isValid;
    }
}