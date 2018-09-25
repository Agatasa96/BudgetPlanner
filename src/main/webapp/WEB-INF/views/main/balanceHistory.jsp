<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/balancePageStyle.css"
	type="text/css">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/tableStyle.css" type="text/css">

<title>Balance history</title>
</head>
<body>
	<h1>Balance history</h1>
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
				<c:forEach items="${balanceHistory }" var="b">
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
</body>
</html>