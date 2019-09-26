package dao;
import models.CaseLaw;
import models.Judge;

import java.util.List;

public interface JudgeDao {
    void add(Judge judge);

    //read
    List<Judge> getAllJudges();
    Judge findById(int id);
<<<<<<< HEAD
    List<Judge> getCaseByJudgeId(int id);
=======
    List<CaseLaw> getCaseByForJudge( int judge_id);
>>>>>>> c5c3cb380e134d54c62e2af749e7fa6e7dbea768

    //delete
    void deleteById(int id);
    void clearAll();
}
