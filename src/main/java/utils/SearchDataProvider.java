package utils;

import DTO.SearchDataDTO;
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

    static final ClassLoader loader = SearchDataProvider.class.getClassLoader();

    public static Map<String,SearchDataDTO> process() throws FileNotFoundException, IOException, ParseException {
        Map<String,SearchDataDTO> data = new HashMap<String, SearchDataDTO>();

        SearchDataDTO userSearchDataDTO = new SearchDataDTO();
        userSearchDataDTO.setType("USERS");
        data.put("1",buildSearchData(AppConstants.user_path, userSearchDataDTO));

        SearchDataDTO ticketSearchDataDTO = new SearchDataDTO();
        ticketSearchDataDTO.setType("TICKETS");
        data.put("2",buildSearchData(AppConstants.tickets_path, ticketSearchDataDTO));

        SearchDataDTO orgSearchDataDTO = new SearchDataDTO();
        orgSearchDataDTO.setType("ORGS");
        data.put("3", buildSearchData(AppConstants.org_path, orgSearchDataDTO));

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
                Object searchItem = jsonObject.get(key);
                if (searchMap.containsKey(searchItem)) {
                    String value = searchMap.get(searchItem)
                            + "||" + key
                            + "::" + id;
                    searchMap.put(jsonObject.get(key), value);
                } else {
                    Object searchKey = jsonObject.get(key);
                    searchMap.put(searchKey, key + "::" + id);
                }
            }
        }
        for (Map.Entry<String, JSONObject> entry : dataMap.entrySet()) {
          //  System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }

        for (Map.Entry<Object, String> entry : searchMap.entrySet()) {
           // System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }

        searchDataDTO.setDataMap(dataMap);
        searchDataDTO.setSearchMap(searchMap);

        return searchDataDTO;
    }


}