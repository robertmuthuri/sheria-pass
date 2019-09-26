package dao;

import models.Petitioner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oPetitionerDaoTest {

    private Connection conn;
    private Sql2oPetitionerDao petitionerDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        petitionerDao = new Sql2oPetitionerDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        Petitioner petitioner = setUpPetitioner();
        assertTrue(petitionerDao.getAllPetioners().contains(petitioner));
    }

    @Test
    public  void  addAssignsId(){
        Petitioner petitioner = setUpPetitioner();
        Petitioner petitioner1 = setUpPetitioner();
        assertEquals(1,petitioner.getId());
        assertEquals(2,petitioner1.getId());
    }
    @Test
    public void addPetitionerToCase() {

    }

    @Test
    public void getAllPetioners() {
        Petitioner petitioner = setUpPetitioner();
        Petitioner petitioner1 = setUpPetitioner();
        Petitioner petitioner2 = setUpPetitioner();
        assertEquals(3,petitionerDao.getAllPetioners().size());
    }

    @Test
    public void findById() {
        Petitioner petitioner = setUpPetitioner();
        assertEquals(petitioner,petitionerDao.findById(petitioner.getId()));
    }

    @Test
    public void getCaseByPetitionerId() {
    }

    @Test
    public void deleteById() {
        Petitioner petitioner = setUpPetitioner();
        Petitioner petitioner1 = setUpPetitioner();
        assertEquals(2,petitionerDao.getAllPetioners().size());
        petitionerDao.deleteById(petitioner.getId());
        assertEquals(1,petitionerDao.getAllPetioners().size());
    }

    @Test
    public void clearAll() {
        Petitioner petitioner = setUpPetitioner();
        Petitioner petitioner1 = setUpPetitioner();
        petitionerDao.clearAll();
        assertEquals(0,petitionerDao.getAllPetioners().size());
    }

    //helper
    public Petitioner setUpPetitioner(){
        Petitioner petitioner = new Petitioner("Daisy");
        petitionerDao.add(petitioner);
        return petitioner;
    }
}