package dao;

import models.InterestedParty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oInterestedPartyDaoTest {

    private Connection conn;
    private Sql2oInterestedPartyDao interestedPartyDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        interestedPartyDao = new Sql2oInterestedPartyDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        InterestedParty interestedParty = setUpInterestedParty();
        assertTrue(interestedPartyDao.getAllInterestedParties().contains(interestedParty));
    }


    @Test
    public void getAllInterestedParties() {
        InterestedParty interestedParty = setUpInterestedParty();
        InterestedParty interestedParty1 = setUpInterestedParty();
        assertEquals(2,interestedPartyDao.getAllInterestedParties().size());
    }

    @Test
    public void findById() {
        InterestedParty interestedParty = setUpInterestedParty();
        assertEquals(interestedParty,interestedPartyDao.findById(interestedParty.getId()));
    }

    @Test
    public void getCaseByPetitionerId() {
    }

//    @Test
//    public void deleteById() {
//        InterestedParty interestedParty = setUpInterestedParty();
//        InterestedParty interestedParty1 = setUpInterestedParty();
//        interestedPartyDao.deleteById(interestedParty.getId());
//        assertEquals(1,interestedPartyDao.getAllInterestedParties().size());
//    }

    @Test
    public void clearAll() {
        InterestedParty interestedParty = setUpInterestedParty();
        InterestedParty interestedParty1 = setUpInterestedParty();
        interestedPartyDao.clearAll();
        assertEquals(0,interestedPartyDao.getAllInterestedParties().size());
    }

    public InterestedParty setUpInterestedParty(){
        InterestedParty interestedParty = new InterestedParty("robert");
        interestedPartyDao.add(interestedParty);
        return  interestedParty;
    }
}