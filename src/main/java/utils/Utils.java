package utils;

import actions.BingApiClient;
import actions.SoundCapture;
import actions.VoiceRecognizer;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by sebastian on 25.02.17.
 */
public class Utils {
    private final SoundCapture soundCapture;
    private final VoiceRecognizer voiceRecognizer;
    private final boolean textInput;
    private final static Utils instance = new Utils(true);

    public Utils(final boolean textInput) {
        this.soundCapture = new SoundCapture(
                5000,
                "/home/sebastian/recording.wav",
                AudioFileFormat.Type.WAVE
        );
        this.voiceRecognizer = new VoiceRecognizer(new BingApiClient(new DefaultHttpClient()));
        this.scanner = new Scanner(System.in);
        this.textInput = textInput;
    }

    private final Scanner scanner;

    public static String getUserInputStatic() {
        return instance.getUserInput();
    }

    public String getUserInput() {
        return textInput ? getUserTextInput() : getUserVoiceUnput();
    }

    private String getUserTextInput() {
        return scanner.nextLine();
    }

    private String getUserVoiceUnput() {
        try {
            final String filePath = soundCapture.capture();
            if (filePath.isEmpty()) {
                return "";
            }
            return voiceRecognizer.recognizeRecording(filePath);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void printLineMessageToUser(final String line) {
        System.out.println(line);
    }
}
