package mc.apps.spring.bdd_forex;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@CucumberContextConfiguration
public class StepDef {
    private static Logger logger = LoggerFactory.getLogger(StepDef.class);

    // Obtain the reserved port number from environment variable
    private String apiServiceUrl = "http://localhost:8080"; //+ System.getProperty("test.server.port");

    // Store response in class variable for data share across steps
    private HttpResponse<String> response;

    // Inject scenario object for report logging
    private Scenario scenario;
    private String baseCurrency;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    // Step Implementation ...
    /**
     * Annotation “@Given” maps the Java method to the statement in feature file that matches the text
     *
     * rates-retreive-api.feature :
     * Scenario Outline: Fetch the latest rate for base currency '<baseCurrency>'
     *   Given API Service is started
     *   When I request for the latest rate for base currency '<baseCurrency>'
     *   Then I should receive a list of currency rates
     *   Examples:
     *   | baseCurrency |
     *   | GBP |
     *   | USD |
     */
    @Given("^API Service is started$") // precondition : Given API Service is started
    public void api_service_is_started() throws IOException {

        // ping if application is up and running
        int appPort = 8080;// Integer.parseInt(System.getProperty("test.server.port"));

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", appPort), 1000);
        socket.close();
    };


    @When("I request for the latest rate for base currency {string}")
    public void i_request_for_the_latest_rate_with_base_currency(String baseCurrency) throws IOException, InterruptedException, URISyntaxException {
        this.baseCurrency = baseCurrency;

        // Send request to get the latest rates
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder(new URI(apiServiceUrl + "/api/exchangeRate/all/")) //baseCurrency
                .header("accept", "application/json").build();

        scenario.log(String.format("Request: %1$s", request.toString()));

        this.response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("I should receive a list of currency rates")
    public void i_should_receive_list_of_currency_rate() throws JSONException {

        scenario.log(String.format("Status Code: %1$s, Body: %2$s", response.statusCode(), response.body()));

        assertEquals(200, response.statusCode());

        JSONArray jsonArray = new JSONArray(response.body());
        logger.debug("record size: " + jsonArray.length());

        assertTrue(jsonArray.length() > 2);

        jsonArray.forEach(item -> {
            JSONObject json = (JSONObject) item;
            assertTrue(json.getDouble("rate") > 0);

//            assertEquals(baseCurrency, json.getString("baseCurrency"));
        });

    }


}
