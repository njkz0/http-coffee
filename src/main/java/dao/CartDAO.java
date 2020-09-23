package dao;

import factory.HibernateFactory;
import model.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO extends BaseDAO<Cart> {

    private SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
    private static CartDAO cartDAO;

    private CartDAO() {
    }

    public static CartDAO getCartDAO() {
        if (cartDAO == null) {
            cartDAO = new CartDAO();
            return cartDAO;
        } else return cartDAO;
    }

    public Cart searchCartByUserID(Integer userID) {
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            String sql = "SELECT * FROM carts WHERE user_id=:userID";
            Query query = session.createNativeQuery(sql, Cart.class);
            query.setParameter("userID", userID);
            Cart cart = (Cart) query.getSingleResult();
            session.close();
            return cart;
        } catch (NoResultException e) {
            return null;
        }
    }
}
