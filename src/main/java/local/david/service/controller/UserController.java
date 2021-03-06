package local.david.service.controller;

import local.david.service.common.AbstractController;
import local.david.service.common.AbstractDAO;
import local.david.service.model.dao.UserDAO;
import local.david.service.model.pojo.User;
import local.david.service.service.ContextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by [david] on 22.11.16.
 */
@Service("userController")
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController extends AbstractController<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ContextService ctx;

    public UserController() {
        super(User.class);
    }

    @Override
    public AbstractDAO<User> getDao() {
        return userDAO;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected User getCurentUser() {
        return ctx.getCurrentUser();
    }
}
