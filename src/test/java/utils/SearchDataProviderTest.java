package utils;

import DTO.SearchDataDTO;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class SearchDataProviderTest {

    @Test
    public void testProcess() throws Exception {
        // Setup

        // Run the test
        final Map<String, SearchDataDTO> result = SearchDataProvider.process();

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testProcess_ThrowsIOException() throws Exception {
        // Setup

        // Run the test
        SearchDataProvider.process();
    }

    @Test(expected = ParseException.class)
    public void testProcess_ThrowsParseException() throws Exception {
        // Setup

        // Run the test
        SearchDataProvider.process();
    }

    @Test
    public void testBuildSearchData() throws Exception {
        // Setup
        final SearchDataDTO searchDataDTO = new SearchDataDTO("type");

        // Run the test
        final SearchDataDTO result = SearchDataProvider.buildSearchData("path", searchDataDTO);

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testBuildSearchData_ThrowsIOException() throws Exception {
        // Setup
        final SearchDataDTO searchDataDTO = new SearchDataDTO("type");

        // Run the test
        SearchDataProvider.buildSearchData("path", searchDataDTO);
    }

    @Test(expected = ParseException.class)
    public void testBuildSearchData_ThrowsParseException() throws Exception {
        // Setup
        final SearchDataDTO searchDataDTO = new SearchDataDTO("type");

        // Run the test
        SearchDataProvider.buildSearchData("path", searchDataDTO);
    }
}
