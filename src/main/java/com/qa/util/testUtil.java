package com.qa.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;

public class testUtil {

    public static String getValueBypath(JSONObject responseOfJson, String jpath) throws ParseException {
        Object obj = responseOfJson;
        for (String s : jpath.split("/"))
            if (!(s.contains("{") || s.contains("}")))
                obj = ((JSONObject) obj).get(s);
            else if (s.contains("[") || s.contains("]"))
                obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", " ")));
        return obj.toString();
    }

    public static String  JsonParsingJsonObject(JSONObject responseOfJson, String pathvalue) throws ParseException {
        String jObject= responseOfJson.get(pathvalue).toString();
        return jObject;
    }

   /* public static String JsonParsingJsonArray(JSONObject jsonResponse, String arrayPath) {
        JSONArray jArrayValue = (JSONArray) jsonResponse.get(arrayPath);
       int n = jArrayValue.length();
        Object itemId = null;
        for (int i = 0; i < n; i++) {
            itemId = jArrayValue.get(i);
            System.out.println(i+" item from the array is "+itemId);
            }
        return String.valueOf(itemId);

    }*/

    public static String JsonParsingJsonArray(JSONObject jsonResponse, String arrayPath) {
        JSONArray jArrayValue = (JSONArray) jsonResponse.get(arrayPath);
        int n = jArrayValue.length();
        Object itemId= null;
        String firstname = null;
        String lastname = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            //itemId = jArrayValue.get(i);
            JSONObject jsonobject = jArrayValue.getJSONObject(i);
            firstname = jsonobject.getString("first_name");
            lastname = jsonobject.getString("last_name");
            sb.append(firstname+"--"+lastname+"\n");
            //System.out.println(" FirstName from JSON Array 0 is  "+firstname);
            //System.out.println(" LastName  from JSON Array 0 is  "+lastname);
            //System.out.println(i+" item from the array is "+itemId);
        }
        return sb.toString();

    }
}
