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
<%
        }
    }
%>

<a href="index.jsp">Continue Shopping</a>

</body>
</html>