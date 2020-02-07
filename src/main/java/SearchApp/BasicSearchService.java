package SearchApp;

import DTO.SearchDataDTO;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import utils.JSONUtil;
import utils.SearchDataProvider;

import java.io.IOException;
import java.util.Map;

public class BasicSearchService  {

    public void searchExactKeywords(String input, String searchField, String searchValue) throws IOException, ParseException {

        Map<String, SearchDataDTO> data = SearchDataProvider.process();

        //Search amd print
        SearchDataDTO currentSearchDTO = data.get(input);

        //find value
        String value = currentSearchDTO.getSearchMap().get(searchValue);

        String[] ids = value.split("\\|\\|");
        for(String eachId : ids)
        {
            if(searchField.equalsIgnoreCase(eachId.trim().substring(0,eachId.indexOf("::"))))
            {
            String _id= eachId.trim().substring(eachId.indexOf("::")+2);
            JSONUtil.printJsonObject(currentSearchDTO.dataMap.get(_id));
            System.out.println("-----------------------------------------------------------");
            }
        }

    }
}
