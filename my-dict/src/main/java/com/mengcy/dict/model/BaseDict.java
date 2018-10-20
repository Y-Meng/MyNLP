package com.mengcy.dict.model;

import com.mengcy.dict.config.DictConfig;
import com.mengcy.dict.util.ContextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * @author mengcy
 * @date 2018/10/20
 */
public abstract class BaseDict {

    static Logger logger = LoggerFactory.getLogger(BaseDict.class);

    public BaseDict(){}

    protected void load(String path, String encode){

        logger.info("dict path:" + path);

        long start = System.currentTimeMillis();
        try {
            InputStream inputStream = ContextHelper.getFile(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encode));
            String line = reader.readLine();
            while (line != null){
                String[] item = line.split("\t");
                if(item.length == 2){
                    put(item[0], item[1]);
                }
                line = reader.readLine();
            }
            reader.close();
            inputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        long end = System.currentTimeMillis();
        logger.info("dict size：" + size());
        logger.info("load time：" + (end - start) + "ms");
    }

    public abstract int size();

    public abstract void put(String key, String value);

    public abstract String get(String key);

    public abstract List<String> searchWords(String prefix);
}
