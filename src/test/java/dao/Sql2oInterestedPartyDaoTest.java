package dao;

import models.CaseLaw;
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
    private  Sql2oCaselawDao caselawDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        interestedPartyDao = new Sql2oInterestedPartyDao(sql2o);
        caselawDao = new Sql2oCaselawDao(sql2o);
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
        CaseLaw caseLaw = setupCaselaw();
        CaseLaw caseLaw1 = setupCaselaw();
        InterestedParty interestedParty = setUpInterestedParty();

        interestedPartyDao.addInterestedPartyToCaselaw(interestedParty,caseLaw);
        interestedPartyDao.addInterestedPartyToCaselaw(interestedParty,caseLaw1);
        assertEquals(2,interestedPartyDao.getCaseLawsForInterestedParty(interestedParty.getId()).size());
    }

    @Test
    public void deleteById() {
        InterestedParty interestedParty = setUpInterestedParty();
        InterestedParty interestedParty1 = setUpInterestedParty();
        interestedPartyDao.deleteById(interestedParty.getId());
        assertEquals(1,interestedPartyDao.getAllInterestedParties().size());
    }



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
    public CaseLaw setupCaselaw() {
        CaseLaw caseLaw =  new CaseLaw("Raila Amolo Odinga & another v Independent Electoral and Boundaries Commission 2 others [2017] eKLR", "Validity of a presidential election-petition challenging the validity of the president elect-allegations of non-compliance with the Constitution and electoral laws - allegations of various  irregularities and illegalities during the conduct of the elections","Supreme Court of Kenya","(i) A declaration is hereby issued that the Presidential Election held on 8th August, 2017 was not conducted in accordance with the Constitution and the applicable law rendering the declared result invalid, null and void;","http://www.kenyalaw.org/kl/fileadmin/pdfdownloads/2017ElectionPetition/Presidential_Petition_1_of_2017.pdf");
    caselawDao.add(caseLaw);
    return caseLaw;
    }


}