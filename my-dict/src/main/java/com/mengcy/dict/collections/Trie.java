package com.mengcy.dict.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengcy
 * @date 2018/10/20
 */
public class Trie<T> {

    private static class Node<T> {
        int code;
        T value;
        List<Node<T>> children;
    }

    private Node<T> root;
    private int size;

    public Trie(){
        size = 0;
        root = new Node<>();
        root.children = new ArrayList<>();
    }

    public T get(String key){

        int len = key.length();
        Node<T> curNode = root;
        for(int i = 0; i < len; i++){
            char c = key.charAt(i);
            if(curNode.children != null){
                boolean exist = false;
                for(Node<T> node : curNode.children){
                    if(node.code == c){
                        if(i == len - 1){
                            return node.value;
                        }else {
                            curNode = node;
                            exist = true;
                            break;
                        }
                    }
                }

                if(!exist){
                    return null;
                }
            }else {
                return null;
            }
        }
        return null;
    }

    public void put(String key, T value){

        int len = key.length();
        Node<T> curNode = root;
        for(int i = 0; i < len; i++){
            char c = key.charAt(i);

            if(curNode.children == null){
                curNode.children = new ArrayList();
            }

            boolean exist = false;
            for(Node<T> node : curNode.children){
                if(c == node.code){
                    curNode = node;
                    exist = true;
                    break;
                }
            }

            if(!exist){
                Node node = new Node();
                node.code = c;
                curNode.children.add(node);
                curNode = node;
            }

            if(i == len - 1){
                curNode.value = value;
            }
        }

        size += 1;
    }

    public int size() {
        return size;
    }

    public List<String> search(String prefix) {

        List<String> words = new ArrayList<>();

        Node<T> curNode = root;
        for(int i = 0, len = prefix.length(); i < len; i++){

            char c = prefix.charAt(i);
            if(root.children != null) {

                boolean exist = false;
                for (Node<T> node : curNode.children){
                    if(node.code == c){
                        if(i == len - 1){
                            words = listWords(prefix, node.children);
                        }else {
                            curNode = node;
                            break;
                        }
                    }
                }

                if(!exist){
                    break;
                }
            }
        }
        return words;
    }

    private List<String> listWords(String prefix, List<Node<T>> nodes) {

        List<String> words = new ArrayList<>();
        if(prefix != null){
            words.add(prefix);
        }

        if(nodes != null){
            for(Node<T> node : nodes){

                String word = prefix + (char) node.code;
                if(node.children != null) {
                    words.addAll(listWords(word, node.children));
                }else {
                    words.add(word);
                }
            }
        }
        return words;
    }
}
