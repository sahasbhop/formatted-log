package com.github.sahasbhop.flog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class FLogTest {

    @Before
    public void before() {
        FLog.setEnableLogCat(false);
        FLog.setEnableSystemOut(true);
    }

    @Test public void testHelloWorld() {
        FLog.v("HelloWorld");
        Assert.assertTrue(true);
    }

}
