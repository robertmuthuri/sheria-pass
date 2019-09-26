package dao;

import models.Law;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oLawDao implements LawDao {

    private final Sql2o sql2o;
    public Sql2oLawDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Law law) {

        String sql = "INSERT INTO laws (law_citation,law_summary,law_url) VALUES (:law_citation,:law_summary,:law_url)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(law)
                    .executeUpdate()
                    .getKey();
            law.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Law> getAllLaws() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM laws")
                    .executeAndFetch(Law.class);
        }
    }

    @Override
    public Law findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM laws WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Law.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from laws WHERE id = :id";
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
        String sql = "DELETE from laws";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
