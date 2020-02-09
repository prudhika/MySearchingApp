package utils;

import org.json.simple.JSONObject;
public class JSONUtil {

    public static void printJsonObject(JSONObject jsonObj) {

//        LinkedHashMap<String,Object> result =
//        new ObjectMapper().readValue(jsonObj, HashMap.class);

        for (Object key : jsonObj.keySet()) {
            Object value = jsonObj.get(key);

            if (value instanceof JSONObject)
                printJsonObject((JSONObject)value);
            else
                System.out.println(key + generateStringOfLength(50-key.toString().length()) + value);
        }
        System.out.println("-----------------------------------------------------------");
    }

    public static String generateStringOfLength(int length)
    {
        StringBuffer outputBuffer = new StringBuffer(length);
        for (int i = 0; i < length; i++){
            outputBuffer.append(" ");
        }
        return outputBuffer.toString();
    }
}
