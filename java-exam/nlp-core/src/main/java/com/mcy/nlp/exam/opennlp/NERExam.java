package com.mcy.nlp.exam.opennlp;

import com.mcy.nlp.exam.util.TextReader;
import opennlp.tools.namefind.*;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.*;
import opennlp.tools.util.eval.FMeasure;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author zkzc-mcy create at 2018/6/14.
 * 命名实体识别测试
 */
public class NERExam {

    public void ner() throws Exception {

        InputStream inputStream = NERExam.class.getResourceAsStream("/opennlp/en-token.bin");
        InputStream nerStream = NERExam.class.getResourceAsStream("/opennlp/en-ner-person.bin");

        TokenizerModel tokenizerModel = new TokenizerModel(inputStream);
        Tokenizer tokenizer = new TokenizerME(tokenizerModel);

        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(nerStream);
        NameFinderME nameFinder = new NameFinderME(tokenNameFinderModel);

        String[] tokens = tokenizer.tokenize(TextReader.readResource("/en-text.txt"));

        System.out.println("--------------------------------------------");
        Span[] names = nameFinder.find(tokens);
        // 概率
        double[] probs = nameFinder.probs();
        for (int i = 0; i < names.length ; i++){
            System.out.println( "entity:" + tokens[names[i].getStart()]);
            System.out.println( "prob:" + probs[i]);
        }
    }

    /**
     * 训练模型
     */
    public void train(){
        try {
            File input = new File(getClass().getResource("/ner-model.train.txt").getPath());
            ObjectStream<String> lineStream = new PlainTextByLineStream(
                    new MarkableFileInputStreamFactory(input), "UTF-8");

            ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);

            TokenNameFinderModel model = NameFinderME.train(
                    "en",
                    "person",
                    sampleStream,
                    new TrainingParameters(),
                    new TokenNameFinderFactory());

            File output = new File("C:/Z_DATA/export/ner-model.bin");
            if(!output.exists()){
                output.createNewFile();
            }
            model.serialize(output);

            // 模型评价
            evaluate(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 评价模型
     */
    public void evaluate(TokenNameFinderModel model) throws Exception {

        TokenNameFinderEvaluator evaluator = new TokenNameFinderEvaluator(new NameFinderME(model));
        File input = new File(getClass().getResource("/ner-model.evaluate.txt").getPath());
        ObjectStream<String> lineStream = new PlainTextByLineStream(
                new MarkableFileInputStreamFactory(input), "UTF-8");
        ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);
        evaluator.evaluate(sampleStream);
        FMeasure measure = evaluator.getFMeasure();
        System.out.println(measure.toString());
    }

    public static void main(String[] args){
        try {
            NERExam exam = new NERExam();
            exam.train();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
