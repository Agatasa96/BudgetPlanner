<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add shopping list</title>
</head>
<body>

<h1>Add shopping list</h1>

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
			</form:form>
		
	</div>
	
	<!-- Buttons -->

	<div class="cont">
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/shoppingList/addList">Save list</a>
			</p>
		</div>

	</div>
</body>
</html>