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

<title>Add shopping list</title>
</head>
<body>
<h1>Add shopping list</h1>

<!-- Formularz -->
	<div class="container">
		<form:form class="signUp" modelAttribute="list" method="post"
			action="/BudgetPlanner/shoppingList/addList">


			<form:input path="name" placeholder="Insert item name" />
			</br>
			<form:errors path="name" cssStyle="color:red"></form:errors>
			</br>
			<button class="form-btn sx log-in" type="submit" id="log">Add list</button>
			
			</form:form>
		
	</div>

</body>
</html>