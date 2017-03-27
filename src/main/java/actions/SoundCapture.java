package actions;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * Created by sebastian on 15.03.17.
 */
public class SoundCapture {
    private final long recordTime;
    private final File filePath;
    private final AudioFormat audioFormat;
    private final AudioFileFormat.Type fileType;

    public SoundCapture(
            final long recordTime,
            final String filePath,
            final AudioFileFormat.Type fileType
    ) {
        this.recordTime = recordTime;
        this.filePath = new File(filePath);
        this.audioFormat = getAudioFormat();
        this.fileType = fileType;
    }

    public String capture() throws LineUnavailableException, IOException {
        final DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line is not supported");
            return "";
        }
        TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
        targetDataLine.open(audioFormat);
        targetDataLine.start();
        System.out.println("Started capturing");
        new Thread(() -> {
            try {
                Thread.sleep(recordTime);
                targetDataLine.stop();
                targetDataLine.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);
        System.out.println("Started recording...");
        AudioSystem.write(audioInputStream, fileType, filePath);
        System.out.println("Record finished");
        return filePath.getPath();
    }

    private AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        return new AudioFormat(sampleRate, sampleSizeInBits,
                channels, true, true);
    }
}
