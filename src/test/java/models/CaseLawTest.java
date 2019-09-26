package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class CaseLawTest {
    //helper methods
    public CaseLaw setupCaselaw() { return new CaseLaw("Raila Amolo Odinga & another v Independent Electoral and Boundaries Commission 2 others [2017] eKLR", "Validity of a presidential election-petition challenging the validity of the president elect-allegations of non-compliance with the Constitution and electoral laws - allegations of various  irregularities and illegalities during the conduct of the elections","Supreme Court of Kenya","(i) A declaration is hereby issued that the Presidential Election held on 8th August, 2017 was not conducted in accordance with the Constitution and the applicable law rendering the declared result invalid, null and void;","http://www.kenyalaw.org/kl/fileadmin/pdfdownloads/2017ElectionPetition/Presidential_Petition_1_of_2017.pdf");}

    @Test
    public void getCase_citation() {
        CaseLaw testCaseLaw = setupCaselaw();
        assertEquals("Raila Amolo Odinga & another v Independent Electoral and Boundaries Commission 2 others [2017] eKLR", testCaseLaw.getCase_citation());
        assertNotEquals("[2017] eKLR", testCaseLaw.getCase_citation());
    }
    @Test
    public void getCase_court() {
        CaseLaw testCaseLaw = setupCaselaw();
        assertEquals("Supreme Court of Kenya", testCaseLaw.getCase_court());
        assertNotEquals("supreme court of kenya", testCaseLaw.getCase_court());
    }
    @Test
    public void getCase_holding() {
        CaseLaw testCaseLaw = setupCaselaw();
        assertEquals("(i) A declaration is hereby issued that the Presidential Election held on 8th August, 2017 was not conducted in accordance with the Constitution and the applicable law rendering the declared result invalid, null and void;", testCaseLaw.getCase_holding());
    }
    @Test
    public void getCase_summary() {
        CaseLaw testCaseLaw = setupCaselaw();
        assertEquals("Validity of a presidential election-petition challenging the validity of the president elect-allegations of non-compliance with the Constitution and electoral laws - allegations of various  irregularities and illegalities during the conduct of the elections", testCaseLaw.getCase_summary());
    }
    @Test
    public void getCase_url() {
        CaseLaw testCaseLaw = setupCaselaw();
        assertEquals("http://www.kenyalaw.org/kl/fileadmin/pdfdownloads/2017ElectionPetition/Presidential_Petition_1_of_2017.pdf", testCaseLaw.getCase_url());
    }
    @Test
    public void setCase_citation() {
        CaseLaw testCaseLaw = setupCaselaw();
        testCaseLaw.setCase_citation("Raila v IEBC [2017] eKLR");
        assertEquals("Raila v IEBC [2017] eKLR", testCaseLaw.getCase_citation());
    }
    @Test
    public void setCase_court() {
        CaseLaw testCaseLaw = setupCaselaw();
        testCaseLaw.setCase_court("Supreme Court of India");
        assertNotEquals("Supreme Court of Kenya", testCaseLaw.getCase_court());
    }
    @Test
    public void setCase_holding() {
        CaseLaw testCaseLaw = setupCaselaw();
        testCaseLaw.setCase_holding("We're not sure about this ruling!");
        assertNotEquals("(i) A declaration is hereby issued that the Presidential Election held on 8th August, 2017 was not conducted in accordance with the Constitution and the applicable law rendering the declared result invalid, null and void;", testCaseLaw.getCase_holding());

    }
    @Test
    public void setCase_summary() {
        CaseLaw testCaseLaw = setupCaselaw();
        testCaseLaw.setCase_summary("I'm not sure I'm up to the task");
        assertNotEquals("Validity of a presidential election-petition challenging the validity of the president elect-allegations of non-compliance with the Constitution and electoral laws - allegations of various  irregularities and illegalities during the conduct of the elections", testCaseLaw.getCase_summary());
    }
    @Test
    public void setCase_url() {
        CaseLaw testCaseLaw = setupCaselaw();
        testCaseLaw.setCase_url("other url");
        assertNotEquals("\"http://www.kenyalaw.org/kl/fileadmin/pdfdownloads/2017ElectionPetition/Presidential_Petition_1_of_2017.pdf",testCaseLaw.getCase_url());
    }
}