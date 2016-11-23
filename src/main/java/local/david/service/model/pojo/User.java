package local.david.service.model.pojo;

import local.david.service.common.Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by [david] on 22.11.16.
 */
public class User extends Entity {
    private String name;
    private String password;
    private Set<String> roles = new HashSet<>();

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
