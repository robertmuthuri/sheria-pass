package dao;

import models.CaseLaw;
import models.Respondent;

import java.util.List;

public interface RespondentDao {
    void add(Respondent respondent);
    void addRespondentToCase(Respondent respondent, CaseLaw caseLaw);

    //read
    List<Respondent> getAllRespondent();
    Respondent findById();
    List<CaseLaw> getCaseByRespondentId();

    //delete
    void deleteById(int id);
    void clearAll();
}
