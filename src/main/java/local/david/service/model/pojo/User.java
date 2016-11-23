package local.david.service.model.pojo;

import local.david.service.common.Entity;

import java.util.Set;

/**
 * Created by [david] on 22.11.16.
 */
public class User extends Entity {
    private String pass;
    private Set<String> roles;
    private String name;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

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
}
