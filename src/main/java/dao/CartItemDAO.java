package dao;

import controller.dto.CartItemDTO;
import model.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartItemDAO {

    public static CartItem saveCartItem(CartItem cartItem) {
        String sql = "INSERT INTO cart_items(item_id, cart_id, amount) VALUES (?, ?, ?)";
        String curIdStatement = "SELECT currval(pg_get_serial_sequence('cart_items','id'))";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             PreparedStatement idPreparedStatement = connection.prepareStatement(curIdStatement)
        ) {
            preparedStatement.setInt(1, cartItem.getItemID());
            preparedStatement.setInt(2, cartItem.getCartID());
            preparedStatement.setInt(3, cartItem.getAmount());
            preparedStatement.executeUpdate();
            ResultSet resultSet = idPreparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                cartItem.setId(id);
                return cartItem;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static ArrayList searchAllCartItemsByCartID(int cartID) {
        String sql = "SELECT * FROM cart_items WHERE cart_id IN(?)";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cartID);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<CartItem> cartItems = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int itemID = resultSet.getInt(2);
                int amount = resultSet.getInt(4);
                cartItems.add(new CartItem().builder()
                        .id(id)
                        .itemID(itemID)
                        .cartID(cartID)
                        .amount(amount)
                        .build());
            }
            return cartItems;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CartItem updateCartItem(CartItem newCartItem, int changedCartItemID) {
        String sql = "UPDATE cart_items SET item_id=?, cart_id=?, amount=? WHERE id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, newCartItem.getItemID());
            preparedStatement.setInt(2, newCartItem.getCartID());
            preparedStatement.setInt(3, newCartItem.getAmount());
            preparedStatement.setInt(4, changedCartItemID);
            preparedStatement.executeUpdate();
            newCartItem.setId(changedCartItemID);
            return newCartItem;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteCartItem(int cartItemID) {
        String sql = "DELETE FROM cart_items where id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cartItemID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList getCartItemDTOByCartID(int cartID) {
        String sql = " SELECT * FROM cart_items ci FULL OUTER JOIN items i ON ci.item_id=i.id WHERE cart_id=?";
        ArrayList<CartItemDTO> cartItemDTOs = new ArrayList<>();
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cartID);
            ResultSet resultSet = preparedStatement.executeQuery();
            CartItemDTO cartItemDTO;
            while (resultSet.next()) {
                String name = resultSet.getString(6);
                int amount = resultSet.getInt(4);
                int price = resultSet.getInt(7);
                cartItemDTOs.add(new CartItemDTO(name, amount, price));
            }
            return cartItemDTOs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
