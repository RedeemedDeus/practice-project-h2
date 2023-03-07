package DAO;

import Model.Item;
import Util.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {


    ItemDAO(){};

    Item getItemById(int id)
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
    List<Item> getAllItems()
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
    Item addNewItem(Item newItem)
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
    Item updateItem(Item updatedItem)
    {
        Item newItem = this.getItemById(updatedItem.getId());

        if( !updatedItem.getName().isEmpty() )
            newItem.setName(updatedItem.getName());
        if( !updatedItem.getDescription().isEmpty() )
            newItem.setDescription(updatedItem.getDescription());
        if (updatedItem.getPrice() != 0l)
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
            ResultSet resultSet = statement.getGeneratedKeys();

            if(result > 0)
                return newItem;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    Item getItemByStoreId(int id)
    {
        String query = "SELECT * FROM item WHERE store_id = ?";
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
     * Get a list of items based on state.
     *
     * Returns a list of items or an empty list if none are found.
     * @param state
     * @return List<Item>
     */
    List<Item> getItemsByState(String state)
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
     *
     * Returns a list of items or an empty list if non are found.
     * @param zipCode
     * @return List<Item>
     */
    List<Item> getItemsByZip(int zipCode)
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
