package com.shoppingcart.servlets;

import com.shoppingcart.beans.CartBean;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet(name = "UpdateCartServlet", urlPatterns = { "/update-cart" })
public class UpdateCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartBean cartBean = (CartBean) session.getAttribute("cartBean");
        if (cartBean == null) {
            cartBean = new CartBean();
            session.setAttribute("cartBean", cartBean);
        }

        String productId = request.getParameter("productId");
        String quantityString = request.getParameter("quantity");

        if (productId != null && !productId.trim().isEmpty()) {
            try {
                int quantity = Integer.parseInt(quantityString);
                if (quantity > 0) {
                    cartBean.addItem(productId, quantity);
                } else {
                    cartBean.removeItem(productId);
                }
                session.setAttribute("cartBean", cartBean);
            } catch (NumberFormatException e) {
                // Handle invalid quantity input
                // If quantity is invalid, don't update the cart and possibly log the error
            }
        }

        response.sendRedirect("cart.jsp");
    }
}