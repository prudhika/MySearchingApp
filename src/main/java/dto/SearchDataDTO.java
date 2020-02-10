package dto;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * DTO for holding data map, search map and the type.
 */
public class SearchDataDTO {

    public SearchDataDTO(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }

    public Map<String, JSONObject> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, JSONObject> dataMap) {
        this.dataMap = dataMap;
    }

    public Map<Object, String> getSearchMap() {
        return searchMap;
    }

    public void setSearchMap(Map<Object, String> searchMap) {
        this.searchMap = searchMap;
    }

    String type;
    public Map<String, JSONObject> dataMap=new HashMap<String, JSONObject>();
    public Map<Object, String> searchMap=new HashMap<Object, String>();




}
