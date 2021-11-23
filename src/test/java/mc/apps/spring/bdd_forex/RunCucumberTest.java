package mc.apps.spring.bdd_forex;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json"}, // location of test result data output
        features = {"classpath:bdd_forex"},                                      // location of feature files
        glue = {"mc.apps.spring.bdd_forex"})                               // location of step implementation
public class RunCucumberTest {

}