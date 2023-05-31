<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail History User</title>
<%@ include file="/common/admin/link.jsp"%>

</head>
<body>

	<div class="wrapper">
		<%@ include file="/common/admin/sidebar.jsp"%>
		<div class="main-panel">
			<%@ include file="/common/admin/header.jsp"%>

			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card card-plain table-plain-bg">
								<div class="card-header">
									<h4 class="card-title">ORDER DETAILS</h4>
									<p class="card-category">List of client's order details</p>
								</div>

								<table class="table table-striped table-borderless table-hover">
									<thead>
										<tr class="text-left text-dark">
											<th class="text-left">ID</th>
											<th class="text-left">Name product</th>
											<th class="text-left">Quantity</th>
											<th class="text-left">Price</th>
											<th class="text-left">Created at</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach var="ItemOrder" items="${AllOrderItemHistory}"
											varStatus="loop">
											<tr class="text-left text-dark">

												<td class="text-left">${ItemOrder.id}</td>
												<td class="text-left">${ItemOrder.nameProduct}</td>
												<td class="text-left">${ItemOrder.orderItemQty}</td>
												<td class="text-left">${ItemOrder.price}</td>
												<td class="text-center">${ItemOrder.created_at}</td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/common/admin/footer.jsp"%>
	</div>

</body>
<%@ include file="/common/admin/script.jsp"%>
</html>
