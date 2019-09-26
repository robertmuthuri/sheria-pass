package dao;

import models.Respondent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oRespondentDaoTest {
    private Connection conn;
    private Sql2oRespondentDao respondentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        respondentDao = new Sql2oRespondentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        Respondent respondent=setUpRespondent();
       assertTrue(respondentDao.getAllRespondent().contains(respondent));
    }

    @Test
    public void addRespondentToCase() {
    }

    @Test
    public void getAllRespondent() {
        Respondent respondent=setUpRespondent();
        respondentDao.add(respondent);
        assertEquals(2,respondentDao.getAllRespondent().size());
    }

    @Test
    public void findById() {
        Respondent respondent=setUpRespondent();
        assertEquals(respondent,respondentDao.findById(respondent.getId()));
    }

    @Test
    public void getCaseByRespondentId() {
    }

    @Test
    public void deleteById() {
        Respondent respondent=setUpRespondent();
        Respondent respondent1 = setUpRespondent();
        assertEquals(2,respondentDao.getAllRespondent().size());
        respondentDao.deleteById(respondent.getId());
        assertEquals(1,respondentDao.getAllRespondent().size());
    }

    @Test
    public void clearAll() {
        Respondent respondent=setUpRespondent();
        Respondent respondent1 = setUpRespondent();
        Respondent respondent2 = setUpRespondent();
        respondentDao.clearAll();
        assertEquals(0,respondentDao.getAllRespondent().size());
    }

    //helper
    public Respondent setUpRespondent(){
        Respondent respondent = new Respondent("Daisy");
        respondentDao.add(respondent);
        return respondent;
    }
}