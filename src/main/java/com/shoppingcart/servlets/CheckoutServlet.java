package com.shoppingcart.servlets;

import com.shoppingcart.data.ProductDataStore;
import com.shoppingcart.model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "CheckoutServlet", urlPatterns = { "/checkout" })
public class CheckoutServlet extends HttpServlet {
    private static final double TAX_RATE = 0.08; // For example, 8% tax
    private static final double SHIPPING_COST = 5.99; // Flat shipping cost

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the shopping cart or create it if it's not in the session yet
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Calculate the subtotal for the items in the cart
        double subtotal = cart.entrySet().stream()
                .mapToDouble(entry -> {
                    Product product = ProductDataStore.getProduct(entry.getKey());
                    return product.getPrice() * entry.getValue();
                })
                .sum();

        // Apply discounts if a promo code is used (this is simplified)
        String promoCode = request.getParameter("promoCode");
        double discount = 0;
        if (promoCode != null && promoCode.equals("SPECIAL10")) {
            discount = subtotal * 0.1; // 10% discount for the promo code SPECIAL10
        }

        double tax = (subtotal - discount) * TAX_RATE;
        double total = subtotal - discount + tax + SHIPPING_COST;

        // Pass along the totals to the request (jsp can pull from request scope)
        request.setAttribute("subtotal", subtotal);
        request.setAttribute("discount", discount);
        request.setAttribute("tax", tax);
        request.setAttribute("shippingCost", SHIPPING_COST);
        request.setAttribute("total", total);

        cart.clear();
        session.setAttribute("cart", cart);

        // Forward to the JSP page that will show the receipt
        RequestDispatcher dispatcher = request.getRequestDispatcher("receipt.jsp");
        dispatcher.forward(request, response);
    }
}
