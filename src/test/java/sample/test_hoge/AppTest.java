package sample.test_hoge;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.TestCase;

public class AppTest extends TestCase {

    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "https://www.google.co.jp/";
        driver.get(baseUrl);
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
