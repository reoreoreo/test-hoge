package sample.test_hoge;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, format = { "pretty", "json:target/cucumber.json" })
public class runCucumberTest {
}