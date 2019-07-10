package dao;

import models.Department;
import models.News;

import java.util.List;

public interface DepartmentDao {
    //create
    void add (Department department);
    void addDepartmentToNews(Department department, News news);

    //read
    List<Department> getAll();
    Department findById(int id);
    List<News> getAllNewsByDepartment(int departmentId);

    //update
    void update(int id, String name, String description, int numberOfEmployees);

    //delete
    void deleteById(int id);
    void clearAll();
}
