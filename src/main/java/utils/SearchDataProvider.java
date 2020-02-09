package utils;

import DTO.SearchDataDTO;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


@Component
public class SearchDataProvider {

    public static HashMap<String, JSONObject> dataMap;
    public static HashMap<Object, String> searchMap;

    static final Logger logger = Logger.getLogger(SearchDataProvider.class);
    static final ClassLoader loader = SearchDataProvider.class.getClassLoader();


    public static Map<String,SearchDataDTO> process() throws IOException, ParseException {
        Map<String,SearchDataDTO> data = new HashMap<String, SearchDataDTO>();

        SearchDataDTO userSearchDataDTO = new SearchDataDTO(AppConstants.users);
        data.put(AppConstants.ONE,buildSearchData(AppConstants.user_path, userSearchDataDTO));

        SearchDataDTO ticketSearchDataDTO = new SearchDataDTO(AppConstants.tickets);
        data.put(AppConstants.TWO,buildSearchData(AppConstants.tickets_path, ticketSearchDataDTO));

        SearchDataDTO orgSearchDataDTO = new SearchDataDTO(AppConstants.orgs);
        data.put(AppConstants.THREE, buildSearchData(AppConstants.org_path, orgSearchDataDTO));

        return data;
    }

    public static SearchDataDTO buildSearchData(String path, SearchDataDTO searchDataDTO) throws IOException, ParseException {

        dataMap = new HashMap<String, JSONObject>();
        searchMap = new HashMap<Object, String>();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        JSONArray jsonArray = (JSONArray) obj;

        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String id = (String) jsonObject.get("_id").toString();
            dataMap.put(id, jsonObject);

            for (Object key : jsonObject.keySet()) {
                Object searchItem = String.valueOf(jsonObject.get(key));
                if (searchMap.containsKey(searchItem)) {
                    String value = searchMap.get(searchItem)
                            + "||" + key
                            + "::" + id;
                    searchMap.put(String.valueOf(jsonObject.get(key)), value);
                } else {
                    Object searchKey = jsonObject.get(key);
                    searchMap.put(String.valueOf(searchKey), key + "::" + id);
                }
            }
        }
        for (Map.Entry<String, JSONObject> entry : dataMap.entrySet()) {
            //logger.info(entry.getKey() + ":" + entry.getValue().toString());
        }

        for (Map.Entry<Object, String> entry : searchMap.entrySet()) {
           // logger.info(entry.getKey() + ":" + entry.getValue().toString());
        }

        searchDataDTO.setDataMap(dataMap);
        searchDataDTO.setSearchMap(searchMap);

        return searchDataDTO;
    }


}