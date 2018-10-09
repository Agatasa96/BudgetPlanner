<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/addItemStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/form.js"></script>
<title>Add item to list</title>
</head>
<body>

	<h1>Add item to shopping list</h1>
	<div class="left">
		<h3>Shopping list ${shopingList.name }</h3>
		<ul>
		<c:forEach items="${itemList }" var="i">
			<li>${i.name }</li>
			

		</c:forEach>
		</ul>
	</div>
	<!-- Formularz -->
	<div class="container">
		<form:form class="signUp" modelAttribute="item" method="post"
			action="/BudgetPlanner/shoppingList/addItem">


			<form:input path="name" placeholder="Insert item name" />
			</br>
			<form:errors path="name" cssStyle="color:red"></form:errors>
			</br>

			<form:input path="price" placeholder="Insert price" />
			</br>
			<form:errors path="price" cssStyle="color:red"></form:errors>
			</br>
			<button class="form-btn dx" type="submit">Add to list</button>
		<button class="form-btn sx log-in" type="button" id="log"> <a href="/BudgetPlanner/shoppingList/addList">Save list</a></button>
		</form:form>

	</div>


</body>
</html>