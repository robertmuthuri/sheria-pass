package dao;

import models.CaseLaw;
import models.Judge;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
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
        String sql ="INSERT INTO caselaws_judges (case_id,judge_id) VALUES (:case_id,:judge_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("case_id", caseLaw.getId())
                    .addParameter("party_id", judge.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Judge> getAllJudges() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM judges")
                    .executeAndFetch(Judge.class);
        }
    }

    @Override
    public Judge findById() {
        try(Connection con = sql2o.open()){
            return (Judge) con.createQuery("SELECT * FROM parties WHERE type=judge")
                    .executeAndFetch(Judge.class);
        }
    }

    @Override
    public List<CaseLaw> getCaseByForJudge(int judge_id) {
        List<CaseLaw> caseLaws = new ArrayList();
        String joinQuery = "SELECT case_id FROM caselaws_judges WHERE judge_id = :judge_id";

        try (Connection con = sql2o.open()) {
            List<Integer> allCaseLawIds = con.createQuery(joinQuery)
                    .addParameter("judge_id", judge_id)
                    .executeAndFetch(Integer.class);
            for (Integer case_id : allCaseLawIds){
                String caseLawQuery = "SELECT * FROM caselaws WHERE id = :case_id";
                caseLaws.add(
                        con.createQuery(caseLawQuery)
                                .addParameter("case_id", case_id)
                                .executeAndFetchFirst(CaseLaw.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return caseLaws;
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM judges WHERE id = :id";
        String deleteJoin = "DELETE from caselaws_judges WHERE judge_id = :judge_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("judge_id", id)
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
