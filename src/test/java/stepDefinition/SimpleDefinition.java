package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SimpleDefinition {

    @Given("simple page")
    public void simple_page() {
        System.out.println("This is given.");

    }

    @When("user clicks")
    public void user_clicks() {
        System.out.println("This is when.");

    }

    @Then("all good")
    public void all_good() {
        System.out.println("This is then.");
    }
}
