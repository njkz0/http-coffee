package dao;

import factory.HibernateFactory;
import model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO extends BaseDAO<Item>{

    private SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
    private static ItemDAO itemDAO;

    private ItemDAO() {
    }

    public static ItemDAO getItemDAO() {
        if (itemDAO == null) {
            itemDAO = new ItemDAO();
            return itemDAO;
        } else return itemDAO;
    }

    public List searchAllItems() {
        Session session= sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM items";
        List<Item> items= session.createNativeQuery(sql, Item.class).getResultList();
        if (items.isEmpty()){
           items.add( itemDAO.save(new Item("Iphone XR", 60000)));
            items.add(itemDAO.save(new Item("Samsung Galaxy S", 80000))); //add items if not exist
        }
        session.close();
        return items;
    }
}
