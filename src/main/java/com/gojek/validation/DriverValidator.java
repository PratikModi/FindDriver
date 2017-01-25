package com.gojek.validation;

import com.gojek.api.Driver;
import com.gojek.util.LocationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Pratik on 22-01-2017.
 */
public class DriverValidator implements Validator<Driver> {

    public StringBuffer violationMessage= new StringBuffer();

    private static final Logger log = LoggerFactory.getLogger(DriverValidator.class);

    @Override
    public boolean validate(Driver driver) {
       log.info("Validating Driver:-"+driver);
       if(!isValidLatitude(driver.getLatitude())){
            return false;
        }else if(!isValidLongitude(driver.getLongitude())){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public boolean isValidLatitude(long latitude){
        if(!LocationUtil.getInstance().isValidLatitude(latitude)) {
            violationMessage.append("Latitude should be between +/- 90");
            return false;
        }else
            return true;
    }

    @Override
    public boolean isValidLongitude(long longitude){
        if(!LocationUtil.getInstance().isValidLongitude(longitude)){
            violationMessage.append("Longitude should be between +/- 180");
            return false;
        }else{
            return true;
        }

    }

    @Override
    public String getValidationMessage() {
        return violationMessage.toString();
    }
}
