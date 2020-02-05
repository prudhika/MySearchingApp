package utils;

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
public class JSONReadFromFile {

    public static HashMap<String,JSONObject> dataMap=new HashMap<String, JSONObject>();
    public static HashMap<Object, String> searchMap=new HashMap<Object, String>();

    @SuppressWarnings("unchecked")
    public static void readFromFile() throws FileNotFoundException,IOException,ParseException{

            JSONParser parser=new JSONParser();


            Object obj=parser.parse(new FileReader("/Users/prudhii/Documents/SearchingApp/src/main/resources/users.json"));
            JSONArray jsonArray = (JSONArray) obj;

            ArrayList<String> result=new ArrayList<String>();
            for(int i=0 ; i< jsonArray.size();i++)
            {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String id = (String) jsonObject.get("_id").toString();
                dataMap.put(id,jsonObject);

                for(Object key : jsonObject.keySet()){
                    Object searchItem= jsonObject.get(key);
                    if(searchMap.containsKey(searchItem)) {
                        String value=searchMap.get(searchItem)
                                +"||"+ key
                                +"::"+id;
                        searchMap.put(jsonObject.get(key),value);
                    }
                    else{
                        Object searchKey=jsonObject.get(key);
                        searchMap.put(searchKey,key+ "::" +id);
                    }
                }
            }
           for (Map.Entry<String, JSONObject> entry : dataMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }

        for (Map.Entry<Object, String> entry : searchMap.entrySet()) {
           System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }
    }



}