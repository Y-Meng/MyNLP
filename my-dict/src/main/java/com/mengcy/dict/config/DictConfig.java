package com.mengcy.dict.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author mengcy
 * @date 2018/10/20
 */
public class DictConfig {

    public static String DICT_PATH = "data/dict.txt";
    public static String DICT_ENCODE = "GBK";

    static {
        loadProperties();
    }

    private static void loadProperties() {

        InputStream stream = DictConfig.class.getResourceAsStream("/dict.properties");
        if(stream != null) {
            Properties properties = new Properties();
            try {
                properties.load(new InputStreamReader(stream, "GBK"));
                DICT_PATH = properties.getProperty("dict.path", "data/dict.txt");
                DICT_ENCODE = properties.getProperty("dict.encode", "GBK");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
