package dao;
import models.*;
import models.CaseLaw;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.*;

import static org.junit.Assert.*;

public class Sql2oCaselawDaoTest {

    private Sql2oCaselawDao caselawDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        caselawDao = new  Sql2oCaselawDao(sql2o);
        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    // Helper method
    public CaseLaw setupCaselaw() {
        return new CaseLaw("Raila Amolo Odinga & another v Independent Electoral and Boundaries Commission 2 others [2017] eKLR", "Validity of a presidential election-petition challenging the validity of the president elect-allegations of non-compliance with the Constitution and electoral laws - allegations of various  irregularities and illegalities during the conduct of the elections","Supreme Court of Kenya","(i) A declaration is hereby issued that the Presidential Election held on 8th August, 2017 was not conducted in accordance with the Constitution and the applicable law rendering the declared result invalid, null and void;","http://www.kenyalaw.org/kl/fileadmin/pdfdownloads/2017ElectionPetition/Presidential_Petition_1_of_2017.pdf"); }

    @Test
    public void addingCaselawSetsId() throws Exception {
        CaseLaw testCaselaw = setupCaselaw();
        int originalCaselawId = testCaselaw.getId();
        caselawDao.add(testCaselaw);
        assertNotEquals(originalCaselawId, testCaselaw.getId());
    }
    @Test
    public void addedCaselawsAreReturnedFromGetAll() throws Exception {
        CaseLaw testCaselaw = setupCaselaw();
        caselawDao.add(testCaselaw);
        assertEquals(1, caselawDao.getAll().size());
    }
    @Test
    public void noCaselawsReturnsEmptyList() throws Exception {
        assertEquals(0, caselawDao.getAll().size());
    }
    @Test
    public void deleteByIdDeletesCorrectCaselaw() throws Exception {
        CaseLaw testCaselaw = setupCaselaw();
        caselawDao.add(testCaselaw);
        caselawDao.deleteById(testCaselaw.getId());
        assertEquals(0, caselawDao.getAll().size());
    }
    @Test
    public void clearAll() throws Exception {
        CaseLaw testCaselaw = setupCaselaw();
        caselawDao.add(testCaselaw);
        caselawDao.clearAll();
        assertEquals(0, caselawDao.getAll().size());
    }
}