package dao;

import model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO {

    public static Cart saveCart(Cart cart) {
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

    public static Cart searchCartByUserID(int userID) {
        String sql = "SELECT * FROM carts WHERE user_id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            Cart cart;
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String time = resultSet.getString(3);
                cart= new Cart().builder()
                        .id(id)
                        .userID(userID)
                        .time(time)
                        .build();
                return cart;
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
