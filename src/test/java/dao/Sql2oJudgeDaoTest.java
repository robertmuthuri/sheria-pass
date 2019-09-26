package dao;

import models.Judge;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oJudgeDaoTest {
    private Connection conn;
    private Sql2oJudgeDao judgeDao;
    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        judgeDao = new Sql2oJudgeDao(sql2o);
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
    }

    @Test
    public void getAllJudges() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void getCaseByForJudge() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void clearAll() {
    }

    public Judge setUpNewJudge(){
        Judge judge = new Judge("dscc","dscgfbvc");
        return judge;
    }
}