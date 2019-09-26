package dao;
import models.CaseLaw;
import models.Judge;

import java.util.List;

public interface JudgeDao {
    void add(Judge judge);

    //read
    List<Judge> getAllJudges();
    Judge findById(int id);
    List<Judge> getCaseByJudgeId(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
