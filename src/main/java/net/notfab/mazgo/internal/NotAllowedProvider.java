package net.notfab.mazgo.internal;

import net.notfab.mazgo.utils.RestUtils;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAllowedProvider implements ExceptionMapper<NotAllowedException> {

    @Override
    public Response toResponse(NotAllowedException exception) {
        return RestUtils.error(405, "Method not allowed");
    }

}
