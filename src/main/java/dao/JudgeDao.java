package dao;
import models.CaseLaw;
import models.Judge;
import models.Respondent;

import java.util.List;

public interface JudgeDao {
    void add(Judge judge);
    void addJudgeToCase(Judge judge, CaseLaw caseLaw);

    //read
    List<Judge> getAllJudges();
    Judge findById();
    List<CaseLaw> getCaseByForJudge( int judge_id);

    //delete
    void deleteById(int id);
    void clearAll();
}
