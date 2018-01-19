package springjpa.model;

import javax.persistence.*;

@Entity
@Table(name = "userss_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private long user_lode_id;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "role")
    private String role;

    public UserRole(String username, String role) {
        this.username = username;
        this.role = role;
    }

    protected UserRole(){}

    public long getUser_lode_id() {
        return user_lode_id;
    }

    public void setUser_lode_id(long user_lode_id) {
        this.user_lode_id = user_lode_id;
    }

    public String getLogin() {
        return username;
    }

    public void setLogin(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "UserRole{" +
                "user_lode_id=" + user_lode_id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
