<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Cart</title>

<%@ include file="/common/public/info/linkinfo.jsp"%>
</head>
<body>
	<div class="se-pre-con"></div>
	<%@ include file="/common/public/info/headerinfo.jsp"%>

	<div class="container">
		<header class="text-center bg-light text-dark py-3 rounded mt-3 mb-3">
			<h1 class="display-5 font-weight-bold">CART</h1>
		</header>

		<div class="rows">
			<div class="col-sm-12">
				<table class="table table-striped table-hover text-center">
					<thead>
						<tr class="text-light bg-dark">
							<th>Id</th>
							<th>Name</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Amount</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

						<form action="${pageContext.request.contextPath}/update"
							method="post">
							<c:forEach var="item" items="${CART_ITEMS}">
								<input type="hidden" name="id" value="${item.productID}" />
								<tr>
									<td>${item.productID }</td>
									<td>${item.name }</td>
									<td><fmt:formatNumber value="${item.price}"
											type="currency" currencyCode="VND" /></td>
									<td><input name="qty" value="${item.qty}"
										onblur="this.form.submit()" style="width: 50px;"></td>
									
									<td><fmt:formatNumber value="${item.price*item.qty}"
											type="currency" currencyCode="VND" /></td>
									<td><a class="btn btn-dark btn-sm"
										href="${pageContext.request.contextPath}/delete/${item.productID }">Remove</a></td>
								</tr>
						</form>
						</c:forEach>
					</tbody>
				</table>
				<p class="text-danger font-weight-bold">Total Amount:
				<fmt:formatNumber value="${TOTAL_PRICE}"
											type="currency" currencyCode="VND" />
					</p>
				<hr />
				<a class="btn btn-dark btn-sm"
					href="${pageContext.request.contextPath}/clear">Clear Cart</a> <a
					class="btn btn-dark btn-sm"
					href="${pageContext.request.contextPath}/clienthome">Add more</a> <a
					class="btn btn-dark btn-sm"
					href="${pageContext.request.contextPath}/checkout">Check out</a>
			</div>
		</div>

	</div>
</body>
</html>