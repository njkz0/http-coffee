package dao;

import controller.dto.CartItemDTO;
import factory.HibernateFactory;
import model.Cart;
import model.CartItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO extends BaseDAO<CartItem> {

    private SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
    private static CartItemDAO cartItemDAO;

    private CartItemDAO() {
    }

    public static CartItemDAO getCartItemDAO() {
        if (cartItemDAO == null) {
            cartItemDAO = new CartItemDAO();
            return cartItemDAO;
        } else return cartItemDAO;
    }

    public List searchAllCartItemsByCartID(int cartID) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM cart_items WHERE cart_id IN(:cartID)";
        Query query = session.createNativeQuery(sql, CartItem.class);
        query.setParameter("cartID", cartID);
        List<CartItem> cartItems = query.getResultList();
        session.close();
        return cartItems;


    }


}
