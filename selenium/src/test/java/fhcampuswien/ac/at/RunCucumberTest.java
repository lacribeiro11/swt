package fhcampuswien.ac.at;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/fhcampuswien.ac.at.features",
        glue = "fhcampuswien.ac.at.stepdefinitions",
        plugin = {"pretty", "json:../target/cucumber_selenium.json", "html:target/cucumber.html"})
public class RunCucumberTest {

}
