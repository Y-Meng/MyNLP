package com.mcy.nlp.exam.opennlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author zkzc-mcy create at 2018/6/14.
 * 分句
 */
public class SentencesDetectorExam {

    public void getSentence(){
        try {
            InputStream inputStream = getClass().getResourceAsStream("/opennlp/en-sent.bin");
            SentenceModel sentenceModel = new SentenceModel(inputStream);

            URL path = getClass().getResource("/en-text.txt");
            List<String> lines = Files.readAllLines(Paths.get(path.toURI()));
            String content = StringUtils.join(lines.toArray());

            SentenceDetectorME detector = new SentenceDetectorME(sentenceModel);

            // 分句
            String[] sentences = detector.sentDetect(content);
            Arrays.stream(sentences).forEach(s -> System.out.println(s));

            // 句子概率
            double[]  probabilities = detector.getSentenceProbabilities();
            Arrays.stream(probabilities).forEach(p -> System.out.println(p));

            // 位置
            Span[] spans = detector.sentPosDetect(content);
            Arrays.stream(spans).forEach(span -> System.out.println(span));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new SentencesDetectorExam().getSentence();
    }
}
