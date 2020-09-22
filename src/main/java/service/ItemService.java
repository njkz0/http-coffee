package service;

import dao.ItemDAO;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private static ItemDAO itemDAO = ItemDAO.getItemDAO();

    public static List getAllAvailableItems() {
        return itemDAO.searchAllItems();
    }

}


