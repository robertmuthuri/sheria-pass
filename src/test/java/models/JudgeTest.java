package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JudgeTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void RespondentInstantiatesCorrectly() throws Exception{
        Judge judge = setUpJudge();
        assertTrue( judge instanceof Judge);
    }

    //helper method
    public Judge setUpJudge (){
        return new Judge("Robert","v bncvb");
    }
}