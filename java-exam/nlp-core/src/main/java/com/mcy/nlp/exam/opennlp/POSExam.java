package com.mcy.nlp.exam.opennlp;

import com.mcy.nlp.exam.util.ModelLoader;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Span;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zkzc-mcy create at 2018/6/20.
 */
public class POSExam {

    private static String[] sentence = {"The", "voyage", "of", "the",
            "Abraham", "Lincoln", "was", "for", "a", "long", "time", "marked",
            "by", "no", "special", "incident."};

    String theSentence = "The voyage of the Abraham Lincoln was for a "
            + "long time marked by no special incident.";


    public void pos(){
        try (InputStream modelIn = ModelLoader.load("/opennlp/en-pos-maxent.bin");
             InputStream chunkerIn = ModelLoader.load("/opennlp/en-chunker.bin")){

            // 1.单词标注
            POSModel model = new POSModel(modelIn);
            POSTaggerME taggerME = new POSTaggerME(model);

            String[] taggers = taggerME.tag(sentence);

            for (int i = 0; i < sentence.length; i++){
                System.out.print(sentence[i] + "/" + taggers[i] + " ");
            }
            System.out.print("\n");

            // 2.词组标注
            ChunkerModel chunkerModel = new ChunkerModel(chunkerIn);
            ChunkerME chunkerME = new ChunkerME(chunkerModel);

            String[] result = chunkerME.chunk(sentence, taggers);
            for(int i = 0; i < result.length; i++){
                System.out.println("[" + sentence[i] + "]" + result[i]);
            }

            // 3.词组标注
            Span[] spans = chunkerME.chunkAsSpans(sentence, taggers);
            for(Span span : spans){
                System.out.println(span.getType() + ":" + span.getStart() + "-" + span.getEnd() + ":" + span.length());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void usePOSDictionary(){

    }

    public static void main(String[] args){

        POSExam exam = new POSExam();
        exam.pos();
    }
}
