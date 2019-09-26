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
        String sql = "INSERT INTO judges (name) VALUES (:name)";
        try(Connection con = sql2o.open()){
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
    public void addJudgeToCase(Judge judge, CaseLaw caseLaw) {

    }

    @Override
    public List<Judge> getAllJudges() {
        return null;
    }

    @Override
    public Judge findById() {
        try(Connection con = sql2o.open()){
            return (Judge) con.createQuery("SELECT * FROM parties WHERE type=judge")
                    .executeAndFetch(Judge.class);
        }
    }

    @Override
    public List<CaseLaw> getCaseByJudgeId() {
        return null;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM judges WHERE id = :id";
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
        String sql = "DELETE FROM judges";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
