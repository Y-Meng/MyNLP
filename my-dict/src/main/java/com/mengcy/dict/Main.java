package com.mengcy.dict;

import com.mengcy.dict.model.TrieDict;
import java.io.*;

/**
 * @author mengcy
 * @date 2018/10/20
 */
public class Main {

    public static void main(String[] args) throws IOException {

        TrieDict dict = new TrieDict();

        while (true) {
            System.out.println("请输入单词：");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String word = reader.readLine();

            if(word.equals("#exit")){
                System.exit(0);
            }

            String result = dict.get(word);
            if (result == null) {
                System.out.println("未查询到单词");
            }else {
                System.out.println(result);
            }
        }
    }

}
