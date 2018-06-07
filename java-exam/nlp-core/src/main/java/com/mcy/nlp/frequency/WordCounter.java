package com.mcy.nlp.frequency;


import java.util.List;

/**
 * @author zkzc-mcy create at 2018/6/5.
 */
public interface WordCounter {

    /** 统计输入内容词频 */
    List<WordFrequency> count(List<String> contents);

    class WordFrequency implements Comparable< WordFrequency>{

        private final String word;
        private final int frequency;

        public WordFrequency(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        public String getWord() {
            return this.word;
        }

        public int getFrequency() {
            return this.frequency;
        }

        @Override
        public int compareTo(WordFrequency wordFrequency) {
            return wordFrequency.frequency - this.frequency;
        }

        @Override
        public String toString() {
            return "[word=" + this.word + ", frequency=" + this.frequency + "]";
        }
    }
}
