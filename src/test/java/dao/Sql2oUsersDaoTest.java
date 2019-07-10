//package dao;
//
//import models.Department;
//import models.Users;
//import org.junit.*;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;
//
//import static org.junit.Assert.*;
//
//public class Sql2oUsersDaoTest {
//    private static Connection conn;
//    private static Sql2oUsersDao usersDao;
//    private static Sql2oDepartmentDao departmentDao;
//    private static Sql2oNewsDao newsDao;
//
////    @Rule
////    public DatabaseRule database = new DatabaseRule();
//
//    @BeforeClass
//    public static void setUp() throws Exception {
//        String connectionString = "jdbc:postgresql://localhost:5432/news_api_test";
//        Sql2o sql2o = new Sql2o(connectionString, "postgres", "Bus-242-001/2014");
//        departmentDao = new Sql2oDepartmentDao(sql2o);
//        newsDao = new Sql2oNewsDao(sql2o);
//        usersDao = new Sql2oUsersDao(sql2o);
//        conn = sql2o.open();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        departmentDao.clearAll();
////        usersDao.clearAll();
//       newsDao.clearAll();
//        System.out.println("clearing database");
//    }
//    @AfterClass
//    public static void shutDown() throws Exception{ //changed to static
//        conn.close();
//        System.out.println("connection closed");
//    }
//    @Test
//    public void addingUsersSetsId() throws Exception {
//        Users testUser = setupNewUsers();
//        int originalUsersId = testUser.getId();
//        usersDao.add(testUser);
////        Users testUsers = new Users("Captain Kirk", "sing", "foodcoma!",testDepartment.getId());
//
//
//        assertEquals(originalUsersId,testUser.getId());
//    }
//
//
//
////    @Test
////    public void getAll() throws Exception{
////        Users users1 = setupNewUsers();
////        usersDao.add(users1);
////        Users users2 = setupNewUsers();
////        usersDao.add(users2);
////        assertEquals(1, usersDao.getAll().size());
//////        assertTrue(usersDao.getAll().contains(users2));
////    }
//
////    @Test
////    public void getAllUsersByDepartment() throws Exception {
////        Department testDepartment = setupDepartment();
////        Department otherDepartment = setupDepartment();
////        Users users1 = setupUsersForDepartment(testDepartment);
////        Users users2 = setupUsersForDepartment(testDepartment);
////        Users usersForOtherDepartment = setupUsersForDepartment(otherDepartment);
////        assertEquals(2, usersDao.getAllUsersByDepartment(testDepartment.getId()).size());
////    }
//
////    @Test
////        public void deleteById() throws Exception {
////            Users testUsers = setupUsers();
////            Users otherUsers = setupUsers();
////            assertEquals(2, usersDao.getAll().size());
////            usersDao.deleteById(testUsers.getId());
////            assertEquals(1, usersDao.getAll().size());
////    }
//
//        @Test
//        public void clearAll() throws Exception {
//            Users testUsers = setupUsers();
//            Users otherUsers = setupUsers();
//            usersDao.clearAll();
//            assertEquals(0, usersDao.getAll().size());
//        }
////    @Test
////    public void timeStampIsReturnedCorrectly() throws Exception {
////        Department testDepartment = setupDepartment();
////        departmentDao.add(testDepartment);
////        Users testUsers = new Users("Captain Kirk", "meee","foodcoma!", testDepartment.getId());
////        usersDao.add(testUsers);
////
////        long creationTime = testUsers.getCreatedat();
////        long savedTime = usersDao.getAll().get(0).getCreatedat();
////        String formattedCreationTime = testUsers.getFormattedCreatedAt();
////        String formattedSavedTime = usersDao.getAll().get(0).getFormattedCreatedAt();
////        assertEquals(formattedCreationTime,formattedSavedTime);
////        assertEquals(creationTime, savedTime);
////    }
//
////    @Test
////    public void usersAreReturnedInCorrectOrder() throws Exception {
////        Department testDepartment = setupDepartment();
////        departmentDao.add(testDepartment);
////        Users testUsers = new Users("Captain Kirk", "meee", "foodcoma!", testDepartment.getId());
////        usersDao.add(testUsers);
////        try {
////            Thread.sleep(2000);
////        }
////        catch (InterruptedException ex){
////            ex.printStackTrace();
////        }
////
////        Users testSecondUsers = new Users("Mr. Spock", "mee ", "passable", testDepartment.getId());
////        usersDao.add(testSecondUsers);
////
////        try {
////            Thread.sleep(2000);
////        }
////        catch (InterruptedException ex){
////            ex.printStackTrace();
////        }
////
////        Users testThirdUsers = new Users("Scotty", "mee", "bloody good grub!", testDepartment.getId());
////        usersDao.add(testThirdUsers);
////
////        try {
////            Thread.sleep(2000);
////        }
////        catch (InterruptedException ex){
////            ex.printStackTrace();
////        }
////
////        Users testFourthUsers = new Users("Mr. Sulu", "meee", "I prefer home cooking", testDepartment.getId());
////        usersDao.add(testFourthUsers);
////
////        assertEquals(4, usersDao.getAllUsersByDepartment(testDepartment.getId()).size()); //it is important we verify that the list is the same size.
////        assertEquals("I prefer home cooking", usersDao.getAllUsersByDepartmentSortedNewestToOldest(testDepartment.getId()).get(0).getContent());
////    }
//
//
//    //helper
//    public Users setupNewUsers() {
//        return new Users("perpy", "cleaning", "subordinate staff", 2);
//
//    }
//
//    public Users setupUsers() {
//        Users users = new Users("perpy", "cleaning", "subordinate staff", 2);
//        usersDao.add(users);
//        return users;
//    }
//
//    public Users setupUsersForDepartment(Department department) {
//        Users users = new Users("perpy", "cleaning", "subordinate staff", department.getId());
//        usersDao.add(users);
//        return users;
//    }
//
//    public Department setupDepartment() {
//        Department department = new Department("Finance", "handle finance issues", 20);
//        departmentDao.add(department);
//        return department;
//    }
//}