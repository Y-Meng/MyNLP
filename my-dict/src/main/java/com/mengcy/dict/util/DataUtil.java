package com.mengcy.dict.util;

import java.io.*;

/**
 * @author mengcy
 * @date 2018/10/20
 */
public class DataUtil {

    public static void js2TxtDict() throws IOException{
        File txt = new File("C:\\Z_WORK\\data\\十万词英汉词典词库\\dict.txt");
        if(!txt.exists()){
            txt.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(txt));

        File file = new File("C:\\Z_WORK\\data\\十万词英汉词典词库\\E2Cdictionary.js");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null){

            if(line.startsWith("$")){
                line = line.replace("$", "")
                        .replace(":\"", "\t")
                        .replace("\"", "");
                writer.write(line);
                writer.newLine();
            }

            line = reader.readLine();
        }

        reader.close();
        writer.close();
    }

    /***
     * 造个百万测试词典
     * @throws IOException
     */
    public static void js2TxtMillionDict() throws IOException{

        File txt = new File("C:\\Z_WORK\\data\\十万词英汉词典词库\\million_dict.txt");
        if(!txt.exists()){
            txt.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(txt));

        File file = new File("C:\\Z_WORK\\data\\十万词英汉词典词库\\E2Cdictionary.js");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null){

            if(line.startsWith("$")){
                line = line.replace("$", "")
                        .replace(":\"", "\t")
                        .replace("\"", "");

                for(int i = 0; i < 10; i++) {
                    if(i > 0) {
                        writer.write(i + line);
                    }else {
                        writer.write(line);
                    }
                    writer.newLine();
                }
            }

            line = reader.readLine();
        }

        reader.close();
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        js2TxtMillionDict();
    }
}
