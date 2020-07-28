<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<meta charset="UTF-8">
<title>Added to Cart</title>
</head>
<body>

	<p style="text-transform: uppercase; text-align: center;" class="h1">
		<span style="background-color: rgb(208, 233, 250)">Item added
			to cart</span>
	</p>
	<table class="table table-dark">
		<thead>
			<tr>
				<th>ID</th>
				<th>Description</th>
				<th>Price(₹)</th>
				<th>Your Id</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${cartItems}" var="item">
				<tr>
					<td>${item.menuId}</td>
					<td>${item.menuItem}</td>
					<td>${item.price}</td>
					<td>${item.userId }
					<td><a
						href="http://localhost:8101/yum/removeFromCart/${item.menuId}"
						class="btn btn-danger">Remove</a></td>
				</tr>

			</c:forEach>
	</table>
	<p style="text-align: center;" class="display-3" data-format="#.00">
		Your total is: <span style="color: red;">₹${total}</span>
	</p>
	<p>
		<a href="http://localhost:8101/yum/find">Go back to menu</a>
	</p>
	<p style="text-align: center">
		<a href="http://localhost:8101/yum/checkout" class="btn btn-success">Checkout</a>
	</p>
</body>
</html>