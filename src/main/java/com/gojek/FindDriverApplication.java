package com.gojeck;

import com.gojeck.exception.RuntimeExceptionMapper;
import com.gojeck.resource.DriverResource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pratik on 21-01-2017.
 */
public class FindDriverApplication extends Application<FindDriverConfiguration>{

    public static void main(final String[] args) throws Exception {
        new FindDriverApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<FindDriverConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<FindDriverConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(FindDriverConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public String getName() {
        return "Where Is My Driver";
    }

    @Override
    public void run(FindDriverConfiguration configuration, Environment environment) throws Exception {

        List<Module> moduleList = new ArrayList<Module>();
        moduleList.add(new FindDriveModule(configuration,environment));

        final Injector injector = Guice.createInjector(Stage.PRODUCTION,moduleList);
        environment.jersey().register(injector.getInstance(DriverResource.class));
        environment.jersey().register(RuntimeExceptionMapper.class);
        environment.jersey().register(MultiPartFeature.class);

    }
}
