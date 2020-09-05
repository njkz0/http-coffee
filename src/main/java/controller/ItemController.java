package controller;

import controller.dto.CartItemDTO;
import model.Item;
import model.User;
import service.CartItemService;
import service.ItemService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/item"})
public class ItemController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if (action.equals("items")) {
            ArrayList<Item> items = ItemService.getAllAvailableItems();
            session.setAttribute("allItems", items);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/available-items.jsp");
            dispatcher.forward(req, resp);
        } else if (action.equals("cart")) {
            User user = (User) session.getAttribute("user");
            ArrayList<CartItemDTO> cartItemDTOs = CartItemService.getCartItemsDTO(user.getId());
            if (cartItemDTOs != null) {
                session.setAttribute("DTOs", cartItemDTOs);
                int total = CartItemService.getTotalPrice(cartItemDTOs);
                session.setAttribute("total", total);
                RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/my-cart.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/empty.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if (action.equals("add-item-in-cart")) {
            User user = (User) session.getAttribute("user");
            int itemID = Integer.parseInt(req.getParameter("itemID"));
            int amount = Integer.parseInt(req.getParameter("amount"));
            UserService.addCartItemToCart(user.getLogin(), itemID, amount);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/successfully.jsp");
            dispatcher.forward(req, resp);
        } else if (action.equals("buy")) {
            User user = (User) session.getAttribute("user");
            CartItemService.buy(user.getId());
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/successfully.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
