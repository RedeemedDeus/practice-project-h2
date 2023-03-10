package Service;

import Model.Store;
import DAO.StoreDAO;

import java.util.List;

public class StoreService {
    private StoreDAO storeDAO;

    //NO ARG CONSTRUCTOR
    public StoreService(){
        this.storeDAO = new StoreDAO();
    }

    //ARG CONSTRUCTOR
    public StoreService(StoreDAO storeDAO){
        this.storeDAO = storeDAO;
    }

    /**
     * ADD A STORE TO THE DATABASE
     */
    public Store insertStore(Store store){
//        CHECK IF THE STORE ALREADY EXISTS, FALSE IF IT DOESN'T(GOOD)
//        Store storeFromDb = this.storeDAO.getStoreById(store.getStore_id());

        //THE NAME STORE NAME, STATE CANNOT BE EMPTY, ZIP HAS TO BE >= 5 (AS IS NORM IN THE US)
        if(store.getStore_name() == "" || store.getState() == "" || store.getZip() < 5) return null;
//        else if(storeFromDb != null) return null;

        return this.storeDAO.insertStore(store);
    }

    /**
     * GET ALL STORES FROM THE DATABASE
     */
    public List<Store> getAllStores(){
        List<Store> allStores = this.storeDAO.getAllStores();

        if(allStores == null) return null;
        return allStores;
    }

    /**
     * GET A STORE BY ITS store_id
     */
    public Store getStoreById(int store_id){
        Store storeFromDb = this.storeDAO.getStoreById(store_id);

        if(storeFromDb == null) return null;
        return storeFromDb;
    }

    /**
     * GET ALL STORES BY ITS state
     */
    public List<Store> getStoreByState(String state){
        List<Store> storesbystate = this.storeDAO.getStoreByState(state);

        if(storesbystate == null) return null;
        return storesbystate;
    }

    /**
     * GET ALL STORES BY ITS zip
     */
    public List<Store> getStoreByZip(int zip){
        List<Store> storesByZip = this.storeDAO.getStoreByZip(zip);

        if(storesByZip == null) return null;
        return storesByZip;
    }
}
