package net.notfab.mazgo.internal;

import net.notfab.mazgo.utils.RestUtils;
import org.json.JSONException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JSONExceptionProvider implements ExceptionMapper<JSONException> {

    @Override
    public Response toResponse(JSONException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(RestUtils.error(400, exception.getMessage()))
                .build();
    }

}
