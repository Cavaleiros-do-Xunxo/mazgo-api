package net.notfab.mazgo.internal;

import net.notfab.mazgo.utils.RestUtils;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundProvider implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        return RestUtils.error(404, "Not found");
    }

}
