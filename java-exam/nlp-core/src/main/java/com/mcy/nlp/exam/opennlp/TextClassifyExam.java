package com.mcy.nlp.exam.opennlp;

import com.mcy.nlp.exam.util.ModelLoader;
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
        try {
            File dataIn = new File(getClass().getResource("/tc-model.train.txt").getPath());
            File modelFile = new File("C:/Z_DATA/export/tc-model.bin");
            if(!modelFile.exists()){
                modelFile.createNewFile();
            }

            ObjectStream<String> lineStream = new PlainTextByLineStream(
                    new MarkableFileInputStreamFactory(dataIn), "UTF-8");
            ObjectStream<DocumentSample> sampleObjectStream = new DocumentSampleStream(lineStream);

            DoccatModel model = DocumentCategorizerME.train(
                    "en", sampleObjectStream, new TrainingParameters(), new DoccatFactory());

            // 保存模型
            model.serialize(modelFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void classify(String[] doc){
        try {
            InputStream modelIn = new FileInputStream(new File("C:/Z_DATA/export/tc-model.bin"));
            DoccatModel model = new DoccatModel(modelIn);
            DocumentCategorizerME categorizer = new DocumentCategorizerME(model);

            double[] outcomes = categorizer.categorize(doc);
            for (int i = 0; i<categorizer.getNumberOfCategories(); i++) {
                String category = categorizer.getCategory(i);
                System.out.println(category + " - " + outcomes[i]);
            }
            System.out.println(categorizer.getBestCategory(outcomes));
            System.out.println(categorizer.getAllResults(outcomes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
