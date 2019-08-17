import com.github.chen0040.data.text.*;

import java.util.ArrayList;
import java.util.List;

public class PreprocessingBuilder {
    private final String text;
    private List<String> tokenizeText;

    public static class Builder{
        private final String text;
        private List<String> tokenized;

        public Builder stemming(){
            TextFilter stemmer = new PorterStemmer();
            tokenized = stemmer.filter(tokenized);
            return this;
        }

        public Builder deletePunct(){
           // Работает неправильно, его заменяет StopWordRemoval
	   // TextFilter punctuationFilter = new PunctuationFilter();
           // tokenized = punctuationFilter.filter(tokenized);
            return this;
        }
        public Builder removeStopWords(){
            StopWordRemoval deleteStopWords = new StopWordRemoval();
            tokenized = deleteStopWords.filter(tokenized);
            return this;
        }

        public Builder(String text) {
            this.text = text;
            tokenized = BasicTokenizer.doTokenize(text.toLowerCase());
        }
        public PreprocessingBuilder build(){
            return new PreprocessingBuilder(this);
        }
    }
    private PreprocessingBuilder(Builder builder){
        text = builder.text;
        tokenizeText = builder.tokenized;
    }

    public List<String> getPreprocessingText(){
        return tokenizeText;
    }

}
