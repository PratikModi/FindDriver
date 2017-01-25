package com.gojek.validation;

/**
 * Created by Pratik on 22-01-2017.
 */
public interface Validator<T> {

    boolean validate(T t);

    boolean isValidLatitude(long latitude);

    boolean isValidLongitude(long longitude);

    String getValidationMessage();
}
