package dao;

import models.*;
import org.sql2o.*;
import java.util.*;

import org.sql2o.Sql2o;

import javax.print.DocFlavor;

public class Sql2oCaselawDao implements CaselawDao {

    private final Sql2o sql2o;
    public Sql2oCaselawDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(CaseLaw caselaw) {
        String sql = "INSERT INTO caselaws (case_citation, case_summary, case_court, case_holding, case_url) VALUES (:case_citation, :case_summary, :case_court, :case_holding, :case_url)";
        try ( Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(caselaw).executeUpdate().getKey();
                    caselaw.setId(id);
        } catch (Sql2oException ex) { System.out.println(ex); }
    }
    // Add CaseLaw to party
    @Override
    public void addCaseLawToParty(CaseLaw caselaw, Party party) {
        String sql = "INSERT INTO caselaws_parties (case_id, party_id) VALUES (:case_id,:party_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("case_id", caselaw.getId())
                    .addParameter("party_id", party.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) { System.out.println(ex); }
    }
    //    add CaseLaw to judge
    @Override
    public void addCaseLawToJudge(CaseLaw caselaw, Judge judge) {
        String sql = "INSERT INTO caselaws_parties (case_id, judge_id) VALUES (:case_id,:judge_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("case_id", caselaw.getId())
                    .addParameter("judge_id", judge.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) { System.out.println(ex); }
    }

    // find by Id
    @Override
    public CaseLaw findById(int id) {
        String sql = "SELECT * FROM caselaws WHERE id = :id";
        try ( Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(CaseLaw.class);
        }
    }
    // List all cases
    @Override
    public List<CaseLaw> getAll() {
        String sql = "SELECT * FROM caselaws";
        try ( Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(CaseLaw.class);
        }
    }
    // delete by id - also deletes in join tables
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM caselaws WHERE id = :id";
        String joinSql = "DELETE FROM caselaws_parties";
        String joinOtherSql = "DELETE FROM caselaws_judges";
        try ( Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("id",id).executeUpdate();
            con.createQuery(joinSql).addParameter("id",id).executeUpdate();
            con.createQuery(joinOtherSql).addParameter("id",id).executeUpdate();
        } catch (Sql2oException ex) { System.out.println(ex); }

}
    // delete all
    @Override
    public void clearAll() {
        String sql = "DELETE FROM caselaws";
        String joinSql = "DELETE FROM caselaws_parties";
        String joinOtherSql = "DELETE FROM caselaws_judges";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
            con.createQuery(joinSql).executeUpdate();
            con.createQuery(joinOtherSql).executeUpdate();
        } catch (Sql2oException ex) { System.out.println(ex); }
    }
}
