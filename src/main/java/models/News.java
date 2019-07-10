package models;

import java.util.Objects;

public class News {
    private String name;
    private int id;

    public News(String name){
        this.name =name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return id == news.id &&
                Objects.equals(name, news.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
