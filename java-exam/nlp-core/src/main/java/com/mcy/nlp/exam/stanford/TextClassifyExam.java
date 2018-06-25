package com.mcy.nlp.exam.stanford;

import opennlp.tools.doccat.*;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.*;

/**
 * @author zkzc-mcy create at 2018/6/25.
 */
public class TextClassifyExam {


    void train(){

    }

    void classify(String[] doc){

    }

    public static void main(String[] args){

        String[] toto = ("dog belongs to Dorothy Gale, the heroine of "
                + "the first and many subsequent books. In the first "
                + "book, he never spoke, although other animals, native "
                + "to Oz, did. In subsequent books, other animals "
                + "gained the ability to speak upon reaching Oz or "
                + "similar lands, but Toto remained speechless.").split(" ");

        String[] calico = ("This cat is also known as a calimanco cat or "
                + "clouded tiger cat, and by the abbreviation 'tortie'. "
                + "In the cat fancy, a tortoiseshell cat is patched "
                + "over with red (or its dilute form, cream) and black "
                + "(or its dilute blue) mottled throughout the coat.").split(" ");

        TextClassifyExam exam = new TextClassifyExam();
        exam.train();
        exam.classify(toto);
        exam.classify(calico);
    }
}
