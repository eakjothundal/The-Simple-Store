package com.shoppingcart.servlets;

import com.shoppingcart.beans.CartBean;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet(name = "RemoveFromCartServlet", urlPatterns = { "/remove-from-cart" })
public class RemoveFromCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartBean cartBean = (CartBean) session.getAttribute("cartBean");
        String productId = request.getParameter("productId");

        if (cartBean != null && productId != null) {
            cartBean.removeItem(productId);
            session.setAttribute("cartBean", cartBean);
        }

        response.sendRedirect("cart.jsp");
    }
}