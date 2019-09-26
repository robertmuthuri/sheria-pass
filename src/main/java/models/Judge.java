package models;

import org.sql2o.*;

import java.util.List;
import java.util.Objects;

public class Judge {
    private int id;
    private String judge_name;
    private String judge_rank;


    public Judge(String judge_name, String judge_rank) {
        this.judge_name = judge_name;
        this.judge_rank = judge_rank;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Judge judge = (Judge) o;
        return getId() == judge.getId() &&
                Objects.equals(getJudge_name(), judge.getJudge_name()) &&
                Objects.equals(getJudge_rank(), judge.getJudge_rank());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getJudge_name(), getJudge_rank());
    }

    public String getJudge_name() {
        return judge_name;
    }

    public void setJudge_name(String judge_name) {
        this.judge_name = judge_name;
    }

    public String getJudge_rank() {
        return judge_rank;
    }

    public void setJudge_rank(String judge_rank) {
        this.judge_rank = judge_rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





}
