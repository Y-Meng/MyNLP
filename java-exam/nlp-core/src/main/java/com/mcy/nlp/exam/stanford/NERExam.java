package com.mcy.nlp.exam.stanford;

import com.mcy.nlp.exam.util.TextReader;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

import java.util.List;

/**
 * @author zkzc-mcy create at 2018/6/14.
 */
public class NERExam {

    public void ner() throws Exception {

        CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(
                NERExam.class.getResourceAsStream("/standford/english.conll.4class.distsim.crf.ser.gz"));

        List<List<CoreLabel>> entiies = classifier.classify(TextReader.read("/en-text.txt"));

        for(List<CoreLabel> list : entiies){
            for(CoreLabel coreLabel : list){
                System.out.println(coreLabel.word() + ":" + coreLabel.get(CoreAnnotations.AnswerAnnotation.class));
            }
        }
    }

    public static void main(String[] args){
        try {
            new NERExam().ner();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
