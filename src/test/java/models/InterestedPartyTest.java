package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InterestedPartyTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void InterestedPartyInstantiatesCorrectly() throws Exception{
        InterestedParty interestedParty  = setUpInterestedParty();
        assertTrue( interestedParty instanceof InterestedParty);
    }

    @Test
    public void getName() {
        InterestedParty interestedParty  = setUpInterestedParty();
        assertEquals("Robert",interestedParty.getName());
    }

    @Test
    public void getType() {
        InterestedParty interestedParty  = setUpInterestedParty();
        assertEquals("interested party",interestedParty.getType());
    }

    //helper method
    public InterestedParty setUpInterestedParty (){
        return new InterestedParty("Robert");
    }
}