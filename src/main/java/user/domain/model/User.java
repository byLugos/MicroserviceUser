package user.domain.model;

import java.time.LocalDate;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private String cc;
    private LocalDate birthDate;
    private String email;
    private String password;
    private String phone;
    private Role role;
    public User(Long id, String name, String lastName, String cc, LocalDate birthDate, String email, String password, String phone, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cc = cc;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }
    public User() {
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public String getCc() {
        return cc;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getPhone() {
        return phone;
    }
    public Role getRole() {
        return role;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setCc(String cc) {
        this.cc = cc;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
