package com.shoppingcart.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "RemoveFromCartServlet", urlPatterns = { "/remove-from-cart" })
public class RemoveFromCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        String productId = request.getParameter("productId");

        if (cart != null && productId != null) {
            cart.remove(productId);
            session.setAttribute("cart", cart);
        }

        response.sendRedirect("cart.jsp");
    }
}
