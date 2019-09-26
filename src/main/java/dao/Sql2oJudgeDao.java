package dao;

import models.CaseLaw;
import models.Judge;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oJudgeDao implements JudgeDao{
    private final Sql2o sql2o;
    public Sql2oJudgeDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Judge judge) {
        String sql = "INSERT INTO judges (judge_name,judge_rank) VALUES (:judge_name,:judge_rank)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(judge)
                    .executeUpdate()
                    .getKey();
            judge.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public List<Judge> getAllJudges() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM judge")
                    .executeAndFetch(Judge.class);
        }
    }

    @Override
    public Judge findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Judge.class);
        }
    }


    @Override
    public List<Judge> getCaseByJudgeId(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM employees WHERE id=:id")
                    .executeAndFetch(Judge.class);
        }
    }

    @Override
    public void deleteById(int id) {

        String sql = "DELETE from judges WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {

        String sql = "DELETE from judges";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
