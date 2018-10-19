<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/formStyle.css" type="text/css">

<script src="/BudgetPlanner/resources/js/form.js"></script>
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/menuStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/menu.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>Edit shopping list</title>
</head>
<body>
	<h1>Edit shopping list name</h1>

	<!-- Formularz -->
	<div class="container">
		<form:form class="signUp" modelAttribute="editList" method="post"
			action="/BudgetPlanner/shoppingList/editList">


			<form:input path="name" placeholder="Insert list name" />
			</br>
			<form:errors path="name" cssStyle="color:red"></form:errors>
			</br>
			<button class="form-btn sx log-in" type="submit" id="log">Edit
				name</button>

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