package com.qa.client;

import netscape.javascript.JSObject;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class RestClient {

    // GET Method
    public void get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // Hitting the GET URL

        // Status code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code   "+statusCode);

        // Response
        System.out.println("Response");
        String responsrString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        //JSONObject jsonResponse = new JSONObject(responsrString);
        JSONObject jsonResponse = new JSONObject(responsrString.trim());
        //JSONObject allCDs = new JSONObject(objectString.replace(/^\s+/,""));
        System.out.println("JSON Response is  "+jsonResponse);

        Header[] headersArray= closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allheaders= new  HashMap<String, String>();
        for(Header header:headersArray ) {
            allheaders.put(header.getName(), header.getValue());
        }
        System.out.println("All headers are   "+  allheaders);

    }
}
