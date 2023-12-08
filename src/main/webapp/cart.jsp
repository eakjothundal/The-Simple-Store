<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.shoppingcart.model.Product" %>
<%@ page import="com.shoppingcart.data.ProductDataStore" %>
<html>
<head>
    <title>Your Shopping Cart</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<h1>Your Shopping Cart</h1>

<%
    @SuppressWarnings("unchecked")
    Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
    if (cart == null) {
        cart = new HashMap<>();
    }

    for (Map.Entry<String, Integer> entry : cart.entrySet()) {
        String productId = entry.getKey();
        Integer quantity = entry.getValue();
        Product product = ProductDataStore.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);
        if (product != null) {
%>
<p><strong><%= product.getName() %>:</strong> <%= quantity %> x $<%= String.format("%.2f", product.getPrice()) %></p>
<form action="<%= request.getContextPath() %>/update-cart" method="post">
    <input type="hidden" name="productId" value="<%= productId %>" />
    <label for="quantity_<%= productId %>">Quantity:</label>
    <input type="number" id="quantity_<%= productId %>" name="quantity" value="<%= quantity %>" min="1" />
    <input type="submit" value="Update" />
</form>
<form action="<%= request.getContextPath() %>/remove-from-cart" method="post" style="display:inline;">
    <input type="hidden" name="productId" value="<%= productId %>" />
    <input type="submit" value="Remove" />
</form>
<%
        }
    }
%>

<p><strong>Total:</strong> $<%= String.format("%.2f", cart.entrySet().stream()
        .mapToDouble(entry -> {
            Product product = ProductDataStore.getProducts().stream()
                    .filter(p -> p.getId().equals(entry.getKey()))
                    .findFirst()
                    .orElse(null);
            return product != null ? product.getPrice() * entry.getValue() : 0;
        })
        .sum()) %></p>

<form action="<%= request.getContextPath() %>/checkout" method="post">
    <label for="promoCode">Promo Code:</label>
    <input type="text" id="promoCode" name="promoCode" />
    <input type="submit" value="Checkout">
</form>

<a href="index.jsp">Continue Shopping</a>

</body>
</html>