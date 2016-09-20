package com.xiaxiao.xappdbg_test;

import android.app.Application;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityUnitTestCase<MainActivity> {
    Intent intent;
    public ApplicationTest() {
        super(MainActivity.class);
    }

    /*@Override
    protected void setUp() throws Exception {
        super.setUp();
        mLoginIntent = new Intent(getInstrumentation().getTargetContext(), LoginActivity.class);
    }*/

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
    }


    public void testAddString(){
        startActivity(intent, null, null);
        assertEquals("hh123", getActivity().addString("hh"));
    }
}