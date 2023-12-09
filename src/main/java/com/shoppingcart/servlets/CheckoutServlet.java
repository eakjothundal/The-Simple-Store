package com.shoppingcart.servlets;

import com.shoppingcart.beans.CartBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CheckoutServlet", urlPatterns = { "/checkout" })
public class CheckoutServlet extends HttpServlet {
    private static final double TAX_RATE = 0.08; // For example, 8% tax
    private static final double SHIPPING_COST = 5.99; // Flat shipping cost

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartBean cartBean = (CartBean) session.getAttribute("cartBean");

        if (cartBean == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        double subtotal = cartBean.calculateSubtotal();

        String promoCode = request.getParameter("promoCode");
        double discount = 0;
        if (promoCode != null && promoCode.equals("SPECIAL10")) {
            discount = subtotal * 0.10; // 10% discount for the promo code SPECIAL10
        }

        double tax = (subtotal - discount) * TAX_RATE;
        double total = subtotal - discount + tax + SHIPPING_COST;

        request.setAttribute("subtotal", subtotal);
        request.setAttribute("discount", discount);
        request.setAttribute("tax", tax);
        request.setAttribute("shippingCost", SHIPPING_COST);
        request.setAttribute("total", total);

        cartBean.clearCart();
        session.setAttribute("cartBean", cartBean);

        RequestDispatcher dispatcher = request.getRequestDispatcher("receipt.jsp");
        dispatcher.forward(request, response);
    }
}