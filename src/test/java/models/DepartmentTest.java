package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNameReturnsCorrectName() throws Exception {
        Department testDepartment= setupDepartment();
        assertEquals("finance", testDepartment.getName());
    }

    @Test
    public void setNameSetsCorrectName() throws Exception {
        Department testDepartment= setupDepartment();
        testDepartment.setName("finance");
        assertNotEquals("accounting", testDepartment.getName());
    }

    @Test
    public void getDescriptionReturnsCorrectDescription() throws Exception{
        Department testDepartment = setupDepartment();
        assertEquals("handle finance business", testDepartment.getDescription());
    }

    @Test
    public void setDescriptionSetsCorrectDescription() throws Exception {
        Department testDepartment= setupDepartment();
        testDepartment.setDescription("handle finance business");
        assertNotEquals("handle accounting business", testDepartment.getDescription());
    }

    @Test
    public void getNumberOfEmployeesReturnsCorrectNumberofEmployee() {
        Department testDepartment = setupDepartment();
        assertEquals(12, testDepartment.getNumberOfEmployees());
    }

    @Test
    public void setNumberOfEmployeesSetsCorrectNumber() {
        Department testDepartment= setupDepartment();
        testDepartment.setNumberOfEmployees(12);
        assertNotEquals(13, testDepartment.getNumberOfEmployees());
    }

    @Test
    public void getId() {
    }

    @Test
    public void setId() {

    }

    @Test
    public void equals1() {
    }

    @Test
    public void hashCode1() {
    }

    //helper
    public  Department setupDepartment(){
        return new Department("finance","handle finance business",12);
    }
}