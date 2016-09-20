package com.xiaxiao.xappdbg_test;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
    //    assertEquals(5, 2 + 2);
        tt();
        MainActivity c=new MainActivity();
        assertEquals("xx1232",c.addString("xx"));
        //assertEquals("xx","xx");
    }
    public void tt() throws Exception{
        Student s=new Student("xiaxiao",24,"11111111111","beijing",false);
        assertEquals(25,s.getAge());
    }
}