package com.qa.base;

import java.io.*;
import java.util.Properties;

public class Testbase {

    public Properties prop;

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
