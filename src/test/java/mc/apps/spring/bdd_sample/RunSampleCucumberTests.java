package mc.apps.spring.bdd_sample;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber-reports/cucumber-sample.json"}, // location of test result data output
        features = {"classpath:bdd_sample"},                                      // location of feature files
        glue = {"mc.apps.spring.bdd_sample"})
public class RunSampleCucumberTests {
}
