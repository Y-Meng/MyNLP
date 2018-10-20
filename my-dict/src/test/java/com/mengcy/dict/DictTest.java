package com.mengcy.dict;

import com.mengcy.dict.model.HashDict;
import com.mengcy.dict.model.TrieDict;
import com.mengcy.dict.util.RuntimeUtil;
import org.junit.Test;

/**
 * @author mengcy
 * @date 2018/10/20
 */
public class DictTest {

    static String[] words = {
            "am", "before", "car", "doctor", "end", "find", "girl", "hit", "info", "jack"
    };

    @Test
    public void testMillion() {

        String million = "C:\\Z_WORK\\data\\十万词英汉词典词库\\million_dict.txt";

        // 1. hash dict
        testHashDict(words, million);
        System.out.println("---------------------------------------------");

        // 2. trie dict
        testTrieDict(words, million);
        System.out.println("---------------------------------------------");
    }

    @Test
    public void test100K() {
        // 1. hash dict
        testHashDict(words, null);
        System.out.println("---------------------------------------------");

        // 2. trie dict
        testTrieDict(words, null);
        System.out.println("---------------------------------------------");
    }

    private void testHashDict(String[] words, String path){

        System.out.println("Memory:" + RuntimeUtil.getUsedMemory()/1024/1024 + "M");
        HashDict dict;
        if(path == null) {
            dict = new HashDict();
        }else {
            dict = new HashDict(path,"UTF-8", 1400000);
        }
        System.out.println("Memory:" + RuntimeUtil.getUsedMemory()/1024/1024 + "M");

        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++){
            String key = words[i % words.length];
            String value = dict.get(key);
            if(i < 10){
                System.out.println(key + "==>" + value);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("hash dict：" + (end - start) + "ms/100万查询");
    }

    private void testTrieDict(String[] words, String path){

        System.out.println("Memory:" + RuntimeUtil.getUsedMemory()/1024/1024 + "M");

        TrieDict dict;
        if(path == null) {
            dict = new TrieDict();
        }else {
            dict = new TrieDict(path, "UTF-8");
        }

        System.out.println("Memory:" + RuntimeUtil.getUsedMemory()/1024/1024 + "M");

        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++){
            String key = words[i%10];
            String value = dict.get(key);
            if(i < 10){
                System.out.println(key + "==>" + value);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("trie dict：" + (end - start) + "ms/100万查询");
    }
}
