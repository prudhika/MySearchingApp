package utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppConstantsTest {

    private AppConstants appConstantsUnderTest;

    @Before
    public void setUp() {
        appConstantsUnderTest = new AppConstants();
    }

    @Test
    public void testAppConstants()
    {
        Assert.assertEquals("1",AppConstants.ONE);
        Assert.assertEquals("2",AppConstants.TWO);
        Assert.assertEquals("3",AppConstants.THREE);
        Assert.assertEquals("Error Occurred. Please reach out to application support team with below details - \n", AppConstants.USER_ERROR_MESSAGE);
        // We should add for all the constants in similar fashion.
    }
}
