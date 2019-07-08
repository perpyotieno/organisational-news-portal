package dao;

import models.Department;
import models.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oUsersDaoTest {
    private Connection conn;
    private Sql2oUsersDao usersDao;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "Bus-242-001/2014");
        usersDao = new Sql2oUsersDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
    }

    @Test
    public void getAll() throws Exception{
        Users users1 = setupUsers();
        Users users2 = setupUsers();
        assertEquals(2, usersDao.getAll().size());
    }

    @Test
    public void getAllUsersByDepartment() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void clearAll() {
    }

    //helper

    public Users setupUsers() {
        Users users = new Users("perpy", "cleaning", "subordinate staff", 2);
        usersDao.add(users);
        return users;
    }

    public Users setupUsersForDepartment(Department department) {
        Users users = new Users("perpy", "cleaning", "subordinate staff", department.getId());
        usersDao.add(users);
        return users;
    }

    public Department setupDepartment() {
        Department department = new Department("Finance", "handle finance issues", 20);
        departmentDao.add(department);
        return department;
    }
}