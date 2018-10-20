package com.mengcy.dict.model;

import com.mengcy.dict.collections.Trie;
import com.mengcy.dict.config.DictConfig;

import java.util.List;

/**
 * @author mengcy
 * @date 2018/10/20
 */
public class TrieDict extends BaseDict{

    static Trie<String> dict = new Trie<>();

    public TrieDict(){
        load(DictConfig.DICT_PATH, DictConfig.DICT_ENCODE);
    }

    public TrieDict(String path, String encode){
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
    public String get(String key) {
        return dict.get(key);
    }

    @Override
    public List<String> searchWords(String prefix) {
        return dict.search(prefix);
    }
}
