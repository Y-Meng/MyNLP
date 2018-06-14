package com.mcy.nlp.exam.opennlp;

import opennlp.tools.cmdline.ModelLoader;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author zkzc-mcy create at 2018/6/7.
 */
public class TokenizeExam {

    public String[] tokenize(String content){

        try {

            InputStream is = getClass().getResourceAsStream("/opennlp/en-token.bin");

            TokenizerModel model = new TokenizerModel(is);
            Tokenizer tokenizer = new TokenizerME(model);

            String[] tokens = tokenizer.tokenize(content);

            Arrays.stream(tokens).forEach( s -> System.out.println(s));

            return tokens;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){

        new TokenizeExam().tokenize("The tokenize method is then applied, whose argument is the text to be tokenized. ");
    }
}
