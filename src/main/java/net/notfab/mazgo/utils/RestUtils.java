package net.notfab.mazgo.utils;

import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestUtils {

    public static Response error(int code, String message) {
        if (message == null) {
            message = "Unknown error.";
        }
        return Response.status(code)
                .type(MediaType.APPLICATION_JSON)
                .entity(new JSONObject()
                        .put("code", code)
                        .put("message", message).toString())
                .build();
    }

}
