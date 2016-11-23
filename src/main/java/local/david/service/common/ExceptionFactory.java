package local.david.service.common;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by [david] on 22.11.16.
 */
public class ExceptionFactory {

    public static WebApplicationException exception(Response.Status status) {
        return new WebApplicationException(Response
                .status(status)
                .entity(new Error(status.getReasonPhrase())).build());
    }

    public static WebApplicationException exception(Response.Status status,
                                                    Throwable cause) {
        return new WebApplicationException(
                cause,
                Response.status(status)
                        .entity(new Error(cause)).build());
    }

    public static WebApplicationException exception(Response.Status status,
                                                    String cause) {
        return new WebApplicationException(
                Response.status(status)
                        .entity(new Error(cause)).build());
    }

}