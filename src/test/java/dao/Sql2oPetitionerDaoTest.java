package dao;

import models.CaseLaw;
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
    private Sql2oCaselawDao caseLawDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        petitionerDao = new Sql2oPetitionerDao(sql2o);
        caseLawDao = new Sql2oCaselawDao(sql2o);
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
        CaseLaw caseLaw = setupCaselaw();
        CaseLaw caseLaw1 = setupCaselaw();
        Petitioner petitioner = setUpPetitioner();

        petitionerDao.addPetitionerToCase(petitioner,caseLaw);
        petitionerDao.addPetitionerToCase(petitioner,caseLaw1);
        assertEquals(2,petitionerDao.getCaseLawsForPetitioner(petitioner.getId()).size());
    }

//    @Test
//    public void deleteById() {
//        Petitioner petitioner = setUpPetitioner();
//        Petitioner petitioner1 = setUpPetitioner();
//        assertEquals(2,petitionerDao.getAllPetioners().size());
//        petitionerDao.deleteById(petitioner.getId());
//        assertEquals(1,petitionerDao.getAllPetioners().size());
//    }

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
    public CaseLaw setupCaselaw() {
        CaseLaw caseLaw= new CaseLaw("Raila Amolo Odinga & another v Independent Electoral and Boundaries Commission 2 others [2017] eKLR", "Validity of a presidential election-petition challenging the validity of the president elect-allegations of non-compliance with the Constitution and electoral laws - allegations of various  irregularities and illegalities during the conduct of the elections","Supreme Court of Kenya","(i) A declaration is hereby issued that the Presidential Election held on 8th August, 2017 was not conducted in accordance with the Constitution and the applicable law rendering the declared result invalid, null and void;","http://www.kenyalaw.org/kl/fileadmin/pdfdownloads/2017ElectionPetition/Presidential_Petition_1_of_2017.pdf");
    caseLawDao.add(caseLaw);
    return  caseLaw;
    }
}