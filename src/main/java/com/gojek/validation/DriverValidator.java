package com.gojeck.validation;

import com.gojeck.api.Driver;
import com.gojeck.util.LocationUtil;
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
       /* if(driver.getId()<1 || driver.getId()>50000){
            violationMessage.append("Not Found. Invalid Driver ID.\n");
            return false;
        }else */if(!isValidLatitude(driver.getLatitude())){
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
