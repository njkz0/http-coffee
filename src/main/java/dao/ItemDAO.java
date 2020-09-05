package dao;

import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAO {

    public static Item saveItem(Item item) {
        String sql = "INSERT INTO items(name, price) VALUES (?, ?)";
        String curIdStatement = "SELECT currval(pg_get_serial_sequence('items','id'))";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             PreparedStatement idPreparedStatement = connection.prepareStatement(curIdStatement)
        ) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = idPreparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                item.setId(id);
            }
            return item;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void deleteItem(int itemID) {
        String sql = "DELETE FROM items where id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, itemID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Item updateItem(Item newItem, int changedItemID) {
        String sql = "UPDATE items SET name=?, price=? WHERE id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newItem.getName());
            preparedStatement.setInt(2, newItem.getPrice());
            preparedStatement.setInt(3, changedItemID);
            preparedStatement.executeUpdate();
            newItem.setId(changedItemID);
            return newItem;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Item searchSuchItem(int itemID) {
        String sql = "SELECT * FROM items WHERE id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, itemID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                return new Item().builder()
                        .id(itemID)
                        .name(name)
                        .price(price)
                        .build();
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList searchAllItems() {
        String sql = "SELECT * FROM items";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ArrayList<Item> items= new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int itemID=resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                items.add( new Item().builder()
                        .id(itemID)
                        .name(name)
                        .price(price)
                        .build());}
            return items;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
