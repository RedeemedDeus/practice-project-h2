package DAO;

import Model.Item;
import Util.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {


    public ItemDAO(){}

    public Item getItemById(int id)
    {
        String query = "SELECT * FROM item WHERE id = ?";
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                return new Item(resultSet.getString("name"),
                                resultSet.getString("description"),
                                resultSet.getDouble("price"),
                                resultSet.getInt("store_id")
                                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Returns a list of items or an empty list if none are found.
     *
     * @return List<Item>
     */
    public List<Item> getAllItems()
    {
        String query = "SELECT * FROM item";
        try (Connection connection = ConnectionSingleton.getConnection();
             Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(query);

            List<Item> output = new ArrayList<>();

            while(resultSet.next())
            {
                output.add( new Item(resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("store_id")
                            ));
            }
            return output;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Item addNewItem(Item newItem)
    {
        String query = "SELECT INTO item VALUES(?, ?, ?, ?)";
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, newItem.getName());
            statement.setString(2, newItem.getDescription());
            statement.setDouble(3, newItem.getPrice());
            statement.setInt(4, newItem.getStoreId());


            int result = statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next())
            {
                return new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("store_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Item updateItem(Item updatedItem)
    {
        Item newItem = this.getItemById(updatedItem.getId());

        if( !updatedItem.getName().isEmpty() )
            newItem.setName(updatedItem.getName());
        if( !updatedItem.getDescription().isEmpty() )
            newItem.setDescription(updatedItem.getDescription());
        if (updatedItem.getPrice() != 0L)
            newItem.setPrice(updatedItem.getPrice());
        if (updatedItem.getStoreId() != 0)
            newItem.setStoreId(updatedItem.getStoreId());

        String query = "UPDATE item SET name = ?, description = ?, price = ?, store_di = ?";

        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, newItem.getName());
            statement.setString(2, newItem.getDescription());
            statement.setDouble(3, newItem.getPrice());
            statement.setInt(4, newItem.getStoreId());


            int result = statement.executeUpdate();

            if(result > 0)
                return newItem;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public List<Item> getItemsByStoreId(int id)
    {
        String query = "SELECT * FROM item WHERE store_id = ?";
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Item> output = new ArrayList<>();

            while(resultSet.next())
            {
                output.add( new Item(resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("store_id")
                ));
            }
            return output;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get a list of items based on state.
     * Returns a list of items or an empty list if none are found.
     * @param state A string representing the state the store is located in
     * @return List<Item>
     */
    public List<Item> getItemsByState(String state)
    {
        String query = "SELECT * FROM item INNER JOIN store ON store.id = item.store_id WHERE store.state = ?";
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, state);
            ResultSet resultSet = statement.executeQuery();
            List<Item> output = new ArrayList<>();

            while(resultSet.next())
            {
                output.add( new Item(resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("store_id")
                ));
            }
            return output;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get a list of items based on Zip Code.
     * Returns a list of items or an empty list if non are found.
     * @param zipCode An integer representing the zipCode a store is located in.
     * @return List<Item>
     */
    public List<Item> getItemsByZip(int zipCode)
    {
        String query = "SELECT * FROM item INNER JOIN store ON store.id = item.store_id WHERE store.zipcode = ?";
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, zipCode);
            ResultSet resultSet = statement.executeQuery();
            List<Item> output = new ArrayList<>();

            while(resultSet.next())
            {
                output.add( new Item(resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("store_id")
                ));
            }
            return output;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
