package com.mengcy.dict.model;

import com.mengcy.dict.config.DictConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mengcy
 * @date 2018/10/20
 */
public class HashDict extends BaseDict{

    /** 默认10万词 */
    private Map<String, String> dict = new HashMap(140000);

    public HashDict(){
        load(DictConfig.DICT_PATH, DictConfig.DICT_ENCODE);
    }

    public HashDict(String path, String encode){
        load(path, encode);
    }

    public HashDict(String path, String encode, int size){
        dict = new HashMap<>(size);
        load(path, encode);
    }

    @Override
    public int size() {
        return dict.size();
    }

    @Override
    public void put(String key, String value) {
        dict.put(key, value);
    }

    @Override
    public String get(String word){
        return dict.get(word);
    }

    @Override
    public List<String> searchWords(String prefix) {

        List<String> words = new ArrayList<>();
        for(Map.Entry<String, String> entry : dict.entrySet()){
            if(entry.getKey().startsWith(prefix)){
                words.add(entry.getKey());
            }
        }
        return words;
    }
}
