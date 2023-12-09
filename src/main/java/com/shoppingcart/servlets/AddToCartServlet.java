package com.shoppingcart.servlets;

import com.shoppingcart.beans.CartBean;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet(name = "AddToCartServlet", urlPatterns = { "/add-to-cart" })
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        CartBean cartBean = (CartBean) session.getAttribute("cartBean");

        if (cartBean == null) {
            cartBean = new CartBean();
        }

        String productId = request.getParameter("productId");
        String quantityString = request.getParameter("quantity");
        int quantity = 1; // Assume adding one product if the quantity is not specified

        if (quantityString != null && !quantityString.isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityString);
            } catch (NumberFormatException e) {
                // Invalid quantity. Log this or handle this as per your application's requirements.
            }
        }

        if (productId != null && !productId.trim().isEmpty() && quantity > 0) {
            cartBean.addItem(productId, quantity);
            session.setAttribute("cartBean", cartBean);
        }

        response.sendRedirect("index.jsp");
    }
}