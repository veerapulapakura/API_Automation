package com.qa.base;

import java.io.*;
import java.util.Properties;

public class Testbase {

    public Properties prop;
    public int RESPONSE_STATUS_CODE_200 = 200;
    public int RESPONSE_STATUS_CODE_100 = 100;
    public int RESPONSE_STATUS_CODE_300 = 300;
    public int RESPONSE_STATUS_CODE_400 = 400;
    public int RESPONSE_STATUS_CODE_500 = 500;

    public Testbase() {
        prop = new Properties();
        try {
            FileInputStream fip = null;
            fip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
            prop.load(fip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
