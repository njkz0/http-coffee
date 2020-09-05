package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static User saveUser(User user) {
        String sql = "INSERT INTO users(login, password, first_name, last_name) VALUES (?, ?, ?, ?)";
        String curIdStatement = "SELECT currval(pg_get_serial_sequence('users','id'))";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             PreparedStatement idPreparedStatement = connection.prepareStatement(curIdStatement)
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = idPreparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                user.setId(id);
                return user;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void deleteUser(int userID) {
        String sql = "DELETE FROM users where id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User updateUser(User newUser, int changedUserId) {
        String sql = "UPDATE users SET login=?, password=?, first_name=?, last_name=? WHERE id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setString(3, newUser.getFirstName());
            preparedStatement.setString(4, newUser.getLastName());
            preparedStatement.setInt(5, changedUserId);
            preparedStatement.executeUpdate();
            return newUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User searchSuchUser(String userLogin) {
        String sql = "SELECT * FROM users WHERE login=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userLogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String firstName = resultSet.getString(4);
                String lastName = resultSet.getString(5);
                return new User().builder()
                        .id(id)
                        .login(login)
                        .password(password)
                        .firstName(firstName)
                        .lastName(lastName)
                        .build();
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
