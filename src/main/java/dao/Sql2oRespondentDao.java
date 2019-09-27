package dao;

import models.CaseLaw;
import models.Respondent;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oRespondentDao implements RespondentDao {

    private final Sql2o sql2o;
    public Sql2oRespondentDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Respondent respondent) {
        String sql = "INSERT INTO parties (name,type)    VALUES (:name, :type)";
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

        String sql ="INSERT INTO caselaws_parties (case_id,party_id) VALUES (:case_id,:party_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("case_id", caseLaw.getId())
                    .addParameter("party_id", respondent.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Respondent> getAllRespondent() {
            try(Connection con = sql2o.open()){
                return con.createQuery("SELECT * FROM parties WHERE type='respondent'")
                        .executeAndFetch(Respondent.class);
            }
    }

    @Override
    public Respondent findById( int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM parties WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Respondent.class);
        }
    }

    @Override
    public List<CaseLaw> getAllCasesForResponded(int party_id) {
        List<CaseLaw> caseLaws = new ArrayList();
        String joinQuery = "SELECT case_id FROM caselaws_parties WHERE party_id = :party_id";

        try (Connection con = sql2o.open()) {
            List<Integer> allCaseLawIds = con.createQuery(joinQuery)
                    .addParameter("party_id",party_id)
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
        String sql = "DELETE from parties WHERE id = :id";
        String deleteJoin = "DELETE from caselaws_parties WHERE party_id = :party_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("party_id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        }

    @Override
    public void clearAll() {
        String sql = "DELETE from parties";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
