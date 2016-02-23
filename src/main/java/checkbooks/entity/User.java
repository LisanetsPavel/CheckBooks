package checkbooks.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pc8 on 12.09.15.
 */

public class User {

    private String username;
    private String password;

    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +

                ", userRole=" + userRole +
                '}';
    }
}
