<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Shopping list</title>
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/shoppingListStyle.css"
	type="text/css">
</head>
<body>
	<h1>Shopping lists</h1>

<!-- Buttons -->

	<div class="cont">
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/shoppingList/addList">Add shopping list</a>
			</p>
		</div>

	</div>
	
	<div id="lists">
	<c:forEach items="${savedList }" var="sl" >
	<div class="list"><div class="name">${sl.name }</div>
	
	</div>
	<p>
				<a href="/BudgetPlanner/shoppingList/addItem/${sl.id }">Add items to this list</a>
			</p>
	
				</c:forEach>
	</div>
</body>
</html>