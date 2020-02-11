package dataprovider;

import dto.SearchDataDTO;
import org.junit.Assert;
import org.junit.Test;
import utils.AppConstants;

import java.util.Map;

public class SearchDataProviderTest {

    @Test
    public void testProcess() throws Exception {
        // Setup

        // Run the test
        final Map<String, SearchDataDTO> result = SearchDataProvider.process();

        // Verify the results
        Assert.assertEquals(result.size(),3); // Size check

        Assert.assertTrue(result.get("1") != null); // Users Data
        Assert.assertTrue(result.get("2") != null); // Tickets Data
        Assert.assertTrue(result.get("3") != null); // Org Data
        Assert.assertFalse(result.get("4") != null); // Negative case

    }

    @Test
    public void testBuildSearchUsersData() throws Exception {
        // Setup
        final SearchDataDTO searchDataDTO = new SearchDataDTO(AppConstants.users);

        // Run the test
        final SearchDataDTO result = SearchDataProvider.buildSearchData(AppConstants.user_path, searchDataDTO);

        // Verify the results
        Assert.assertTrue(!searchDataDTO.getDataMap().isEmpty());
        Assert.assertTrue(!searchDataDTO.getSearchMap().isEmpty());

        Assert.assertTrue(searchDataDTO.getDataMap().containsKey("1"));
    }

    @Test
    public void testBuildSearchOrgsData() throws Exception {
        // Setup
        final SearchDataDTO searchDataDTO = new SearchDataDTO(AppConstants.orgs);

        // Run the test
        final SearchDataDTO result = SearchDataProvider.buildSearchData(AppConstants.org_path, searchDataDTO);

        // Verify the results
        Assert.assertTrue(!searchDataDTO.getDataMap().isEmpty());
        Assert.assertTrue(!searchDataDTO.getSearchMap().isEmpty());

        Assert.assertTrue(searchDataDTO.getDataMap().containsKey("101"));
    }


    @Test
    public void testBuildSearchTktssData() throws Exception {
        // Setup
        final SearchDataDTO searchDataDTO = new SearchDataDTO(AppConstants.tickets);

        // Run the test
        final SearchDataDTO result = SearchDataProvider.buildSearchData(AppConstants.tickets_path, searchDataDTO);

        // Verify the results
        Assert.assertTrue(!searchDataDTO.getDataMap().isEmpty());
        Assert.assertTrue(!searchDataDTO.getSearchMap().isEmpty());

        Assert.assertTrue(searchDataDTO.getDataMap().containsKey("436bf9b0-1147-4c0a-8439-6f79833bff5b"));
    }


    /**
     * More tests can be added for this class if time persists.
     */
}
