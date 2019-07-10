import dao.DepartmentDao;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUsersDao;
import models.DB;
import models.Department;
import models.News;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import com.google.gson.Gson;
import exceptions.ApiException;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));

        } else {
            port = 4567;
        }
        port(port);
        staticFileLocation("/public");

//        Sql2oNewsDao newsDao = new Sql2oNewsDao(DB.sql2o);
//        Sql2oUsersDao usersDao = new Sql2oUsersDao(DB.sql2o);
//        Sql2oDepartmentDao departmentDao = new Sql2oDepartmentDao(DB.sql2o);
        DepartmentDao departmentDao;
        Sql2oUsersDao usersDao;
        Sql2oNewsDao newsDao;
        Connection conn;
       Gson gson = new Gson();

       String connectionString = "jdbc:postgresql://ec2-54-235-92-43.compute-1.amazonaws.com:5432/d6sjreg0noafos";

        Sql2o sql2o = new Sql2o(connectionString, "qkmgrzytbocygi", "fe80bf94d95498ef3453f331016c72f2a79c2f5c95e78c11e325176356f62b82");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        conn = sql2o.open();



//        //String connectionString = "jdbc:postgresql://localhost:5432/organisational_news_api";
//        String connectionString = "jdbc:postgresql://ec2-107-21-120-104.compute-1.amazonaws.com:5432/dd882ffb4uqm12";
////        Sql2o String connectionString = "jdbc:postgresql://localhost:5432/organisational_news_api";
//        Sql2o sql2o = new Sql2o(connectionString, "xkddftxsfraqws", "5f5d3259bc8ebb3621205bc08f55d9ecf0df2c958d5f2aef0253555a9c22f619");
//        departmentDao = new Sql2oDepartmentDao(sql2o);
//        userDao = new Sql2oUserDao(sql2o);
//        newsDao = new Sql2oNewsDao(sql2o);
//        conn = sql2o.open();
//


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());


        //CREATE
        post("/departments/:departmentId/news/:newsId", "application/json", (req, res) -> {

            int departmentId = Integer.parseInt(req.params("departmentId"));
            int newsId = Integer.parseInt(req.params("newsId"));
            Department department = departmentDao.findById(departmentId);
            News news = newsDao.findById(newsId);


            if (department != null && news != null) {
                //both exist and can be associated
                newsDao.addNewsToDepartment(news, department);
                res.status(201);
                return gson.toJson(String.format("Department '%s' and News '%s' have been associated", news.getName(), department.getName()));
            } else {
                throw new ApiException(404, String.format("Department or News does not exist"));
            }
        });

        get("/departments/:id/news", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            } else if (departmentDao.getAllNewsByDepartment(departmentId).size() == 0) {
                return "{\"message\":\"I'm sorry, but no news are listed for this department.\"}";
            } else {
                return gson.toJson(departmentDao.getAllNewsByDepartment(departmentId));
            }
        });

        get("/news/:id/departments", "application/json", (req, res) -> {
            int newsId = Integer.parseInt(req.params("id"));
            News newsToFind = newsDao.findById(newsId);
            if (newsToFind == null) {
                throw new ApiException(404, String.format("No news with the id: \"%s\" exists", req.params("id")));
            } else if (newsDao.getAllDepartmentsForANews(newsId).size() == 0) {
                return "{\"message\":\"I'm sorry, but no departments are listed for this news.\"}";
            } else {
                return gson.toJson(newsDao.getAllDepartmentsForANews(newsId));
            }
        });

        post("/departments/:departmentId/users/new", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("departmentId"));
            Users users = gson.fromJson(req.body(), Users.class);

            users.setDepartmentId(departmentId);
            usersDao.add(users);
            res.status(201);
            res.type("application/json");
            return gson.toJson(users);
        });

        post("/news/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsDao.add(news);
            res.status(201);
            res.type("application/json");
            return gson.toJson(news);
        });

        //READ
        get("/departments", "application/json", (req, res) -> {
            System.out.println(departmentDao.getAll());

            if (departmentDao.getAll().size() > 0) {
                res.type("application/json");
                return gson.toJson(departmentDao.getAll());
            } else {
                return "{\"message\":\"I'm sorry, but no departments are currently listed in the database.\"}";
            }

        });

        get("/departments/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }
            res.type("application/json");
            return gson.toJson(departmentToFind);
        });

        get("/departments/:id/users", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));

            Department departmentToFind = departmentDao.findById(departmentId);
            List<Users> allUsers;

            if (departmentToFind == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }

            allUsers = usersDao.getAllUsersByDepartment(departmentId);
            res.type("application/json");
            return gson.toJson(allUsers);
        });

        get("/news", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(newsDao.getAll());
        });

        get("/users", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(usersDao.getAll());
        });
//        //FILTERS
//        exception(ApiException.class, (exc, req, res) -> {
//            ApiException err = (ApiException) exc;
//            Map<String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("status", err.getStatusCode());
//            jsonMap.put("errorMessage", err.getMessage());
//            res.type("application/json");
//            res.status(err.getStatusCode());
//            res.body(gson.toJson(jsonMap));
//        });


        //CREATE
        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);
            res.type("application/json");
            return gson.toJson(department);
        });



//handlebars routes
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());


        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "form.hbs");
        },new HandlebarsTemplateEngine());

        get("/form2", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "form2.hbs");
        },new HandlebarsTemplateEngine());

        get("/form3", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "form3.hbs");
        },new HandlebarsTemplateEngine());

        post("/new/news", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            News newNews = new News(name);
            newsDao.add(newNews);
            return new ModelAndView(model, "view.hbs");
        }, new HandlebarsTemplateEngine());


        post("/new/users", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String duties = req.queryParams("duties");
            String position = req.queryParams("position") ;
            int departmentId = Integer.parseInt(req.queryParams("departmentId"));
            Users newUsers = new Users(name, duties,position,departmentId);
            usersDao.add(newUsers);
            return new ModelAndView(model, "view.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new/department",(req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name") ;
            String description = req.queryParams("description") ;
            int numberOfEmployees = Integer.parseInt(req.queryParams("numberOfEmployees"));
            Department newDepartment = new Department(name, description, numberOfEmployees);
            departmentDao.add(newDepartment);
            return new ModelAndView(model, "view.hbs");
        }, new HandlebarsTemplateEngine());

    }
}


