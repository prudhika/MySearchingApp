package searchApp;

import dto.SearchDataDTO;
import org.json.simple.parser.ParseException;
import utils.AppConstants;
import utils.JSONUtil;
import dataprovider.SearchDataProvider;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * This class has method to perform validations on the input
 * and provide appropriate console output to the user
 * Based on the fields capability is provided to invoke right
 * search in the maps returned from the corresponding search dto(s).
 */
public class BasicSearchService {

    Map<String, SearchDataDTO> data;

    /**
     * This method actually orchestrates the search operation based on the inputs provided.
     * It also ensures data is built during the first search and referred to the
     * same map for subsequent searches for faster search performance.
     *
     * @param input         category to be searched
     * @param searchField   field to be searched
     * @param searchValue   value to be searched
     * @param isFirstSearch represents whether its initial search
     * @throws IOException    This is propogated from buildSearchData of SearchDataProvider
     * @throws ParseException This is propogated from buildSearchData of SearchDataProvider
     */
    public void searchExactKeywords(String input, String searchField, String searchValue, boolean isFirstSearch) throws IOException, ParseException {

        if (isFirstSearch)
            data = SearchDataProvider.process(); // This will ensure O(1) for search for all subsequent searches in a session

        //Search amd print
        SearchDataDTO currentSearchDTO = data.get(input.trim());

        Set fieldSet = currentSearchDTO.dataMap.entrySet().iterator().next().getValue().keySet();

        /*Search field validation*/
        while (!fieldSet.contains(searchField.toLowerCase())) {
            System.out.println(AppConstants.USER_NO_FIELD_MESSAGE);
            System.out.println(fieldSet);
            System.out.println("Please Enter search Item ");
            Scanner scanner = new Scanner(System.in);
            searchField = scanner.next();
        }

        final String tempSearchField = searchField;
        /*This is to search the empty values for a given search field*/
        if (searchValue.isEmpty()) {
            currentSearchDTO.getDataMap().forEach((k, v) -> {
                if (!v.keySet().contains(tempSearchField) || String.valueOf(v.get(tempSearchField)).isEmpty())
                    JSONUtil.printJsonObject(v);
            });
        }

        //find value
        String value = currentSearchDTO.getSearchMap().get(searchValue.trim());

        if (value == null) {
            System.out.println(AppConstants.USER_NO_RESULTS_MESSAGE);
            return;
        }

        String[] ids = value.split("\\|\\|"); // Get all the matching ids.
        boolean isNoSearchField = true; // for empty search value.

        // for printing each entity
        for (String eachId : ids) {
            if (searchField.equalsIgnoreCase(eachId.trim().substring(0, eachId.indexOf("::")))) {
                String _id = eachId.trim().substring(eachId.indexOf("::") + 2);
                JSONUtil.printJsonObject(currentSearchDTO.dataMap.get(_id));
                isNoSearchField = Boolean.FALSE;
            }
        }
        if (isNoSearchField) System.out.println(AppConstants.USER_NO_RESULTS_MESSAGE);
    }
}
