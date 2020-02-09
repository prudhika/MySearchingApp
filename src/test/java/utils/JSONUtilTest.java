package utils;

import org.json.simple.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

public class JSONUtilTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testPrintJsonObject() {
        // Setup
        final JSONObject jsonObj = new JSONObject(new HashMap<>());
        HashMap<Integer, String> hash_map = new HashMap<Integer, String>();

        hash_map.put(10, "Geeks");
        hash_map.put(15, "4");
        hash_map.put(20, "Geeks");
        hash_map.put(25, "Welcomes");
        hash_map.put(30, "You");

        jsonObj.putAll(hash_map);

        // Run the test
        JSONUtil.printJsonObject(jsonObj);

        assertTrue(systemOutRule.getLog().contains("Geeks")); // Success
        assertFalse(systemOutRule.getLog().contains("Geaks")); // Failure

        // Verify the results
    }

    @Test
    public void testGenerateStringOfLength() {
        assertEquals("   ", JSONUtil.generateStringOfLength(3));
        assertEquals("", JSONUtil.generateStringOfLength(0));
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testExceptionNegativeInput()  {
       JSONUtil.generateStringOfLength(-1);

    }

    @Test
    public void testGenerateStringOfLengthOther() {
        assertEquals(3, JSONUtil.generateStringOfLength(3).length());
    }
}
