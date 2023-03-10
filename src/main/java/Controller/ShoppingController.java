package Controller;

import Model.Item;
import Model.Store;
import Service.ItemService;
import Service.StoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class ShoppingController {
    private final Javalin app;
    private final ItemService itemService;
    private final StoreService storeService;
    private final ObjectMapper mapper = new ObjectMapper();

    public ShoppingController()
    {
        this.itemService = new ItemService();
        this.storeService = new StoreService();

        this.app = Javalin.create()
            // Store end-points
            .post("/store", this::addStore)
            .get("/store/id/{id}", this::getStoreById)

            .get("/stores", this::getAllStores)
            .get("/stores/zip/{zipCode}", this::getStoresByZip)
            .get("/stores/state/{state}", this::getStoresByState)

            // Item end-points
            .post("/item", this::addItem)
            .patch("item", this::updateItem)
            .get("/item/id/{id}", this::getItemById)

            .get("/items", this::getAllItems)
            .get("/items/zip/{zipCode}", this::getItemsByZip)
            .get("/items/state/{state}", this::getItemsByState)
        ;
    }
    public void start()
    {
        this.start(8080);
    }
    public void start(int port)
    {
        this.app.start(port);
    }
    public void stop()
    {
        this.app.stop();
    }


    // Store Endpoints
    /**
     * addStore endpoint handler
     * @param context
     * @throws JsonProcessingException
     */
    private void addStore(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Store store = mapper.readValue(context.body(), Store.class);
        Store addedStore = storeService.insertStore(store);

        if(addedStore == null){
            context.status(400);
        }
        else{
            context.json(mapper.writeValueAsString(addedStore));
        }
    }

    /**
     * getStoreById endpoint handler
     * @param context
     */
    private void getStoreById(Context context) {
        Store store = storeService.getStoreById(Integer.parseInt(context.pathParam("id")));

        if(store != null){
            context.json(store);
        }
    }

    /**
     * getAllStores endpoint handler
     * @param context
     */
    private void getAllStores(Context context) {
        List<Store> stores = storeService.getAllStores();

        if(stores != null){
            context.json(stores);
        }

    }

    /**
     * getStoresByZip endpoint handler
     * @param context
     */
    private void getStoresByZip(Context context) {
        List<Store> stores = storeService.getStoreByZip(Integer.parseInt(context.pathParam("zipCode")));

        if(stores != null){
            context.json(stores);
        }

    }

    /**
     * getStoresByState endpoint handler
     * @param context
     */
    private void getStoresByState(Context context) {
        List<Store> stores = storeService.getStoreByState(context.pathParam("state"));

        if(stores != null){
            context.json(stores);
        }

    }




    // Item Endpoints
    private void addItem(Context context)
    {
        Item newItem;
        try {
            newItem = mapper.readValue(context.body(), Item.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            context.result("Invalid item data received");
            context.status(400);
            return;
        }

        newItem = itemService.addNewItem(newItem);
        context.json(newItem);
        context.status(200);
    }

    private void updateItem(Context context) {
        Item newItem;
        try {
            newItem = mapper.readValue(context.body(), Item.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            context.result("Invalid item data received");
            context.status(400);
            return;
        }

        newItem = itemService.updateItem(newItem);

        if(newItem == null)
        {
            context.result("Item does not exist");
            context.status(404);
            return;
        }
        context.json(newItem);
        context.status(200);
    }

    private void getItemById(Context context)
    {
        int itemId;
        try { // in case the user submits a string that is not a number to the endpoint
            itemId = Integer.parseInt(context.pathParam("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            context.result("Invalid item id");
            context.status(400);
            return;
        }

        Item retrievedItem = itemService.getItemById(itemId);

        if(retrievedItem == null)
        {
            context.result("Item does not exist");
            context.status(404);
            return;
        }

        context.json(retrievedItem);
        context.status(200);
    }

    private void getAllItems(Context context) {
        List<Item> retrievedItems = itemService.getAllItems();

        context.json(retrievedItems);
        context.status(200);
    }

    private void getItemsByZip(Context context) {
        int zipCode;
        try { // in case the user submits a string that is not a number to the endpoint
            zipCode = Integer.parseInt(context.pathParam("zipCode"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            context.result("Invalid zip code");
            context.status(400);
            return;
        }
        List<Item> retrievedItems = itemService.getItemsByZip(zipCode);

        context.json(retrievedItems);
        context.status(200);
    }

    private void getItemsByState(Context context) {
        String state;
        try { // in case of invalid input
            state = context.pathParam("state");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            context.result("Invalid zip code");
            context.status(400);
            return;
        }
        List<Item> retrievedItems = itemService.getItemsByState(state);

        context.json(retrievedItems);
        context.status(200);
    }
}
