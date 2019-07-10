package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Users implements  Comparable<Users> {
    private String name;
    private  String duties;
    private String position;
    private int id;
    private int departmentId; //will be used to connect Department to Users (one-to-many)
    private long createdat;
    private String formattedCreatedAt;


    public Users(String name, String duties, String position, int departmentId){
        this.name = name;
        this.duties = duties;
        this.position =position;
        this.departmentId = departmentId;
        this.createdat = System.currentTimeMillis();
        setFormattedCreatedAt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return duties;
    }

    public void setRole(String duties) {
        this.duties = duties;
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

    @Override
    public int compareTo(Users usersObject) {
        if (this.createdat < usersObject.createdat)
        {
            return -1; //this object was made earlier than the second object.
        }
        else if (this.createdat > usersObject.createdat){ //this object was made later than the second object
            return 1;
        }
        else {
            return 0; //they were made at the same time, which is very unlikely, but mathematically not impossible.
        }
    }
    public long getCreatedat() {
        return createdat;
    }
    public void setCreatedat() {
        this.createdat = System.currentTimeMillis();

    }

    public String getFormattedCreatedAt(){
        Date date = new Date(createdat);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a"; //see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        return sdf.format(date);
    }
    public void setFormattedCreatedAt(){
        Date date = new Date(this.createdat);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        this.formattedCreatedAt = sdf.format(date);
    }


}
