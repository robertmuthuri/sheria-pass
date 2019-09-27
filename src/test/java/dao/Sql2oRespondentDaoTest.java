package dao;

import models.CaseLaw;
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
    private Sql2oCaselawDao caselawDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        respondentDao = new Sql2oRespondentDao(sql2o);
        caselawDao = new Sql2oCaselawDao(sql2o);
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
        CaseLaw caseLaw = setupCaselaw();
        CaseLaw caseLaw1 = setupCaselaw();
        Respondent respondent=setUpRespondent();

        respondentDao.addRespondentToCase(respondent,caseLaw);
        respondentDao.addRespondentToCase(respondent,caseLaw1);
        assertEquals(2,respondentDao.getAllCasesForResponded(respondent.getId()).size());
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

    public CaseLaw setupCaselaw() {
        CaseLaw caseLaw= new CaseLaw("Raila Amolo Odinga & another v Independent Electoral and Boundaries Commission 2 others [2017] eKLR", "Validity of a presidential election-petition challenging the validity of the president elect-allegations of non-compliance with the Constitution and electoral laws - allegations of various  irregularities and illegalities during the conduct of the elections","Supreme Court of Kenya","(i) A declaration is hereby issued that the Presidential Election held on 8th August, 2017 was not conducted in accordance with the Constitution and the applicable law rendering the declared result invalid, null and void;","http://www.kenyalaw.org/kl/fileadmin/pdfdownloads/2017ElectionPetition/Presidential_Petition_1_of_2017.pdf");
        caselawDao.add(caseLaw);
        return  caseLaw;
    }
}