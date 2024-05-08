package Model;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TextToSpeech {
    String textToSpeech;
    String language;
    String voiceName;
    String speedRate;

    public TextToSpeech(String textToSpeech, String language, String voiceName, String speedRate) {
        this.textToSpeech = textToSpeech;
        this.language = language;
        this.voiceName = voiceName;
        this.speedRate = speedRate;
    }

    public void speak() {
        String apiKey = "ec3d616154994f4a891ce5d297175493"; // Replace with your Voice RSS API key

        try {
            String apiUrl = "http://api.voicerss.org/?";
            String apiKeyParam = "key=" + URLEncoder.encode(apiKey, StandardCharsets.UTF_8);
            String textParam = "src=" + URLEncoder.encode(textToSpeech, StandardCharsets.UTF_8);
            String langParam = language;
            String voiceParam = "v=" + voiceName;
            String speedParam = "r=" + speedRate;

            URL url = new URL(apiUrl + apiKeyParam + "&" + textParam + "&" + langParam + "&" + voiceParam + "&" + speedParam);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                // Convert the audio format to WAV
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
                AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                        audioInputStream.getFormat().getSampleRate(),
                        16,
                        audioInputStream.getFormat().getChannels(),
                        audioInputStream.getFormat().getChannels() * 2,
                        audioInputStream.getFormat().getSampleRate(),
                        false);
                AudioInputStream convertedInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);

                // Play the converted audio
                Clip clip = AudioSystem.getClip();
                clip.open(convertedInputStream);
                clip.start();

                Thread.sleep(clip.getMicrosecondLength() / 1000);

                convertedInputStream.close();
                audioInputStream.close();
                bufferedInputStream.close();
                inputStream.close();
            } else {
                System.out.println("Error: " + responseCode);
            }

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
