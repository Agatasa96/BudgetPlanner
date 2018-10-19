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
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/singleInputStyle.css"
	type="text/css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>Save up history</title>
</head>
<body>

	<h1>Save up history</h1>
	<div class="singleInput">
		<h3>Search since (date)</h3>
		<form:form modelAttribute="date" method="post"
			action="/BudgetPlanner/saveUp/historyByDate">
			<input type="datetime-local" name="date" placeholder="yyyy-mm-dd">

			<button class="form-btn dx" type="submit">Ok</button>
		</form:form>
	</div>
	<!-- Table -->

	<div class="container">
		<table>
			<thead>
				<tr>
					<th>Total saved</th>
					<th>To save up</th>
					<th>Date</th>
					
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${saveUpHistory }" var="s" begin="${start }"
					end="${start+4 }">

					<tr>
						<td>${s[4]}</td>
						<td><c:choose>
								<c:when test="${s[2]==null }">
					${ s[3]}
					</c:when>
								<c:otherwise>
					${ s[2]}
					</c:otherwise>
							</c:choose></td>

						<td>${s[1]}</td>

						
					</tr>

				</c:forEach>

			</tbody>
		</table>
	</div>

	<!-- Buttons -->

	<div class="cont">
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/saveUp/historyNext">Next >></a>
			</p>
		</div>
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/saveUp/historyPrev"> << Prev</a>
			</p>
		</div>

	</div>

	<!-- Menu boczne -->

	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">
				<li><a href="/BudgetPlanner/main"> Home</a></li>
				<li><a href="/BudgetPlanner/saveUp/add">Save up</a></li>
				<li><a href="/BudgetPlanner/balance">Balance</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>


</body>
</html>