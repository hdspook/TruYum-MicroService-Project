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
<title>Showing Menu Item By Id</title>
</head>
<body>

	<p style="text-transform: uppercase; text-align: center;" class="h1">
		<span style="background-color: rgb(208, 233, 250)">Displaying
			Menu Item By Id</span>
	</p>
	<table class="table table-dark">
		<thead>
			<tr>
				<th>ID</th>
				<th>Description</th>
				<th>Price(₹)</th>
				<th>Action</th>
			</tr>

			<tr>
				<td>${menu.menuId}</td>
				<td>${menu.menuItem}</td>
				<td>${menu.price}</td>
				<td><a href="#" class="btn btn-info">Add to cart</a></td>
			</tr>
	</table>
</body>
</html>