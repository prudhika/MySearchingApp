package searchApp;

import dto.SearchDataDTO;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicSearchServiceTest {

    private BasicSearchService basicSearchServiceUnderTest;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() {
        basicSearchServiceUnderTest = new BasicSearchService();
        basicSearchServiceUnderTest.data = mock(Map.class);
    }

    @Test
    public void testSearchUsersExactKeywords() throws IOException, ParseException {
        // Setup
        when(basicSearchServiceUnderTest.data.get("key")).thenReturn(new SearchDataDTO("USERS"));

        // Run the test
        basicSearchServiceUnderTest.searchExactKeywords("1", "_id", "1", true);

        // Verify the results
        assertTrue(systemOutRule.getLog().contains("74341f74-9c79-49d5-9611-87ef9b6eb75f"));
        assertTrue(systemOutRule.getLog().contains("Don't Worry Be Happy!"));
        assertTrue(systemOutRule.getLog().contains("organization_id"));
        assertFalse(systemOutRule.getLog().contains("No Results Found"));
        assertFalse(systemOutRule.getLog().contains("Error Occured"));

    }


    @Test
    public void testSearchOrgExactKeywords() throws IOException, ParseException {
        // Setup
        when(basicSearchServiceUnderTest.data.get("key")).thenReturn(new SearchDataDTO("USERS"));

        // Run the test
        basicSearchServiceUnderTest.searchExactKeywords("3", "_id", "101", true);

        // Verify the results
        assertTrue(systemOutRule.getLog().contains("101"));
        assertTrue(systemOutRule.getLog().contains("kage.com"));
        assertTrue(systemOutRule.getLog().contains("shared_tickets"));
        assertFalse(systemOutRule.getLog().contains("No Results Found"));
        assertFalse(systemOutRule.getLog().contains("Error Occured"));

    }


    @Test
    public void testSearchExactKeywordsNoResults() throws IOException, ParseException {
        // Setup
        when(basicSearchServiceUnderTest.data.get("key")).thenReturn(new SearchDataDTO("USERS"));

        // Run the test
        basicSearchServiceUnderTest.searchExactKeywords("1", "_id", "99999", true);

        // Verify the results
        assertFalse(systemOutRule.getLog().contains("74341f74-9c79-49d5-9611-87ef9b6eb75f"));
        assertFalse(systemOutRule.getLog().contains("Don't Worry Be Happy!"));
        assertFalse(systemOutRule.getLog().contains("organization_id"));
        assertTrue(systemOutRule.getLog().contains("No Results Found"));
        assertFalse(systemOutRule.getLog().contains("Error Occured"));

    }


    @Test
    public void testSearchTicketsExactKeywords() throws IOException, ParseException {
        // Setup
        when(basicSearchServiceUnderTest.data.get("key")).thenReturn(new SearchDataDTO("USERS"));

        // Run the test
        basicSearchServiceUnderTest.searchExactKeywords("2", "_id", "436bf9b0-1147-4c0a-8439-6f79833bff5b", true);

        // Verify the results
        assertTrue(systemOutRule.getLog().contains("436bf9b0-1147-4c0a-8439-6f79833bff5b"));
        assertTrue(systemOutRule.getLog().contains("priority"));
        assertTrue(systemOutRule.getLog().contains("organization_id"));
        assertFalse(systemOutRule.getLog().contains("No Results Found"));
        assertFalse(systemOutRule.getLog().contains("Error Occured"));

    }

 /* To be implemented
  /**
     * More tests can be added for this class if time persists to test Exceptions that are thrown and Invalid Search Items.
     */
}
