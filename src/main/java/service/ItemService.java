package service;

import dao.ItemDAO;

import java.util.ArrayList;

public class ItemService {

    public static ArrayList getAllAvailableItems() {
        return ItemDAO.searchAllItems();
    }

}


