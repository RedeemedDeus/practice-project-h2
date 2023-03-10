package Controller;

import Model.Item;
import Util.ConnectionSingleton;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class ShoppingControllerTest {

    private static ShoppingController controller = new ShoppingController();;
    ObjectMapper mapper = new ObjectMapper();
    private static int PORT = 8080;

    @BeforeEach
    void setUp() {
        ConnectionSingleton.resetTestDatabase();
    }

    /**
     * Just make sure the server starts without throwing exceptions.
     */
    @BeforeAll
    public static void controller_start_test()
    {
        controller.start(PORT);
    }

    /**
     * Add new Item endpoint test
     */
    @Test
    public void controller_addItem_test() throws IOException, InterruptedException {
        String url = "http://localhost:"+PORT+"/item";

        HttpClient webClient = HttpClient.newHttpClient();

        Item newItem = new Item(
                "Product1",
                "Description of product1",
                10.00,
                1
        );

        String newItemJson = mapper.writeValueAsString(newItem);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(newItemJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();
        int expected = 200;
//        the response should be 200
        assertEquals(expected, status);
    }
    /**
     * Get ItemById endpoint test
     */
    @Test
    public void controller_getItemById_test() throws IOException, InterruptedException {
        controller_addItem_test();

        String url = "http://localhost:"+PORT+"/item/id/1";

        HttpClient webClient = HttpClient.newHttpClient();

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Content-Type", "application/json")
                .build();


        HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();
        int expected = 200;
//        the response should be 200
        assertEquals(expected, status, response.body());
    }
    @Test
    public void controller_updateItem_test() throws IOException, InterruptedException {
        controller_addItem_test();

        String url = "http://localhost:"+PORT+"/item";

        HttpClient webClient = HttpClient.newHttpClient();

        Item newItem = new Item();
        newItem.setId(1);
        newItem.setPrice(5.00);
        newItem.setDescription("Updated product description");

        String newItemJson = mapper.writeValueAsString(newItem);
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(newItemJson))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();
        int expectedStatus = 200;

//      the response should be 200
        assertEquals(expectedStatus, status, response.body());

//      the price and description should be updated
        Item responseItem = mapper.readValue(response.body(), Item.class);
        double expectedPrice = 5.00;
        String expectedDescription = "Updated product description";
        assertEquals(expectedPrice, responseItem.getPrice(), response.body());
        assertEquals(expectedDescription, responseItem.getDescription(), response.body());
    }

    @Test
    public void controller_getAllItems_test() throws IOException, InterruptedException {
        controller_addItem_test();

        String url = "http://localhost:"+PORT+"/items";

        HttpClient webClient = HttpClient.newHttpClient();

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();
//        the response should be 200
        int expected = 200;
        assertEquals(expected, status, response.body());

//      response should be List<Item> with one item in it
        ArrayList<Item> retrievedItems = mapper.readValue(response.body(), new TypeReference<ArrayList<Item>>(){});

        ArrayList<Item> expectedItems = new ArrayList<>();
        expectedItems.add(new Item(
                1,
                "Product1",
                "Description of product1",
                10.00,
                1
        ));
        assertTrue(expectedItems.get(0).equals(retrievedItems.get(0)), expectedItems.get(0) + "\n" + retrievedItems.get(0));
    }
}