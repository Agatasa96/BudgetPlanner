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
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/menuStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/menu.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
	<h1>Shopping lists</h1>

	<h3>Total balance: ${savedBalance.totalBalance }</h3>
	<h3>Balance after save: ${savedBalance.saveBalance }</h3>
	<!-- Button -->

	<div class="cont">
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/shoppingList/addList">Add shopping list</a>
			</p>
		</div>
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/shoppingList/resetBalance">Reset balance</a>
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
								<li>${i[0] } ${i[1]}</li>
							</c:when>
						</c:choose>
					</c:forEach>
				</ul>
				<c:choose>
					<c:when test="${itemsList.size()-1==-1}">

					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${sl.id ==itemsList.get(itemsList.size()-1)[4]}">
								<p class="items">Total price:
									${itemsList.get(itemsList.size()-1)[5] }</p>
							</c:when>
						</c:choose>
					</c:otherwise>
				</c:choose>


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
				<div class="button_main">
					<p>
						<a href="/BudgetPlanner/shoppingList/countBalance/${sl.id }">Calculate
							balance</a>
					</p>
				</div>
				<div class="button_main">
					<p>
						<a href="/BudgetPlanner/shoppingList/buyList/${sl.id }">Buy
							items</a>
					</p>
				</div>
				<div class="button_main">
					<p>
						<a href="/BudgetPlanner/shoppingList/editList/${sl.id }">Edit
							list name</a>
					</p>
				</div>
				<div class="button_main">
					<p>
						<a href="/BudgetPlanner/shoppingList/deleteList/${sl.id }">Delete
							list</a>
					</p>
				</div>
			</div>
		</c:forEach>
	</div>

	<!-- Menu boczne -->

	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">
				<li><a href="/BudgetPlanner/main"> Home</a></li>
				<li><a href="/BudgetPlanner/calendar"> Calendar</a></li>
				<li><a href="/BudgetPlanner/balance"> Balance</a></li>
				<li><a href="/BudgetPlanner/putInOut/form"> Pay in/out cash</a></li>
				<li><a href="/BudgetPlanner/saveUp/add"> Save-up </a></li>
				<li><a href="/BudgetPlanner/">Log out</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>
</body>
</html>