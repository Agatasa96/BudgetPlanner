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
		<ul id="items">
		<c:forEach items="${itemList }" var="i">
			<li>${i.itemName }</li>
			
		</c:forEach>
		</ul>
		<div class="button_main">
		<p><a href="/BudgetPlanner/shoppingList/">Save list</a></p></div>
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
		<button class="form-btn sx log-in"  type= "button" id="log"> </button>
		</form:form>

	</div>


</body>
</html>