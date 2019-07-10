package models;
import org.sql2o.Sql2o;

public class DB {
   //public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/news_api", "postgres", "Bus-242-001/2014");
   public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-54-235-92-43.compute-1.amazonaws.com:5432/d6sjreg0noafos", "qkmgrzytbocygi", "fe80bf94d95498ef3453f331016c72f2a79c2f5c95e78c11e325176356f62b82");
}
