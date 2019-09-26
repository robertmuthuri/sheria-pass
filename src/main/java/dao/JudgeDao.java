package dao;
import models.CaseLaw;
import models.Judge;

import java.util.List;

public interface JudgeDao {
    void add(Judge judge);

    //read
    List<Judge> getAllJudges();
    Judge findById(int id);

    List<CaseLaw> getCaseByForJudge( int judge_id);

    //delete
    void deleteById(int id);
    void clearAll();
}
