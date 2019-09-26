package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PetitionerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void PetitionerInstantiatesCorrectly() throws Exception{
        Petitioner petitioner = setUpPetitioner();
        assertTrue( petitioner instanceof Petitioner);
    }

    @Test
    public void getName() {
        Petitioner petitioner = setUpPetitioner();
        assertEquals("Robert",petitioner.getName());
    }

    @Test
    public void getType() {
        Petitioner petitioner = setUpPetitioner();
        assertEquals("petitioner",petitioner.getType());
    }

    //helper method
    public Petitioner setUpPetitioner (){
        return new Petitioner("Robert");
    }
}