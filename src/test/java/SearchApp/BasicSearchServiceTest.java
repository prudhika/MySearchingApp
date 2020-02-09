package SearchApp;

import DTO.SearchDataDTO;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicSearchServiceTest {

    private BasicSearchService basicSearchServiceUnderTest;

    @Before
    public void setUp() {
        basicSearchServiceUnderTest = new BasicSearchService();
        basicSearchServiceUnderTest.data = mock(Map.class);
    }

    @Test
    public void testSearchExactKeywords() throws IOException, ParseException {
        // Setup
        //when(basicSearchServiceUnderTest.data.get("key")).thenReturn(new SearchDataDTO("USERS"));

        // Run the test
        basicSearchServiceUnderTest.searchExactKeywords("1", "_id", "1", true);

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testSearchExactKeywords_ThrowsIOException() throws IOException, ParseException {
        // Setup
        when(basicSearchServiceUnderTest.data.get("key")).thenReturn(new SearchDataDTO("type"));

        // Run the test
        basicSearchServiceUnderTest.searchExactKeywords("input", "searchField", "searchValue", false);
    }

    @Test(expected = ParseException.class)
    public void testSearchExactKeywords_ThrowsParseException() throws IOException, ParseException {
        // Setup
        when(basicSearchServiceUnderTest.data.get("key")).thenReturn(new SearchDataDTO("type"));

        // Run the test
        basicSearchServiceUnderTest.searchExactKeywords("input", "searchField", "searchValue", false);
    }
}
