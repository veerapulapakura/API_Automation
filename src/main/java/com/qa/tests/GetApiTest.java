package com.qa.tests;
import com.qa.base.Testbase;
import com.qa.client.RestClient;
import com.qa.util.testUtil;
import junit.framework.Assert;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class GetApiTest extends Testbase {
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

    }
    @Test
    public void getApiTest() throws IOException, ParseException {
        restClient = new RestClient();
        closeableHttpResponse=restClient.get(url);

        // Status code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code   "+statusCode);
        Assert.assertEquals("Status code is not 200", RESPONSE_STATUS_CODE_200,statusCode);
        Assert.assertNotSame("Status code is not 200", RESPONSE_STATUS_CODE_300,statusCode);


        // Response
        System.out.println("Response");
        String responsrString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        JSONObject jsonResponse = new JSONObject(responsrString.trim());
        System.out.println("JSON Response is  "+jsonResponse);

        // Single value assertions

       /* String perpage = testUtil.getValueBypath(jsonResponse, "/per_page"  );
        System.out.println("Printing perpage from GetApi method  " + perpage );
        Assert.assertEquals(Integer.parseInt(perpage), 6);
*/
        String perpage = testUtil.JsonParsingJsonObject(jsonResponse, "per_page"  );
        System.out.println("Printing perpage from GetApi method  " + perpage );
        Assert.assertEquals(Integer.parseInt(perpage), 6);

        String totalValue = testUtil.JsonParsingJsonObject(jsonResponse,"total");
        System.out.println("Value of per_page is   "+ totalValue);
        Assert.assertEquals(Integer.parseInt(totalValue), 12);


        // getting data from the Array
        String Lastname = testUtil.JsonParsingJsonArray(jsonResponse, "data");
        System.out.println("Json array from the response is   "+ Lastname);


        /*JSONArray Firstname = testUtil.JsonParsingJsonArray(jsonResponse, "data");
        JSONArray Acatar = testUtil.JsonParsingJsonArray(jsonResponse, "data");
        JSONArray ID = testUtil.JsonParsingJsonArray(jsonResponse, "data");

*/
        // Getting all headers into Hash Map
        Header[] headersArray= closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allheaders= new  HashMap<String, String>();
        for(Header header:headersArray ) {
            allheaders.put(header.getName(), header.getValue());
        }
        System.out.println("All headers are   "+  allheaders);

    }
}
