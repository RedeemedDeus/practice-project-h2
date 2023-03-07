package Model;

public class Store {

    //INT, The unique id for the store
    public int store_id;

    //STRING, The name of the store
    public String store_name;

    //STRING, The state where the store is located
    public String state;

    //INT, The zip code of the store
    public int zip;


    //NO ARGS CONSTRUCTOR
    public Store(){

    }

    //FULL ARGS CONSTRUCTOR
    public Store(int store_id, String store_name, String state, int zip){
        this.store_id = store_id;
        this.store_name = store_name;
        this.state = state;
        this.zip = zip;
    }

    //GETS THE store_id
    public int getStore_id(){
        return store_id;
    }

    //SETS THE store_id to the parameter store_id
    public void setStore_id(int store_id){
        this.store_id = store_id;
    }

    //GETS THE store_name
    public String getStore_name(){
        return store_name;
    }

    //SETS THE store_name to the parameter store_name
    public void setStore_name(String store_name){
        this.store_name = store_name;
    }

    //GETS THE zip
    public int getZip(){
        return zip;
    }

    //SETS THE zip to the parameter zip
    public void setZip(int zip){
        this.zip = zip;
    }

    //GETS THE state
    public String getState(){
        return state;
    }

    //SETS THE state to the parameter state
    public void setState(String state){
        this.state = state;
    }



}
