package dao;

import models.CaseLaw;
import models.Respondent;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oRespondentDao implements RespondentDao {

    private final Sql2o sql2o;
    public Sql2oRespondentDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Respondent respondent) {
        String sql = "INSERT INTO parties (name)    VALUES (:name)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(respondent)
                    .executeUpdate()
                    .getKey();
            respondent.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addRespondentToCase(Respondent respondent, CaseLaw caseLaw) {

    }

    @Override
    public List<Respondent> getAllRespondent() {
            try(Connection con = sql2o.open()){
                return con.createQuery("SELECT * FROM parties WHERE type=respondent")
                        .executeAndFetch(Respondent.class);
            }
    }

    @Override
    public Respondent findById() {
        return null;
    }

    @Override
    public List<CaseLaw> getCaseByRespondentId() {
        return null;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from respondents WHERE id = :id";
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
        String sql = "DELETE from respondents";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
