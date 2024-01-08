package com.guin.team.adapter.in.web.validator;

import com.guin.team.adapter.in.web.validator.annotation.HashTag;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Objects;

public class HashTagValidator implements ConstraintValidator<HashTag, List<String>> {

    private static final int MAX_SIZE = 5;
    private static final int HASH_TAG_MAX_LENGTH = 10;

    @Override
    public boolean isValid(List<String> hashTags, ConstraintValidatorContext constraintValidatorContext) {
        if (hashTags == null) {
            return true;
        }

        if (checkHashTagSize(hashTags)) {
            return false;
        }

        if (checkLength(hashTags)) {
            return true;
        }

        return false;
    }

    private boolean checkHashTagSize(final List<String> hashTags) {
        return hashTags.size() > MAX_SIZE;
    }

    private boolean checkLength(final List<String> hashTags) {
        return hashTags.stream()
                .filter(HashTagValidator::isMaxLength)
                .findFirst()
                .isEmpty();
    }

    private static boolean isMaxLength(final String hashTag) {
        return hashTag.length() > HASH_TAG_MAX_LENGTH;
    }

}
