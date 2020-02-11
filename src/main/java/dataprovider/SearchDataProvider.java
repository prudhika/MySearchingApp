package dataprovider;

import dto.SearchDataDTO;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import utils.AppConstants;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;


/**
 * This class has methods which will read through the
 * input jsons - users,tickets & orgs to build two maps which
 * will be used for searching user input.
 *
 * Here maps are used to store the data as it gives
 * faster search results with O(1) complexity and necessary
 * for avoiding redundant parsing through json files
 */
@Component
public class SearchDataProvider {

    public static HashMap<String, JSONObject> dataMap;
    public static HashMap<Object, String> searchMap;

    static final Logger logger = Logger.getLogger(SearchDataProvider.class);
    static final ClassLoader loader = SearchDataProvider.class.getClassLoader();


    /**
     * @return map which holds three entries each for each category - user, ticket and organization.
     * @throws IOException This is propogated from buildSearchData
     * @throws ParseException This is propogated from buildSearchData
     */
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

    /**
     * @param path represents the resource path for parsing.
     * @param searchDataDTO dto of the type for which data needs to built.
     * @return searchDataDTO with loading the data map and the search map for a specific type.
     * @throws IOException might occur while reading the file from the give @param path.
     * @throws ParseException might occur while parsing the file contents as JSON.
     */
    public static SearchDataDTO buildSearchData(String path, SearchDataDTO searchDataDTO) throws IOException, ParseException {

        dataMap = new HashMap<String, JSONObject>();
        searchMap = new HashMap<Object, String>();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(getFileFromResources(path))); // potential parsing/io exception.
        JSONArray jsonArray = (JSONArray) obj;

        for (int i = 0; i < jsonArray.size(); i++) {

            /* To build data map*/
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String id = (String) jsonObject.get("_id").toString(); // get the id of each entry
            dataMap.put(id, jsonObject); // build

            /* To build search map*/
            for (Object key : jsonObject.keySet()) {
                Object searchItem = String.valueOf(jsonObject.get(key));
                if (searchMap.containsKey(searchItem)) { // Append the fields
                    String value = searchMap.get(searchItem)
                            + "||" + key // delimiter for fields
                            + "::" + id; // delimiter for ids
                    searchMap.put(String.valueOf(jsonObject.get(key)), value); // skipping parsing through JSON array. Can be enhanced to search each value in the JSON Array as well
                } else {
                    Object searchKey = jsonObject.get(key);
                    searchMap.put(String.valueOf(searchKey), key + "::" + id); // first matching field
                }
            }
        }
        for (Map.Entry<String, JSONObject> entry : dataMap.entrySet()) {
            logger.info(entry.getKey() + ":" + entry.getValue().toString());
        }

        for (Map.Entry<Object, String> entry : searchMap.entrySet()) {
            logger.info(entry.getKey() + ":" + entry.getValue());
        }
        searchDataDTO.setDataMap(dataMap);
        searchDataDTO.setSearchMap(searchMap);

        return searchDataDTO;
    }

    // get file from classpath, resources folder
     static File getFileFromResources(String fileName) {

        URL resource = SearchDataProvider.class.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}