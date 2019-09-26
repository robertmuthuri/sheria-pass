package dao;

import models.Law;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oLawDaoTest {

    private Connection conn;
    private Sql2oLawDao lawDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        lawDao = new Sql2oLawDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        Law law = setUpNewLaw();
        assertTrue(lawDao.getAllLaws().contains(law));
    }
    @Test
    public  void add_assignsId(){
        Law law = setUpNewLaw();
        assertEquals(1,law.getId());
    }

    @Test
    public void getAllLaws() {
        Law law = setUpNewLaw();
        Law law1 = setUpNewLaw();
        assertEquals(2,lawDao.getAllLaws().size());
    }

    @Test
    public void findById() {
        Law law = setUpNewLaw();
        assertEquals(law,lawDao.findById(law.getId()));
    }

    @Test
    public void deleteById() {
        Law law = setUpNewLaw();
        Law law1 = setUpNewLaw();
        assertEquals(2,lawDao.getAllLaws().size());
        lawDao.deleteById(law.getId());
        assertEquals(1,lawDao.getAllLaws().size());
        lawDao.deleteById(law1.getId());
        assertEquals(0,lawDao.getAllLaws().size());
    }

    @Test
    public void clearAll() {
        Law law = setUpNewLaw();
        Law law1 = setUpNewLaw();
        lawDao.clearAll();
        assertEquals(0,lawDao.getAllLaws().size());
    }

    // helper
    public Law setUpNewLaw(){
        Law law= new Law("hello", "vgcvv fdgf","https/vvxm/vcha");
        lawDao.add(law);
        return  law;
    }
}