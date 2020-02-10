package SearchApp;

import DTO.SearchDataDTO;
import org.json.simple.parser.ParseException;
import utils.AppConstants;
import utils.JSONUtil;
import utils.SearchDataProvider;

import java.io.IOException;
import java.util.Map;

public class BasicSearchService  {

    Map<String, SearchDataDTO> data;
    public void searchExactKeywords(String input, String searchField, String searchValue, boolean isFirstSearch) throws IOException, ParseException {

        if(isFirstSearch) data = SearchDataProvider.process(); // This will ensure O(1) for search for all subsequent searches in a session

        //Search amd print
        SearchDataDTO currentSearchDTO = data.get(input.trim());

        /*This is to search the empty values for a given search field*/
        if(searchValue.isEmpty())
        {
            currentSearchDTO.getDataMap().forEach((k, v) -> {
                if (! v.keySet().contains(searchField) || String.valueOf(v.get(searchField)).isEmpty())
                    JSONUtil.printJsonObject(v);

            });
        }

        //find value
        String value = currentSearchDTO.getSearchMap().get(searchValue.trim());

        if(value == null)  System.out.println(AppConstants.USER_NO_RESULTS_MESSAGE);

        String[] ids = value.split("\\|\\|");
        boolean isNoSearchField = true;
        for(String eachId : ids)
        {
            if(searchField.equalsIgnoreCase(eachId.trim().substring(0,eachId.indexOf("::"))))
            {
            String _id= eachId.trim().substring(eachId.indexOf("::")+2);
            JSONUtil.printJsonObject(currentSearchDTO.dataMap.get(_id));
            isNoSearchField = Boolean.FALSE;
            }
        }

        if(isNoSearchField) System.out.println(AppConstants.USER_NO_RESULTS_MESSAGE);
    }
}
