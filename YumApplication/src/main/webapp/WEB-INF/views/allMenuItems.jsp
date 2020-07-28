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
<title>Showing All Menu Items</title>
</head>
<body>

	<p style="text-transform: uppercase; text-align: center;" class="h1">
		<span style="background-color: rgb(208, 233, 250)">Displaying
			all menu items</span>
	</p>
	<table class="table table-dark">
		<thead>
			<tr>
				<th>ID</th>
				<th>Description</th>
				<th>Price(₹)</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${menuItems}" var="item">
				<tr>
					<td>${item.menuId}</td>
					<td>${item.menuItem}</td>
					<td>${item.price}</td>
					<td><a href="addToCart/${item.menuId}" class="btn btn-info">Add
							to cart</a></td>
				</tr>
			</c:forEach>
	</table>
	<p class="" style="text-align: right;">
		<a href="/yum/cart" class="btn btn-link btn-lg">Go to cart</a>
	</p>
</body>
</html>