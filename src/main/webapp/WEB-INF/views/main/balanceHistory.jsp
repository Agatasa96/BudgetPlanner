<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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


<title>Balance history</title>
</head>
<body>
	<h1>Balance history</h1>

	<form:form modelAttribute="date" method="post"
		action="/BudgetPlanner/balance/historyByDate">
		<input type="datetime-local" name= "date" placeholder="yyyy-mm-dd">
		<button class="form-btn dx" type="submit">Ok</button>
	</form:form>
	<!-- Table -->

	<div class="container">
		<table>
			<thead>
				<tr>
					<th>Total balance</th>
					<th>Save up balance</th>
					<th>After shopping balance</th>
					<th>Pay in</th>
					<th>Pay out</th>
					<th>To save up</th>
					<th>Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${balanceHistory }" var="b" begin="${start }"
					end="${start+4 }">
					<tr>
						<td>${b[1]}</td>
						<td>${b[2]}</td>
						<td>${b[3]}</td>
						<td><c:choose>
								<c:when test="${b[4]==null }">
					${ b[6]}
					</c:when>
								<c:otherwise>
					${ b[4]}
					</c:otherwise>
							</c:choose></td>

						<td>${b[5]}</td>
						<td>${b[7]}</td>
						<td>${b[8]}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- Buttons -->

	<div class="cont">
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/balance/historyNext">Next >></a>
			</p>
		</div>
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/balance/historyPrev"> << Prev</a>
			</p>
		</div>

	</div>

	<!-- Menu boczne -->

	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">
				<li><a href="/BudgetPlanner/main"> Home</a></li>
				<li><a href="/BudgetPlanner/balance">Balance</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>


</body>
</html>