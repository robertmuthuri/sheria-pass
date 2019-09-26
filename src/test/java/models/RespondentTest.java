package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RespondentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void RespondentInstantiatesCorrectly() throws Exception{
        Respondent respondent = setUpRespondent();
        assertTrue( respondent instanceof Respondent);
    }

    @Test
    public void getName() {
        Respondent respondent = setUpRespondent();
        assertEquals("Daisy",respondent.getName());
    }

    @Test
    public void getType() {
        Respondent respondent = setUpRespondent();
        assertEquals("respondent",respondent.getType());
    }

    //helper
    public Respondent setUpRespondent(){
        return new Respondent("Daisy");
    }
}