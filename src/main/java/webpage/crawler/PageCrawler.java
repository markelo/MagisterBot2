package webpage.crawler;


import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.util.List;

public class PageCrawler {
    public static void main(final String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "/usr/bin/firefox");
        System.setProperty("webdriver.chrome.driver", "/Users/Sebastian/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bankmillennium.pl/logowanie");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("Page title is" + driver.getTitle());

        (new WebDriverWait(driver, 10)).until(webDriver -> webDriver.getTitle().toLowerCase().startsWith("cheese!"));
        System.out.println("Page title is " + driver.getTitle());

        driver.quit();
        Thread.sleep(5000);
        WebElement idFillInElement = driver.findElements(By.id("millekod")).get(1);
        idFillInElement.sendKeys("19663319");
        WebElement submitButton = driver.findElements(By.cssSelector("button[type='submit']")).get(1);
        submitButton.click();
        Thread.sleep(2000);
        WebElement passwordInput = driver.findElement(By.id("ctl00_Content_Login_PasswordOne_txtContent"));
        passwordInput.sendKeys("19920110");
        List<WebElement> peselFields = findPeselInputs(driver);
        fillInPeseleFields(peselFields);
        submitButton = driver.findElement(By.id("BtnLogin"));
        submitButton.click();
        Thread.sleep(5000);
        driver.quit();
    }

    private static List<WebElement> findPeselInputs(WebDriver driver) {
        final String formatString = "ctl00_Content_Login_SecurityDigits_Password_Pesel_Container_Pesel_%d_txtContent";
        final List<WebElement> elements = Lists.newLinkedList();
        for (int i = 0; i < 11; i++) {
            elements.add(
                    driver.findElement(
                            By.id(String.format(formatString, i))
                    )
            );
        }
        return elements;
    }

    private static void fillInPeseleFields(final List<WebElement> elements) {
        for (int i = 0; i < elements.size(); i++) {
            final WebElement element = elements.get(i);
            if (element.getAttribute("disabled") == null) {
                System.out.println(String.format("Podaj %d cyfrę peselu", i));
                String number = Utils.getUserInputStatic();
                element.sendKeys(number);
            }
        }
    }
}