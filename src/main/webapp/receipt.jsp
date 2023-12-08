<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Receipt</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>

<h1>Order Receipt</h1>

<p><strong>Subtotal:</strong> $<%= String.format("%.2f", request.getAttribute("subtotal")) %></p>
<p><strong>Discount:</strong> $<%= String.format("%.2f", request.getAttribute("discount")) %></p>
<p><strong>Tax:</strong> $<%= String.format("%.2f", request.getAttribute("tax")) %></p>
<p><strong>Shipping:</strong> $<%= String.format("%.2f", request.getAttribute("shippingCost")) %></p>
<p><strong>Total:</strong> $<%= String.format("%.2f", request.getAttribute("total")) %></p>

<a href="index.jsp">Finish</a>

</body>
</html>