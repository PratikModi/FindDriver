package com.gojeck;

import com.google.inject.PrivateModule;

/**
 * Created by Pratik on 21-01-2017.
 */
public abstract class ConfigurationServiceModule extends PrivateModule{

    private FindDriverConfiguration findDriverConfiguration;

    public FindDriverConfiguration getFindDriverConfiguration() {
        return findDriverConfiguration;
    }

    public void setFindDriverConfiguration(FindDriverConfiguration findDriverConfiguration) {
        this.findDriverConfiguration = findDriverConfiguration;
    }
}
