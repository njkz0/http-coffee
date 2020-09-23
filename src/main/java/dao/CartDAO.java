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

    /*  public static Cart saveCart(Cart cart) {
          String sql = "INSERT INTO carts(user_id, time) VALUES (?, ?)";
          String curIdStatement = "SELECT currval(pg_get_serial_sequence('carts','id'))";
          try (Connection connection = ConnectionToDB.getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement(sql);
               PreparedStatement idPreparedStatement = connection.prepareStatement(curIdStatement)
          ) {
              preparedStatement.setInt(1, cart.getUserID());
              preparedStatement.setString(2, cart.getTime());
              preparedStatement.executeUpdate();
              ResultSet resultSet = idPreparedStatement.executeQuery();
              if (resultSet.next()) {
                  int id = resultSet.getInt(1);
                  cart.setId(id);
                  return cart;
              }

          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
          return null;
      }

      public static void deleteCart(int cartID) {
          String sql = "DELETE FROM carts where id=?";
          try (Connection connection = ConnectionToDB.getConnection();
               PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
              preparedStatement.setInt(1, cartID);
              preparedStatement.executeUpdate();
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  */
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
