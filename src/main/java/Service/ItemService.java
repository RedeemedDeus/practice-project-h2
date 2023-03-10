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
    public Item getItemById(int itemId)
    {
        Item item = new Item();
        item.setId(itemId);
        return this.getItemById(item);
    }
    public Item getItemById(Item item)
    {
        return this.itemDAO.getItemById(item.getId());
    }

    public List<Item> getAllItems()
    {
        return this.itemDAO.getAllItems();
    }

    public Item addNewItem(Item item)
    {
        if(item.getName().isEmpty()
            || item.getDescription().isEmpty()
            || item.getPrice() == 0L
            || item.getStoreId() == 0
        ){
            return null;
        }

        return this.itemDAO.addNewItem(item);
    }

    public Item updateItem(Item item)
    {
        return this.itemDAO.updateItem(item);
    }

    public List<Item> getItemsByStoreId(int id)
    {
        return this.itemDAO.getItemsByStoreId(id);
    }

    public List<Item> getItemsByState(String state)
    {
        return this.itemDAO.getItemsByState(state);
    }

    public List<Item> getItemsByZip(int zipCode)
    {
        return this.itemDAO.getItemsByZip(zipCode);
    }
}
