package com.mcy.nlp.exam.util;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author zkzc-mcy create at 2018/6/14.
 */
public class TextReader {

    public static String read(String path) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(path));
        String content = StringUtils.join(lines.toArray());
        return content;
    }

    public static String readResource(String res) throws Exception{
        URL path = TextReader.class.getResource(res);
        List<String> lines = Files.readAllLines(Paths.get(path.toURI()));
        String content = StringUtils.join(lines.toArray());
        return content;
    }
}
