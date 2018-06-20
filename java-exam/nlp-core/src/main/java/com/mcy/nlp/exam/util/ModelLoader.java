package com.mcy.nlp.exam.util;

import java.io.InputStream;

/**
 * @author zkzc-mcy create at 2018/6/20.
 */
public class ModelLoader {

    public static InputStream load(String model){
        InputStream inputStream = ModelLoader.class.getResourceAsStream(model);
        return inputStream;
    }
}
