package actions;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import speech.recognition.api.SpeechRecognitionResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * Created by sebastian on 16.03.17.
 */
public class BingApiClient {
    final private HttpClient httpClient;
    final private String token;
    final private String subscriptionKey;

    public BingApiClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
        this.subscriptionKey = "09690513a95a45918910e316d29692c7";
        this.token = retrieveJWTToken();
    }

    public SpeechRecognitionResponse recognizeSpeech(final byte[] bytes) {
        final HttpPost postRequest = new HttpPost(createRecognizeRequestPath());
        postRequest.setEntity(new ByteArrayEntity(bytes));
        postRequest.setHeader("Authorization", "Bearer " + token);
        try {
            final HttpResponse response = httpClient.execute(postRequest);
            final String responseAsString =  buildStringFromResponse(response);
            return new Gson().fromJson(responseAsString, SpeechRecognitionResponse.class);
        } catch (IOException e) {
            System.out.println("Request to recognize speech fragment has failed");
            e.printStackTrace();
            return null;
        }
    }

    private String retrieveJWTToken() {
        final HttpPost postRequest = new HttpPost("https://api.cognitive.microsoft.com/sts/v1.0/issueToken");
        postRequest.setHeader("Ocp-Apim-Subscription-Key", "09690513a95a45918910e316d29692c7");
        try {
            final HttpResponse response = httpClient.execute(postRequest);
            return buildStringFromResponse(response);
        } catch (IOException e) {
            System.out.println("Failed to retrieve JWT token. Speech recognition will not work.");
            e.printStackTrace();
            return "";
        }
    }

    private String buildStringFromResponse(final HttpResponse response) throws IOException {
        final StringBuilder builder = new StringBuilder();
        String line;
        final BufferedReader reader = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent())
        );
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    private String createRecognizeRequestPath() {
        return "https://speech.platform.bing.com/recognize" +
                "?scenarios=ulm" +
                "&version=3.0" +
                "&requestid=" + UUID.randomUUID() +
                "&appID=D4D52672-91D7-4C74-8AD8-42B1D98141A5" +
                "&format=json" +
                "&locale=en-US" +
                "&device.os=wp7" +
                "&instanceid=b2c95ede-97eb-4c88-81e4-80f32d6aee54";
    }
}
