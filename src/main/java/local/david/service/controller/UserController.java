package local.david.service.controller;

import local.david.service.model.User;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by [david] on 22.11.16.
 */
@Service("userController")
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @GET
    @Path("/")
    public Object getById() {
        return new User();
    }

}
