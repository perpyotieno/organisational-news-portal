package models;

import java.util.Objects;

public class Users {
    private String name;
    private  String role;
    private String position;
    private int id;
    private int departmentId; //will be used to connect Department to Users (one-to-many)


    public Users(String name, String role, String position, int departmentId){
        this.name = name;
        this.role = role;
        this.position =position;
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getId() == users.getId() &&
                getDepartmentId() == users.getDepartmentId() &&
                getName().equals(users.getName()) &&
                getRole().equals(users.getRole()) &&
                getPosition().equals(users.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRole(), getPosition(), getId(), getDepartmentId());
    }
}
