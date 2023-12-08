<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shoppingcart.model.Product" %>
<%@ page import="com.shoppingcart.data.ProductDataStore" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Our Simple Store</title>
    <link rel="stylesheet" href="styles/styles.css">
    <link rel="stylesheet" href="styles/index.css">
</head>
<body>

<header>
    <h1>Welcome to Our Simple Store</h1>
    <a href="cart.jsp" class="cart-button">View Cart</a>
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