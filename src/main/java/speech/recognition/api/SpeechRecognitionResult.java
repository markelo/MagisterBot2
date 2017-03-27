package speech.recognition.api;

import java.util.List;

/**
 * Created by sebastian on 17.03.17.
 */
public class SpeechRecognitionResult {
    private String name;
    private String lexical;
    private List<Token> tokens;
    private double confidence;


    public double getConfidence() {
        return confidence;
    }

    public String getLexical() {
        return lexical;
    }
}
