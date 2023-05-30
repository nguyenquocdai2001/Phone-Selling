<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Cart</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        
         <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
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
						
							<form action="${pageContext.request.contextPath}/update" method="post">
							<c:forEach var="item" items="${CART_ITEMS}">
								<input type="hidden" name="id" value="${item.productID}"/>
								<tr>
									<td>${item.productID }</td>
									<td>${item.name }</td>
									<td>${item.price}</td>
									<td><input name="qty" value="${item.qty}"
										onblur="this.form.submit()" style="width: 50px;"></td>
									<td>${item.price*item.qty}</td>
									<td><a class="btn btn-dark btn-sm"
										href="${pageContext.request.contextPath}/delete/${item.productID }">Remove</a></td>
								</tr>
							</form>
						</c:forEach>
					</tbody>
				</table>
				<p class="text-danger font-weight-bold">Total Amount: ${TOTAL_PRICE}</p>
				<hr />
				<a class="btn btn-dark btn-sm" href="${pageContext.request.contextPath}/clear">Clear Cart</a> 
				<a class="btn btn-dark btn-sm" href="${pageContext.request.contextPath}/clienthome">Add more</a>
				<a class="btn btn-dark btn-sm" href="${pageContext.request.contextPath}/checkout">Check out</a>	
			</div>
		</div>

	</div>
</body>
</html>