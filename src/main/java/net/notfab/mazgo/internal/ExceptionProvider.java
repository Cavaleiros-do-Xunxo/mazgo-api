package net.notfab.mazgo.internal;

import net.notfab.mazgo.utils.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionProvider implements ExceptionMapper<Exception> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Response toResponse(Exception exception) {
        logger.error("Internal server error", exception);
        return RestUtils.error(500, "Unknown server error");
    }

}
