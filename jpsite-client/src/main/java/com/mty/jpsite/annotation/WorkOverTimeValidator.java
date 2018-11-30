package com.mty.jpsite.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by jiangpeng on 2018/10/24.
 */
public class WorkOverTimeValidator implements ConstraintValidator<WorkOverTime, Integer> {
    WorkOverTime work;
    int max;

    @Override
    public void initialize(WorkOverTime work) {
        this.work = work;
        max = work.max();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (integer == null) {
            return true;
        }
        return integer < max;
    }
}
