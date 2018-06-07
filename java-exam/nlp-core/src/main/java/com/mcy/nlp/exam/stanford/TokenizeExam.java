package com.mcy.nlp.exam.stanford;

import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;

import java.io.StringReader;

/**
 * @author zkzc-mcy create at 2018/6/7.
 */
public class TokenizeExam {

    public static void main(String[] args){

        PTBTokenizer ptb = new PTBTokenizer(
                new StringReader("The tokenize method is then applied, whose argument is the text to be tokenized. "),
                new CoreLabelTokenFactory(),
                null
        );

        while (ptb.hasNext()){
            System.out.println(ptb.next());
        }
    }
}
