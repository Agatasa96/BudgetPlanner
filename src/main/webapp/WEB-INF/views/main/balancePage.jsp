<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/balancePageStyle.css"
	type="text/css">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/tableStyle.css" type="text/css">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/buttonStyle.css" type="text/css">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/menuStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/menu.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>Balance</title>
</head>
<body>
	<h1>Balance</h1>
	<h2>
		Date:
		<%=LocalDate.now()%></h2>
	<!-- Table -->

	<div class="container">
		<table>
			<thead>
				<tr>
					<th>Total balance</th>
					<th>To spend</th>
					<th>To save up</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${savedBalance.totalBalance }</td>
					<td>${savedBalance.saveBalance }</td>
					<td>${savedBalance.totalSaved }</td>

				</tr>
			</tbody>
		</table>
	</div>

	<!-- Buttons -->

	<div class="cont">
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/balance/history">Balance history</a>
			</p>
		</div>
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/saveUp/history">Save-up history</a>
			</p>
		</div>
		
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/shoppingList">Show shopping list</a>
			</p>
		</div>

	</div>

	<!-- Menu boczne -->

	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">
				<li><a href="/BudgetPlanner/main"> Home</a></li>
				<li><a href="/BudgetPlanner/calendar"> Calendar</a></li>
				<li><a href="/BudgetPlanner/putInOut/form"> Pay in/out cash</a></li>
				<li><a href="/BudgetPlanner/saveUp/add"> Save-up </a></li>
				<li><a href="/BudgetPlanner/shoppingList"> Shopping list</a></li>
				<li><a href="/BudgetPlanner/">Log out</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>

</body>
</html>