package mc.apps.spring.bdd_sample;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import mc.apps.spring.modele.Sample;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

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
public class SampleStepDef {
    private static Logger logger = LoggerFactory.getLogger(SampleStepDef.class);

    /**
     * Inject Scenario!!
     * (classpath:bdd_sample/behavior-test-sample.feature)
     */
    private Scenario scenario;
    private String apiServiceUrl = "http://localhost:8080"; //+ System.getProperty("test.server.port");
    private int appPort=8080;
    private HttpResponse<String> response;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }



    /**
     * service (api) + repository
     * @throws IOException
     */

    @Given("api service started")
    public void api_service_is_started() throws IOException {
        // ping if application is up and running

//        logger.info("server.port = "+serverPort);

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", appPort), 1000);
        socket.close();
        logger.info("api_service_is_started..ok");
    }

    @When("add item to list")
    public void add_item_to_list() throws IOException, InterruptedException, URISyntaxException {
        logger.info("i_put_element_into_list..ok");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.BodyPublisher data = HttpRequest.BodyPublishers.ofString("id=0&title=hello mc!");
        HttpRequest request = HttpRequest.newBuilder()
                .POST(data)
                .uri(new URI(apiServiceUrl+"/samples/add"))
                .header("Content-Type", "application/x-www-form-urlencoded")
//              .header("accept", "application/json")
                .build();

        this.response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    @Then("get list size")
    public void get_list_size() {
        logger.info("i_get_list_size..ok");

        scenario.log(String.format("Status Code: %1$s, Body: %2$s", response.statusCode(), response.body()));
//        logger.info(String.format("Status Code: %1$s, Body: %2$s", response.statusCode(), response.body()));

        assertEquals(200, response.statusCode());

        JSONArray jsonArray = new JSONArray(response.body());
        assertTrue(jsonArray.length() > 0);
    }

}
