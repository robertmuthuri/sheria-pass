package dao;

import models.CaseLaw;
import models.InterestedParty;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oInterestedPartyDao implements InterestedPartyDao{

    private final Sql2o sql2o;
    public Sql2oInterestedPartyDao(Sql2o sql2o){

        this.sql2o = sql2o;
    }

    @Override
    public void add(InterestedParty interestedParty) {
        String sql = "INSERT INTO parties (name,type)    VALUES (:name, :type)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(interestedParty)
                    .executeUpdate()
                    .getKey();
            interestedParty.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void addPetitionerToCase(InterestedParty interestedParty, CaseLaw caseLaw) {

    }

    @Override
    public List<InterestedParty> getAllInterestedParties() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM parties WHERE type='interested party'")
                    .executeAndFetch(InterestedParty.class);
        }
    }

    @Override
    public InterestedParty findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM parties WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(InterestedParty.class);
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
