<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shoppingcart.model.Product" %>
<%@ page import="com.shoppingcart.data.ProductDataStore" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Simple Store</title>
    <link rel="stylesheet" href="styles/styles.css">
    <link rel="stylesheet" href="styles/index.css">
</head>
<body>

<header>
    <h1>Welcome to The Simple Store</h1>
    <%
        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart != null && !cart.isEmpty()) { %>
    <a href="cart.jsp" class="cart-button">View Cart</a>
    <% } else { %>
    <div></div>
    <% } %>
</header>

<main class="container">
    <ul id="product-list">
        <%
            List<Product> products = ProductDataStore.getProducts();
            for(Product product : products) {
        %>
        <li>
            <strong><%= product.getName() %></strong>
            <span>$<%= String.format("%.2f", product.getPrice()) %></span>
            <form action="${pageContext.request.contextPath}/add-to-cart" method="post">
                <input type="hidden" name="productId" value="<%= product.getId() %>">
                <button type="submit">Add to Cart</button>
            </form>
        </li>
        <% } %>
    </ul>
</main>

<footer>
    <p>&copy; 2023 Simple Store, Inc. All rights reserved.</p>
</footer>

</body>
</html>