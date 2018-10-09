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
		<c:forEach items="${savedList }" var="sl">
			<div class="list">
				<div class="name">${sl.name }</div>

				<ul class="items">

					<c:forEach items="${itemsList }" var="i">
						<c:choose>
							<c:when test="${sl.id == i[4]}">
								<li>${i[0] } ${i[1]} zl</li>
							</c:when>
						</c:choose>
					</c:forEach>

				</ul>

				<div class="button_main">
					<p>
						<a href="/BudgetPlanner/shoppingList/addItem/${sl.id }">Add
							items to this list</a>
					</p>
				</div>
				<div class="button_main">
					<p>
						<a href="/BudgetPlanner/shoppingList/showItems/${sl.id }">Show
							items</a>
					</p>
				</div>
			</div>


		</c:forEach>


	</div>
</body>
</html>