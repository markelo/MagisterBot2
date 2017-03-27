package speech.recognition.api;

import java.util.List;
import java.util.Map;

/**
 * Created by sebastian on 17.03.17.
 */
public class SpeechRecognitionResponse {
    private String version;
    private SpeechRecognitionResponseHeader header;
    private List<SpeechRecognitionResult> results;
    private Map<String, String> properties;

    public String getMostConfidentResult() {
        return results.stream()
                .sorted((o1, o2) -> Double.compare(o1.getConfidence(), o2.getConfidence()))
                .findFirst()
                .map(SpeechRecognitionResult::getLexical)
                .orElse("");
    }
}
