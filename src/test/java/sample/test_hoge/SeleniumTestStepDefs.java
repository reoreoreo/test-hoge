package sample.test_hoge;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sample.test_hoge.entity.SampleFromEntity;
import sample.test_hoge.enums.Sex;

public class SeleniumTestStepDefs {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        baseUrl = "http://devlop-working.org";
        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chrome);

    }

    @When("^DWサンプルフォームにアクセスします。$")
    public void dwサンプルフォームにアクセスします() throws Throwable {
        driver.get(baseUrl + "/bookmarklet/web/dw/sample-form.php");
    }

    @Then("^メッセージラベルを確認する。\"([^\"]*)\"$")
    public void メッセージラベルを確認する(String arg1) throws Throwable {
        assertEquals(arg1, driver.findElement(By.cssSelector("p")).getText());
    }

    @When("^フォームの項目を入力します。$")
    public void フォームの項目を入力します(List<SampleFromEntity> sampleFromEntityList) throws Throwable {

        SampleFromEntity sampleFromEntity = sampleFromEntityList.get(0);

        // 氏名
        driver.findElement(By.id("simei")).clear();
        driver.findElement(By.id("simei")).sendKeys(sampleFromEntity.get氏名());

        // 誕生日
        final int idx_year = sampleFromEntity.get生年月日().indexOf("年");
        final int idx_month = sampleFromEntity.get生年月日().indexOf("月");
        final int idx_day = sampleFromEntity.get生年月日().indexOf("日");
        final String year = sampleFromEntity.get生年月日().substring(0, idx_year);
        final String month = sampleFromEntity.get生年月日().substring(idx_year + 1, idx_month);
        final String day = sampleFromEntity.get生年月日().substring(idx_month + 1, idx_day);
        new Select(driver.findElement(By.name("birth_year"))).selectByVisibleText(year);
        new Select(driver.findElement(By.name("month"))).selectByVisibleText(month);
        new Select(driver.findElement(By.name("day"))).selectByVisibleText(day);

        // 性別
        driver.findElement(By.id(Sex.getValueFromLabel(sampleFromEntity.get性別()).getValue())).click();

        // 希望日
        driver.findElement(By.id("dlv_date")).clear();
        driver.findElement(By.id("dlv_date")).sendKeys(sampleFromEntity.get希望日());
    }

    @When("^確認画面に遷移する。$")
    public void 確認画面に遷移する() throws Throwable {
        driver.findElement(By.cssSelector("button.execute")).click();
    }

    @Then("^確認画面の表示を確認します。$")
    public void 確認画面の表示を確認します(DataTable arg1) throws Throwable {
        // TODO 省略
    }

    @When("^完了画面に遷移する。$")
    public void 完了画面に遷移する() throws Throwable {
        driver.findElement(By.cssSelector("button.execute")).click();

    }

    @Then("^登録履歴の表示を確認します。$")
    public void 登録履歴の表示を確認します(List<SampleFromEntity> sampleFromEntityList) throws Throwable {

        // +1 はヘッダ分
        assertEquals(sampleFromEntityList.size()+1, driver.findElements(By.tagName("tr")).size());

        for(int i = 0; i < sampleFromEntityList.size(); i++){
            int row_num = i+2;
            SampleFromEntity sampleFromEntity = sampleFromEntityList.get(i);
            assertEquals(sampleFromEntity.get氏名(), driver.findElement(By.xpath("//tr["+ row_num +"]/td[1]")).getText());
            assertEquals(sampleFromEntity.get生年月日(), driver.findElement(By.xpath("//tr["+ row_num +"]/td[2]")).getText());
            assertEquals(sampleFromEntity.get性別(), driver.findElement(By.xpath("//tr["+ row_num +"]/td[3]")).getText());
            assertEquals(sampleFromEntity.get希望日(), driver.findElement(By.xpath("//tr["+ row_num +"]/td[4]")).getText());
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
