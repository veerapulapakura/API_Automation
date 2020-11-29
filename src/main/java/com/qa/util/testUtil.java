package com.qa.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;

public class testUtil {

    public static String getValueBypath(JSONObject responseOfJson, String jpath) throws ParseException {
        Object obj = responseOfJson;
        for (String s : jpath.split("/"))
            if (!(s.contains("[") || s.contains("]")))
                obj = ((JSONObject) obj).get(s);
            else if (s.contains("[") || s.contains("]"))
                obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
        return obj.toString();
    }

    public static String  JsonParsing(JSONObject responseOfJson, String pathvalue) throws ParseException {
        String perpage= responseOfJson.get(pathvalue).toString();
        return perpage;
           }
}
