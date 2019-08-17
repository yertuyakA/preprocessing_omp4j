import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TextData {
    public static void main(String[] args) throws IOException,InterruptedException {
 	String filename = "";
        
	File file = new File(filename);
        BufferedReader s = new BufferedReader(new FileReader(file));

        ArrayList<String> list = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 2427; i++) {
            list.add(s.readLine());
        }

        s.close();
       
        
	List<List<String>> tokenizeText = new ArrayList<>();

	//omp parallel for
	for(int i = 0; i<2000; i++){
	 PreprocessingBuilder preprocessing =
                new PreprocessingBuilder.Builder(list.get(i))
                .removeStopWords()
                .stemming()
                .build();

        //Здесь
	tokenizeText.add(preprocessing.getPreprocessingText());

	}

	long estimatedTime = System.currentTimeMillis() - startTime;

		 
	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++=");
	System.out.println("After: ");
	for(List<String> l : tokenizeText){
		System.out.println(l);
	}
	
	System.out.println("Size of list: " + tokenizeText.size());
	System.out.println(estimatedTime * 0.001+" millis");
    }
}
