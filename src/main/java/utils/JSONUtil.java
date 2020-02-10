package utils;

import org.json.simple.JSONObject;
public class JSONUtil {

    /**
     * This method will print the results in the required format
     * for a specific entry in users,tickets or orgs input.
     * @param jsonObj represents each entity in any of the given inputs
     */
    public static void printJsonObject(JSONObject jsonObj) {

//        LinkedHashMap<String,Object> result =
//        new ObjectMapper().readValue(jsonObj, HashMap.class);

        for (Object key : jsonObj.keySet()) {
            Object value = jsonObj.get(key);

            if (value instanceof JSONObject)
                printJsonObject((JSONObject)value);
            else
                System.out.println(key + generateStringOfLength(50-key.toString().length()) + value); // prints as mentioned in the doc
        }
        System.out.println("-----------------------------------------------------------"); // separator
    }

    /**
     * This method will generate the number of blank spaces which needs
     * to be appended to the fields while printing the output to the console.
     * @param length of string to be generated.
     * @return str which represnts the string of spaces.
     */
    public static String generateStringOfLength(int length)
    {
        StringBuffer outputBuffer = new StringBuffer(length);
        for (int i = 0; i < length; i++){
            outputBuffer.append(" ");
        }
        return outputBuffer.toString();
    }
}
