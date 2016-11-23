package local.david.service.service;

import local.david.service.model.dao.UserDAO;
import local.david.service.model.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by [david] on 23.11.16.
 */
@Service
public class ContextService {
    private static final Logger logger = LoggerFactory.getLogger(ContextService.class);
    @Autowired
    private UserDAO userDAO;

    public User getCurrentUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userDAO.findById(principal.getUsername(), null);
    }

    public String getCurrentUserName() {
        return ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername();
    }
}
