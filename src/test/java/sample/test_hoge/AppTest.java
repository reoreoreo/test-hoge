package sample.test_hoge;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.TestCase;

public class AppTest extends TestCase {

    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
//        driver = new ChromeDriver();
//        String baseUrl = "https://www.google.co.jp/";
//        driver.get(baseUrl);

        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chrome);
        driver.get("https://www.google.co.jp/");
        driver.quit();
    }

    @Test
    public void testCase() throws Exception {
        Thread.sleep(13000);

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
