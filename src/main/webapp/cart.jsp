<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.shoppingcart.model.Product" %>
<%@ page import="com.shoppingcart.data.ProductDataStore" %>
<html>
<head>
    <title>Your Shopping Cart</title>
    <link rel="stylesheet" href="styles/styles.css">
    <link rel="stylesheet" href="styles/cart.css">
</head>
<body>

<h1>Your Shopping Cart</h1>

<div class="cart-container">
    <%
        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        boolean hasItems = !cart.isEmpty();

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String productId = entry.getKey();
            Integer quantity = entry.getValue();
            Product product = ProductDataStore.getProducts().stream()
                    .filter(p -> p.getId().equals(productId))
                    .findFirst()
                    .orElse(null);
            if (product != null) {
    %>
    <div class="cart-item">
        <p class="cart-item-details">
            <strong><%= product.getName() %>:</strong> <%= quantity %> x $<%= String.format("%.2f", product.getPrice()) %>
        </p>
        <form action="<%= request.getContextPath() %>/update-cart" method="post">
            <input type="hidden" name="productId" value="<%= productId %>" />
            <label for="quantity_<%= productId %>">Quantity:</label>
            <input type="number" id="quantity_<%= productId %>" class="quantity-selector" name="quantity" value="<%= quantity %>" min="1" />
            <input type="submit" value="Update" />
        </form>
        <form action="<%= request.getContextPath() %>/remove-from-cart" method="post" style="display:inline;">
            <input type="hidden" name="productId" value="<%= productId %>" />
            <input type="submit" value="Remove" />
        </form>
    </div>
    <%
            }
        }

        if (hasItems) {
    %>
    <div class="cart-summary">
        <p class="total">
            <strong>Total:</strong> $<%= String.format("%.2f", cart.entrySet().stream().mapToDouble(entry -> {
            Product product = ProductDataStore.getProducts().stream()
                    .filter(p -> p.getId().equals(entry.getKey()))
                    .findFirst()
                    .orElse(null);
            return product != null ? product.getPrice() * entry.getValue() : 0;
        }).sum()) %>
        </p>
        <form action="<%= request.getContextPath() %>/checkout" method="post" class="checkout-form">
            <label for="promoCode">Promo Code:</label>
            <input type="text" id="promoCode" name="promoCode" />
            <input type="submit" value="Checkout">
        </form>
    </div>
    <% } %>

    <a href="index.jsp" class="continue-shopping">Continue Shopping</a>
</div>

</body>
</html>