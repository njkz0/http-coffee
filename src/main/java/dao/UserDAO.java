package dao;

import factory.HibernateFactory;
import model.Item;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends BaseDAO<User> {


    private SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
    private static UserDAO userDAO;

    private UserDAO() {
    }

    public static UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAO();
            return userDAO;
        } else return userDAO;
    }


    public User searchSuchUser(String userLogin) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        String sql = "SELECT * FROM users WHERE login=:login";
        Query query = session.createNativeQuery(sql, User.class);
        query.setParameter("login", userLogin);
        List<User> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }
}
