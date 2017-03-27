import actions.Action;
import actions.ActionFactory;
import actions.BingApiClient;
import actions.SoundCapture;
import com.beust.jcommander.internal.Lists;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import speech.recognition.api.SpeechRecognitionResponse;
import states.State;
import states.StateManager;
import utils.Utils;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.LineUnavailableException;
import java.io.*;
import java.util.List;


/**
 * Created by sebastian on 25.02.17.
 */
public class MainClass {
    //Get initial state
    //Get action based on this state
    //Run chosen action
    //Get next state based on number retrieved from action

    public static void main(final String[] args) throws IOException, LineUnavailableException {
        final StateManager stateManager = new StateManager();
        State state = stateManager.getInitialState();

        while(!state.isFinall()) {
            Action action = ActionFactory.getActionBasedOnType(state);
            final int nextStateNumber = action.perform();
            state = state.getChildState(nextStateNumber);
        }
    }

    public static void printMenu() {
        
    }
//        final HttpClient client = new DefaultHttpClient();
//        final HttpPost postRequest = new HttpPost("https://api.cognitive.microsoft.com/sts/v1.0/issueToken");
//        postRequest.setHeader("Ocp-Apim-Subscription-Key", "09690513a95a45918910e316d29692c7");
//        final Utils utils = new Utils();
//        final String input = utils.getUserVoiceUnput();
//        System.out.println(input);
//        final HttpResponse response = client.execute(postRequest);
//        final BufferedReader reader = new BufferedReader(
//                new InputStreamReader(
//                        response.getEntity().getContent()
//                )
//        );
//        final StringBuilder builder = new StringBuilder();
//        String line = "";
//        while ((line = reader.readLine()) != null) {
//            builder.append(line);
//        }
//
////        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzY29wZSI6Imh0dHBzOi8vc3BlZWNoLnBsYXRmb3JtLmJpbmcuY29tIiwic3Vic2NyaXB0aW9uLWlkIjoiMTVmOTY0NTRlNTIxNGNiYzk5OWNmZTU4MTNlNGQ5YWQiLCJwcm9kdWN0LWlkIjoiQmluZy5TcGVlY2guUHJldmlldyIsImNvZ25pdGl2ZS1zZXJ2aWNlcy1lbmRwb2ludCI6Imh0dHBzOi8vYXBpLmNvZ25pdGl2ZS5taWNyb3NvZnQuY29tL2ludGVybmFsL3YxLjAvIiwiYXp1cmUtcmVzb3VyY2UtaWQiOiIiLCJpc3MiOiJ1cm46bXMuY29nbml0aXZlc2VydmljZXMiLCJhdWQiOiJ1cm46bXMuc3BlZWNoIiwiZXhwIjoxNDg5NTM3MTI5fQ.59WjifHG9pR-qKKv4Pin5gvuMPv-cBXhS6RLDCiSuso";
//        System.out.println(builder.toString());
//        final String recognizePath = createRecognizeRequestPath();
//        final HttpPost recognizeRequest = new HttpPost(recognizePath);
//        recognizeRequest.setEntity(new ByteArrayEntity(byteArray));
//        recognizeRequest.setHeader("Authorization", "Bearer " + token);
//        final HttpResponse response = client.execute(recognizeRequest);
//        final StringBuilder builder = new StringBuilder();
//        String line = "";
//        final BufferedReader reader = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent())
//        );
//        while((line = reader.readLine()) != null) {
//            builder.append(line);
//        }
//        System.out.println(builder.toString());

//        final BingApiClient client = new BingApiClient(new DefaultHttpClient());
//        final SpeechRecognitionResponse response = client.recognizeSpeech(byteArray);
//        System.out.println(response);
//    }

    private static String createRecognizeRequestPath() {
        return "https://speech.platform.bing.com/recognize" +
                "?scenarios=ulm" +
                "&version=3.0" +
                "&requestid=b2c95ede-97eb-4c88-81e4-80f32d6aee54" +
                "&appID=D4D52672-91D7-4C74-8AD8-42B1D98141A5" +
                "&format=json" +
                "&locale=en-US" +
                "&device.os=wp7" +
                "&instanceid=b2c95ede-97eb-4c88-81e4-80f32d6aee54";
    }
}

//    public static void main(final String[] args) throws InterruptedException {
//        System.setProperty("webdriver.gecko.driver", "/usr/bin/firefox");
//        System.setProperty("webdriver.chrome.driver", "/home/sebastian/Downloads/chromedriver");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.bankmillennium.pl/logowanie");
//        WebElement element = driver.findElement(By.name("q"));
//        element.sendKeys("Cheese!");
//        element.submit();
//        System.out.println("Page title is" + driver.getTitle());
//
//        (new WebDriverWait(driver, 10)).until(webDriver -> webDriver.getTitle().toLowerCase().startsWith("cheese!"));
//        System.out.println("Page title is " + driver.getTitle());
//
//        driver.quit();
//        Thread.sleep(5000);
//        WebElement idFillInElement = driver.findElements(By.id("millekod")).get(1);
//        idFillInElement.sendKeys("19663319");
//        WebElement submitButton = driver.findElements(By.cssSelector("button[type='submit']")).get(1);
//        submitButton.click();
//        Thread.sleep(2000);
//        WebElement passwordInput = driver.findElement(By.id("ctl00_Content_Login_PasswordOne_txtContent"));
//        passwordInput.sendKeys("19920110");
//        List<WebElement> peselFields = findPeselInputs(driver);
//        fillInPeseleFields(peselFields);
//        submitButton = driver.findElement(By.id("BtnLogin"));
//        submitButton.click();
//        Thread.sleep(5000);
//        driver.quit();
//    }

//    private static List<WebElement> findPeselInputs(WebDriver driver) {
//        final String formatString = "ctl00_Content_Login_SecurityDigits_Password_Pesel_Container_Pesel_%d_txtContent";
//        final List<WebElement> elements = Lists.newLinkedList();
//        for (int i = 0; i < 11; i++) {
//            elements.add(
//                    driver.findElement(
//                            By.id(String.format(formatString, i))
//                    )
//            );
//        }
//        return elements;
//    }
//
//    private static void fillInPeseleFields(final List<WebElement> elements) {
//        for (int i = 0; i < elements.size(); i++) {
//            final WebElement element = elements.get(i);
//            if (element.getAttribute("disabled") == null) {
//                System.out.println(String.format("Podaj %d cyfrę peselu", i));
//                String number = Utils.getUserTextInput();
//                element.sendKeys(number);
//            }
//        }
//    }
//}
