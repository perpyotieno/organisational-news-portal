package dao;

import models.Department;
import models.News;

import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import dao.Sql2oNewsDao;
import dao.Sql2oDepartmentDao;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private static Connection conn;
    private static Sql2oNewsDao newsDao;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oUsersDao usersDao;

//    @Rule
//    public DatabaseRule database = new DatabaseRule();


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_api_test";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "Bus-242-001/2014");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
       departmentDao.clearAll();
        usersDao.clearAll();
       newsDao.clearAll();
        System.out.println("clearing database");
    }
    @AfterClass
    public static void shutDown() throws Exception{ //changed to static
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add() throws Exception{

            News testNews = setupNewNews();
            int originalNewsId = testNews.getId();
            newsDao.add(testNews);
            assertNotEquals(originalNewsId,testNews.getId());
        }

    @Test
    public void noNewsReturnsEmptyList() throws Exception{

            assertEquals(0, newsDao.getAll().size());
        }

    @Test
    public void addedNewsAreReturnedFromGetAll() throws Exception {
        News testNews = setupNewNews();
        newsDao.add(testNews);
        assertEquals(1, newsDao.getAll().size());
    }


    @Test
    public void deleteByIdDeletesCorrectNews() throws Exception{
            News news = setupNewNews();
            newsDao.add(news);
            newsDao.deleteById(news.getId());
            assertEquals(0, newsDao.getAll().size());
        }

    @Test
    public void clearAll() throws Exception{

            News testNews = setupNewNews();
            News otherNews = setupNewNews();
            newsDao.clearAll();
            assertEquals(0, newsDao.getAll().size());

        }
    @Test
    public void addFoodTypeToDepartmentAddsTypeCorrectly() throws Exception {

        Department testDepartment = setupDepartment();
        Department altDepartment = setupAltDepartment();

        departmentDao.add(testDepartment);
        departmentDao.add(altDepartment);

        News testNews = setupNewNews();

        newsDao.add(testNews);

        newsDao.addNewsToDepartment(testNews, testDepartment);
        newsDao.addNewsToDepartment(testNews, altDepartment);

        assertEquals(2, newsDao.getAllDepartmentsForANews(testNews.getId()).size());
    }

//    @Test
//    public void deletingDepartmentAlsoUpdatesJoinTable() throws Exception {
//        News testNews  = new News("Seafood");
//        newsDao.add(testNews);
//
//        Department testDepartment = setupDepartment();
//        departmentDao.add(testDepartment);
//
//        Department altDepartment = setupAltDepartment();
//        departmentDao.add(altDepartment);
//
//        departmentDao.addDepartmentToNews(testDepartment,testNews);
//        departmentDao.addDepartmentToNews(altDepartment, testNews);
//
//        departmentDao.deleteById(testDepartment.getId());
//        assertEquals(0, departmentDao.getAllNewssByDepartment(testDepartment.getId()).size());
//    }

    @Test
    public void deletingNewsAlsoUpdatesJoinTable() throws Exception {

        Department testDepartment = setupDepartment();

        departmentDao.add(testDepartment);

        News testNews = setupNewNews();
        News otherFoodType = new News("Japanese");

        newsDao.add(testNews);
        newsDao.add(otherFoodType);

        newsDao.addNewsToDepartment(testNews, testDepartment);
        newsDao.addNewsToDepartment(otherFoodType,testDepartment);

        newsDao.deleteById(testDepartment.getId());
        assertEquals(1, newsDao.getAllDepartmentsForANews(testNews.getId()).size());
    }


    // helpers

    public News setupNewNews(){
        return new News("Leave Notice");
    }
    public Department setupDepartment (){
        return new Department("Accounts", "Accounting biz", 23);
    }

    public Department setupAltDepartment (){
        return new Department("Accounts", "Accounting biz", 23);
    }
}












