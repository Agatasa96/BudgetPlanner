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
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/menuStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/menu.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>Add item to list</title>
</head>
<body>

	<h1>Add item to shopping list</h1>
	<div class="left">
		<h3>Shopping list ${shopingList.name }</h3>
		<ul class="item">
			<c:forEach items="${itemList }" var="i">
				<li class="item">${i.itemName } ${i.price }</li>

			</c:forEach>
		</ul>
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/shoppingList/">Save list</a>
			</p>
		</div>
	</div>
	<!-- Formularz -->
	<div class="container">
		<form:form class="signUp" modelAttribute="item" method="post"
			action="/BudgetPlanner/shoppingList/addItem">


			<form:input path="itemName" placeholder="Insert item name" />
			</br>
			<form:errors path="itemName" cssStyle="color:red"></form:errors>
			</br>

			<form:input path="price" placeholder="Insert price" />
			</br>
			<form:errors path="price" cssStyle="color:red"></form:errors>
			</br>
			<button class="form-btn dx" type="submit">Add to list</button>
		</form:form>

	</div>
	<!-- Menu boczne -->

	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">
				<li><a href="/BudgetPlanner/main"> Home</a></li>
				<li><a href="/BudgetPlanner/shoppingList"> Shopping list</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>

</body>
</html>