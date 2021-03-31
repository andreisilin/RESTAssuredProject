package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/PlaceValidations.feature",
        plugin = {"pretty", "html:target/reports/cucumber-html-report.html",
                "json:target/jsonReports/cucumber-reports.json"}, glue = "stepDefinition")
public class TestRunner {

}