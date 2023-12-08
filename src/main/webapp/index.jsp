<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shoppingcart.model.Product" %>
<%@ page import="com.shoppingcart.data.ProductDataStore" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Our Simple Store</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<form action="cart.jsp" method="get">
    <input type="submit" value="View Cart">
</form>

<h1>Welcome to Our Simple Store</h1>

<ul id="product-list">
    <%
        List<Product> products = ProductDataStore.getProducts();
        for(Product product : products) {
    %>
    <li>
        <strong><%= product.getName() %></strong> - $<%= String.format("%.2f", product.getPrice()) %>
        <form action="${pageContext.request.contextPath}/add-to-cart" method="post">
            <input type="hidden" name="productId" value="<%= product.getId() %>" />
            <input type="submit" value="Add to Cart" />
        </form>
    </li>
    <% } %>
</ul>

</body>
</html>