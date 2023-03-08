package DAO;

import Model.Store;
import Util.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {

    /**
     * INSERTS A NEW STORE INTO THE DATABASE
     */
    public Store insertStore(Store store){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            //SQL QUERY TO INSERT A NEW STORE
            String sql = "INSERT INTO store(store_name,state,zip) VALUES (?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Write Prepared Statements
            preparedStatement.setString(1,store.getStore_name());
            preparedStatement.setString(2,store.getState());
            preparedStatement.setInt(3,store.getZip());

            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();

            if(pkeyResultSet.next()){
                int generated_store_id = (int) pkeyResultSet.getLong(1);
                return new Store(generated_store_id,store.getStore_name(),store.getState(),store.getZip());
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * GET ALL STORES IN THE store TABLE
     */
    public List<Store> getAllStores(){
        Connection connection = ConnectionSingleton.getConnection();
        List<Store> stores = new ArrayList<>();
        try{
            String sql = "SELECT * FROM store;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Store store = new Store(rs.getInt("store_id"), rs.getString("store_name"),
                        rs.getString("state"), rs.getInt("zip"));

                stores.add(store);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return stores;
    }

    /**
     * GET A STORE BY ITS store_id
     */
    public Store getStoreById(int store_id){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            String sql = "SELECT * FROM store WHERE store_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,store_id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Store store = new Store(rs.getInt("store_id"), rs.getString("store_name"),
                        rs.getString("state"), rs.getInt("zip"));

                return store;
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return null;
    }

    /**
     * GET A STORE BY ITS state
     */
    public Store getStoreByState(String state){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            String sql = "SELECT * FROM store WHERE state = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, state);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Store store = new Store(rs.getInt("store_id"), rs.getString("store_name"),
                        rs.getString("state"), rs.getInt("zip"));

                return store;
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return null;
    }

    /**
     * GET A STORE BY ITS zip
     */
    public Store getStoreByZip(int zip){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            String sql = "SELECT * FROM store WHERE zip = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,zip);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Store store = new Store(rs.getInt("store_id"), rs.getString("store_name"),
                        rs.getString("state"), rs.getInt("zip"));

                return store;
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return null;
    }
}
