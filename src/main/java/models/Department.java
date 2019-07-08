package models;

import java.util.Objects;

public class Department {

    private String name;
    private String description;
    private int numberOfEmployees;
    private  int id;
    private int userId;

    public Department(String name, String description, int numberOfEmployees, int userId){
        this.name = name;
        this.description= description;
        this.numberOfEmployees= numberOfEmployees;
        this. userId = userId;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getNumberOfEmployees() == that.getNumberOfEmployees() &&
                getId() == that.getId() &&
                getUserId() == that.getUserId() &&
                getName().equals(that.getName()) &&
                getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getNumberOfEmployees(), getId(), getUserId());
    }
}
