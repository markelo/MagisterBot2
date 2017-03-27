package actions;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpPost;
import speech.recognition.api.SpeechRecognitionResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by sebastian on 16.03.17.
 */
public class VoiceRecognizer {
    final private BingApiClient apiClient;

    public VoiceRecognizer(final BingApiClient bingApiClient) {
        this.apiClient = bingApiClient;
    }

    public String recognizeRecording(final String recordingFilePath) {
        final File file = new File(recordingFilePath);
        if (!file.exists() || file.isDirectory()) {
            System.out.println(recordingFilePath + " doesn't exists or is directory");
            return "";
        }
        final byte[] fileAsByteArray = convertFileToByteArray(file);
        if (file.length() == 0) {
            return "";
        }
        final SpeechRecognitionResponse response = apiClient.recognizeSpeech(fileAsByteArray);
        return response.getMostConfidentResult();
    }

    private byte[] convertFileToByteArray(final File file) {
        try {
            return IOUtils.toByteArray(new FileInputStream(file));
        } catch (IOException e) {
            System.out.println("Error during converting file to byte array");
            e.printStackTrace();
            return new byte[0];
        }
    }
}
