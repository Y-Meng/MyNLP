package com.mcy.nlp.exam.stanford;

import com.mcy.nlp.exam.util.ModelLoader;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * @author zkzc-mcy create at 2018/6/20.
 */
public class POSExam {

    public void pos(){

        MaxentTagger maxentTagger = new MaxentTagger(ModelLoader.load("/standford/wsj-0-18-bidirectional-distsim.tagger"));

        try {
            List<List<HasWord>> sentences = MaxentTagger.tokenizeText(
                    new BufferedReader(
                            new FileReader(getClass().getResource("/en-text.txt").getFile())));

            for (List<HasWord> sentence : sentences) {
                List<TaggedWord> taggedSentence = maxentTagger.tagSentence(sentence);
                System.out.println(taggedSentence);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){

    }
}
