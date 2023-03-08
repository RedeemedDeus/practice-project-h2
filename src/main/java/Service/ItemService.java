package Service;

import DAO.ItemDAO;
import Model.Item;

import java.util.List;

public class ItemService {
    private final ItemDAO itemDAO;
    public ItemService()
    {
        itemDAO = new ItemDAO();
    }
    public ItemService(ItemDAO itemDAO)
    {
        this.itemDAO = itemDAO;
    }

    Item getItemById(Item item)
    {
        return this.itemDAO.getItemById(item.getId());
    }

    List<Item> getAllItems()
    {
        return this.itemDAO.getAllItems();
    }

    Item addNewItem(Item item)
    {
        return this.itemDAO.addNewItem(item);
    }

    Item updateItem(Item item)
    {
        return this.itemDAO.updateItem(item);
    }

    List<Item> getItemsByStoreId(int id)
    {
        return this.itemDAO.getItemsByStoreId(id);
    }

    List<Item> getItemsByState(String state)
    {
        return this.itemDAO.getItemsByState(state);
    }

    List<Item> getItemsByZip(int zipCode)
    {
        return this.itemDAO.getItemsByZip(zipCode);
    }
}
