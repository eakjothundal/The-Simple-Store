package com.shoppingcart.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "UpdateCartServlet", urlPatterns = { "/update-cart" })
public class UpdateCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        String productId = request.getParameter("productId");
        String quantityString = request.getParameter("quantity");

        // Perform update only if the cart and product ID are valid
        if (cart != null && productId != null && !productId.trim().isEmpty()) {
            try {
                int quantity = Integer.parseInt(quantityString);
                if (quantity > 1) {
                    cart.put(productId, quantity);
                } else {
                    cart.remove(productId);
                }
                session.setAttribute("cart", cart);
            } catch (NumberFormatException e) {
                // Handle invalid quantity input
            }
        }

        response.sendRedirect("cart.jsp");
    }
}
