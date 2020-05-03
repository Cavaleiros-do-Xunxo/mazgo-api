package net.notfab.mazgo.spring;

import net.notfab.mazgo.internal.*;
import net.notfab.mazgo.rest.HistoryRest;
import net.notfab.mazgo.rest.ProductRest;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ProductRest.class);
        register(HistoryRest.class);
        // Error Handlers
        register(ExceptionProvider.class);
        register(IllegalArgumentProvider.class);
        register(JSONExceptionProvider.class);
        register(NotFoundProvider.class);
        register(NotAllowedProvider.class);
        // Spring
        setProperties(Collections.singletonMap("jersey.config.server.response.setStatusOverSendError", true));
    }

}
