package com.shoppingcart.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddToCartServlet", value = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        // Retrieve the selected product ID from the form submission
        String productId = request.getParameter("productId");
        if (productId != null && !productId.trim().isEmpty()) {
            cart.put(productId, cart.getOrDefault(productId, 0) + 1); // Add or increment quantity
            session.setAttribute("cart", cart); // Save the cart back to the session
            System.out.println("Cart: " + cart);
        }

        // Redirect back to the product list (index.jsp) or to the cart page
        response.sendRedirect("index.jsp");
    }
}
