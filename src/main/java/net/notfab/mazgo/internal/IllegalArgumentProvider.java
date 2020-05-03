package net.notfab.mazgo.internal;

import net.notfab.mazgo.utils.RestUtils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentProvider implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException exception) {
        return RestUtils.error(400, exception.getMessage());
    }

}
