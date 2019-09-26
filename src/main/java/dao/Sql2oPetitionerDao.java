package dao;

import models.CaseLaw;
import models.Petitioner;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oPetitionerDao implements PetitionerDao {

    private final Sql2o sql2o;
    public Sql2oPetitionerDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Petitioner petitioner) {
        String sql = "INSERT INTO parties (name,type)    VALUES (:name, :type)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(petitioner)
                    .executeUpdate()
                    .getKey();
            petitioner.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addPetitionerToCase(Petitioner petitioner, CaseLaw caseLaw) {

    }

    @Override
    public List<Petitioner> getAllPetioners() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM parties WHERE type='petitioner'")
                    .executeAndFetch(Petitioner.class);
        }
    }

    @Override
    public Petitioner findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM parties WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Petitioner.class);
        }
    }

    @Override
    public List<CaseLaw> getCaseByPetitionerId() {
        return null;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from parties WHERE id = :id";
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
        String sql = "DELETE from parties";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
