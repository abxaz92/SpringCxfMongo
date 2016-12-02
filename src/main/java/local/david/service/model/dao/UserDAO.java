package local.david.service.model.dao;

import local.david.service.common.AbstractDAO;
import local.david.service.model.pojo.User;
import org.springframework.stereotype.Service;

/**
 * Created by [david] on 23.11.16.
 */
@Service
public class UserDAO extends AbstractDAO<User> {
    public UserDAO() {
        super(User.class);
    }

    @Override
    public void prePut(User entity, String id) {

    }

    @Override
    protected void prepareEntityForSaving(User entity, User user) {

    }
}
