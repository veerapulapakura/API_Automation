package com.qa.tests;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.Testbase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostApiTest extends Testbase {
    Testbase testbase;
    String serviceURL;
    String appURL;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() throws IOException {
        testbase = new Testbase();
        serviceURL = prop.getProperty("URL");
        appURL = prop.getProperty("serviceURL");

        url = serviceURL+appURL;
        System.out.println(url);
    }

    @Test
    public void PostApiTest()  throws JsonGenerationException, JsonMappingException, IOException {
        restClient = new RestClient();
        HashMap<String,String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "appliation/json");

        //jackson API:
        ObjectMapper mapper = new ObjectMapper();
        Users users = new Users("Veera", "QA Engineer"); //expected users obejct


        //object to json file:
        mapper.writeValue(new File("src/main/java/com/qa/data/Users.json"), users);

        //java object to json in String:
        String usersJsonString = mapper.writeValueAsString(users);
        System.out.println(usersJsonString);

        closeableHttpResponse = restClient.post(url, usersJsonString, headermap); //call the API

        //validate response from API:
        //1. status code:
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, testbase.RESPONSE_STATUS_CODE_201);

        //2. JsonString:
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("The response from API is: \n\n"+ responseJson);

        //json to java object:
        Users usersResObj = mapper.readValue(responseString, Users.class); //actual users object
        System.out.println("Reading, converting JSON reaspnse into our class type ----\n\n"+usersResObj);

        Assert.assertFalse(users.getName().equals(usersResObj.getId()));
        Assert.assertFalse(users.getJob().equals(usersResObj.getCreatedAt()));

        System.out.println("Printing the id \n\n"+usersResObj.getId());
        System.out.println("Printing the created at  "+usersResObj.getCreatedAt());
         }

}
