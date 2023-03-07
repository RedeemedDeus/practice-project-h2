package Controller;

import Service.ItemService;
import Service.StoreService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class ShoppingController {
    private final Javalin app;
    private final ItemService itemService;
    private final StoreService storeService;

    ShoppingController()
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
            .get("/item/id/{id}", this::getItemById)

            .get("/items", this::getAllItems)
            .get("/items/zip/{zipCode}", this::getItemsByZip)
            .get("/items/state/{state}", this::getItemsByState)
        ;
    }
    public void start()
    {
        this.app.start(8080);
    }

    // Store Endpoints
    private void addStore(Context context) {
    }

    private void getStoreById(Context context) {
    }

    private void getAllStores(Context context) {
    }

    private void getStoresByZip(Context context) {
    }

    private void getStoresByState(Context context) {
    }


    // Item Endpoints
    private void addItem(Context context) {
    }

    private void getItemById(Context context) {
    }

    private void getAllItems(Context context) {
    }

    private void getItemsByZip(Context context) {
    }

    private void getItemsByState(Context context) {
    }
}
