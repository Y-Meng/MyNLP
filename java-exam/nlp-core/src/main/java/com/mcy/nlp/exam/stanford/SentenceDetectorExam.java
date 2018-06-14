package com.mcy.nlp.exam.stanford;

import com.mcy.nlp.exam.util.TextReader;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.WordToSentenceProcessor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;

/**
 * @author zkzc-mcy create at 2018/6/14.
 */
public class SentenceDetectorExam {

    public void getSentence(){
        String path = getClass().getResource("/en-text.txt").getPath();
        try {


            // 分词组句
            PTBTokenizer ptbTokenizer = new PTBTokenizer(new FileReader(path),
                    new CoreLabelTokenFactory(), null);

            WordToSentenceProcessor wtsp = new WordToSentenceProcessor();
            List<List<CoreLabel>> sents = wtsp.process(ptbTokenizer.tokenize());
            sents.stream().forEach(s -> System.out.println(s));
            System.out.println("-----------------------------------------");

            // 使用文档处理类
            DocumentPreprocessor dp = new DocumentPreprocessor(new FileReader(path));
            for(List sent : dp){
                System.out.println(sent);
            }
            System.out.println("-----------------------------------------");

            // StanfordCoreNLP
            Properties properties = new Properties();
            properties.put("annotators", "tokenize, ssplit");
            StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
            Annotation annotation = new Annotation(TextReader.readResource("/en-text.txt"));
            pipeline.annotate(annotation);
            pipeline.prettyPrint(annotation, System.out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new SentenceDetectorExam().getSentence();
    }
}
