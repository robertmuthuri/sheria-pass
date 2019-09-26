package models;

import org.sql2o.*;

import java.util.List;
import java.util.Objects;

public class Judge {
    private int id;
    private String judge_name;


    public Judge(String name) {
        this.judge_name = judge_name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Judge judge = (Judge) o;
        return getId() == judge.getId() &&
                Objects.equals(getName(), judge.getName());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return judge_name;
    }

    public void setName(String name) {
        this.judge_name = judge_name;
    }



}
