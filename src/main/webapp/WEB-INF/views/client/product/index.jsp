<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Reach2 Store</title>
<%@ include file="/common/public/info/linkinfo.jsp"%>
<style>
a {
	text-decoration: none;
	color: black !important;
}
</style>
</head>
<body class="g-sidenav-show bg-gray-200">
	<div class="se-pre-con"></div>
	<%@ include file="/common/public/info/headerinfo.jsp"%>
	<div class="container-fluid py-4">
		<section>
			<div class="p-3 mb-2 bg-secondary text-white border-top">
				<div class="container">
					<h6 class="mb-0">
						<c:choose>
							<c:when test="${category.name ne null}">
								<a style="color: white !important;"
									href="${pageContext.request.contextPath}/category">Category</a>/<a
									style="color: white !important;"
									href="${pageContext.request.contextPath}/allProduct">${category.name}</a>
							</c:when>
							<c:otherwise>
								<a style="color: white !important;"
									href="${pageContext.request.contextPath}/category">Category</a>/<a
									style="color: white !important;"
									href="${pageContext.request.contextPath}/allProduct">All
									Product</a>
							</c:otherwise>
						</c:choose>

					</h6>
				</div>
			</div>

			<div class="py-5">
				<div class="container">
					<div class="row">
						<c:forEach var="product" items="${listProduct}">
							<div class="col-md-2 mb-3">
								<a href="<c:url value="./info/${product.id}"/>">
									<div class="card">
										<img
											src="${pageContext.request.contextPath}/template/admin/upload/
													<c:choose>
														<c:when test="${product.image ne null}">
															${product.image}		
														</c:when>
														<c:otherwise>
															springmvc.png
														</c:otherwise>
													</c:choose>"
											alt="" />
										<div class="card-body">
											<h5>${product.name}</h5>
											<span class="float-start">${product.selling_price}</span> <span
												class="float-end"><s>${product.og_price}</s></span>
										</div>
									</div>
								</a>
							</div>

						</c:forEach>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>