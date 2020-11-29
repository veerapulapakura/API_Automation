package com.qa.tests;
import com.qa.base.Testbase;
import com.qa.client.RestClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetApiTest extends Testbase {
    Testbase testbase;
    String serviceURL;
    String appURL;
    String url;
    RestClient restClient;

    @BeforeMethod
    public void setUp() throws IOException {
        testbase = new Testbase();
        serviceURL = prop.getProperty("URL");
        appURL = prop.getProperty("serviceURL");
        url = serviceURL+appURL;

    }
    @Test
    public void getApiTest() throws IOException {
        restClient = new RestClient();
        restClient.get(url);
    }
}
