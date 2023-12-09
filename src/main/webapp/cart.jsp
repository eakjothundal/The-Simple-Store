<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shoppingcart.beans.CartBean" %>
<%@ page import="com.shoppingcart.model.Product" %>
<%@ page import="com.shoppingcart.data.ProductDataStore" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <title>Your Shopping Cart</title>
    <link rel="stylesheet" href="styles/cart.css">
</head>
<body>

<h1>Your Shopping Cart</h1>

<div class="cart-container">
    <%
        CartBean cartBean = (CartBean) session.getAttribute("cartBean");
        if (cartBean == null) {
            cartBean = new CartBean();
            session.setAttribute("cartBean", cartBean);
        }
        Map<String, Integer> cart = cartBean.getItems();
        boolean hasItems = cart != null && !cart.isEmpty();

        if (hasItems) {
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                String productId = entry.getKey();
                Integer quantity = entry.getValue();
                Product product = ProductDataStore.getProduct(productId);
                if (product != null) {
    %>
    <div class="cart-item">
        <p class="cart-item-details">
            <strong><%= product.getName() %>:</strong> <%= quantity %> x $<%= String.format("%.2f", product.getPrice()) %>
        </p>
        <form action="update-cart" method="post">
            <input type="hidden" name="productId" value="<%= productId %>" />
            <input type="number" name="quantity" value="<%= quantity %>" min="1" />
            <input type="submit" value="Update" />
        </form>
        <form action="remove-from-cart" method="post" style="display:inline;">
            <input type="hidden" name="productId" value="<%= productId %>" />
            <input type="submit" value="Remove" />
        </form>
    </div>
    <%
            }
        }
    %>
    <!-- Display the cart summary -->
    <div class="cart-summary">
        <p class="total">
            <strong>Total:</strong>
            $<%= String.format("%.2f", cartBean.calculateSubtotal()) %>
        </p>
        <form action="checkout" method="post">
            <input type="text" name="promoCode" placeholder="Promo Code" />
            <input type="submit" value="Checkout" />
        </form>
    </div>
    <%
    } else {
    %>
    <p>Your cart is empty.</p>
    <%
        }
    %>
    <a href="index.jsp" class="continue-shopping">Continue Shopping</a>
</div>

</body>
</html>