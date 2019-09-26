package dao;

import models.CaseLaw;
import models.Judge;
import models.Party;

import java.util.List;

public interface CaselawDao {
    void add(CaseLaw caseLaw);
    void addCaseLawToParty(CaseLaw caseLaw, Party party);
    void addCaseLawToJudge(CaseLaw caseLaw, Judge judge);


    User findById(int id);

    List<CaseLaw> getAll();

    //update
    //omit for now

    void deleteById(int id);

    void clearAll();
}
