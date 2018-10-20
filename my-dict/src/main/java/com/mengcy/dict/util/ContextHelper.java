package com.mengcy.dict.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author mengcy
 * @date 2018/10/20
 */
public class ContextHelper {

    public static InputStream getFile(String path) throws FileNotFoundException {

        File file = new File(path);
        if(file.exists()){
            return new FileInputStream(file);
        }else {

            return ContextHelper.class.getResourceAsStream("/" + path);
        }
    }
}
