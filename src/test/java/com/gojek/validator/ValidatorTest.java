package com.gojek.validator;

import com.gojek.api.Driver;
import com.gojek.validation.DriverValidator;
import com.gojek.validation.Validator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Pratik on 25-01-2017.
 */
public class ValidatorTest {

    Validator<Driver> validator = new DriverValidator();

    @Test
    public void isValidLatitudeTest(){
        boolean result = validator.isValidLatitude(90l);
        Assert.assertEquals(true,result);
    }

    @Test
    public void isValidLongitudeTest(){
        boolean result = validator.isValidLatitude(90l);
        Assert.assertEquals(true,result);
    }

    @Test
    public void validateTest(){
        Driver driver = new Driver(1, 90l,90l);
        boolean result = validator.validate(driver);
        Assert.assertEquals(true,result);
    }

    @Test
    public void validateWrongDriverTest(){
        Driver driver = new Driver(1, 91l,90l);
        boolean result = validator.validate(driver);
        Assert.assertEquals(false,result);
    }

    @Test
    public void validateWrongLatitudeTest(){
        Driver driver = new Driver(1, 91l,90l);
        boolean result = validator.validate(driver);
        Assert.assertEquals(false,result);
    }

    @Test
    public void validateWrongLongitudeTest(){
        Driver driver = new Driver(1, 90l,190l);
        boolean result = validator.validate(driver);
        Assert.assertEquals(false,result);
    }

}
