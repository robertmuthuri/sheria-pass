package dao;

import models.Judge;
import models.CaseLaw;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oJudgeDaoTest {
    private Connection conn;
    private Sql2oJudgeDao judgeDao;
    private Sql2oCaselawDao caselawDao;

    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        judgeDao = new Sql2oJudgeDao(sql2o);
        caselawDao = new Sql2oCaselawDao(sql2o);
                conn = sql2o.open();

    }
    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {

    }

    @Test
    public void addJudgeToCase() {
        CaseLaw caseLaw = setupCaselaw();
        CaseLaw caseLaw1 = setupCaselaw();
        Judge judge = setUpJudge();
        judgeDao.addJudgeToCase(judge,caseLaw);
        judgeDao.addJudgeToCase(judge,caseLaw1);
        assertTrue(judgeDao.getCaseForJudge(judge.getId()).contains(caseLaw));
    }

    @Test
    public void getAllJudges() {
        Judge judge = setUpJudge();
        Judge judge1 = setUpJudge();
        assertEquals(2,judgeDao.getAllJudges().size());
    }

    @Test
    public void findById() {
        Judge judge = setUpJudge();
        assertEquals(judge,judgeDao.findById(judge.getId()));
    }

    @Test
    public void getCaseByForJudge() {
        CaseLaw caseLaw = setupCaselaw();
        CaseLaw caseLaw1 = setupCaselaw();
        Judge judge = setUpJudge();

        judgeDao.addJudgeToCase(judge,caseLaw);
        judgeDao.addJudgeToCase(judge,caseLaw1);
        assertEquals(2,judgeDao.getCaseForJudge(judge.getId()).size());
    }

    @Test
    public void deleteById() {
        Judge judge = setUpJudge();
        Judge judge1 = setUpJudge();
        assertEquals(2,judgeDao.getAllJudges().size());
        judgeDao.deleteById(judge.getId());
        assertEquals(1,judgeDao.getAllJudges().size());
    }

    @Test
    public void clearAll() {
        Judge judge = setUpJudge();
        Judge judge1 = setUpJudge();
        judgeDao.clearAll();
        assertEquals(0,judgeDao.getAllJudges().size());
    }

    //helper method
    public Judge setUpJudge (){
        Judge judge= new Judge("Robert","v bncvb");
        judgeDao.add(judge);
        return judge;
    }

    public CaseLaw setupCaselaw() {
        CaseLaw caseLaw= new CaseLaw("Raila Amolo Odinga & another v Independent Electoral and Boundaries Commission 2 others [2017] eKLR", "Validity of a presidential election-petition challenging the validity of the president elect-allegations of non-compliance with the Constitution and electoral laws - allegations of various  irregularities and illegalities during the conduct of the elections","Supreme Court of Kenya","(i) A declaration is hereby issued that the Presidential Election held on 8th August, 2017 was not conducted in accordance with the Constitution and the applicable law rendering the declared result invalid, null and void;","http://www.kenyalaw.org/kl/fileadmin/pdfdownloads/2017ElectionPetition/Presidential_Petition_1_of_2017.pdf");
        caselawDao.add(caseLaw);
        return  caseLaw;
    }
}