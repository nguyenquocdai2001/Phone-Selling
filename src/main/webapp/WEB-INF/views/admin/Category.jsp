<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Category adding</title>
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
							<div class="card strpied-tabled-with-hover">
								<div class="card-header ">
									<h4 class="card-title">Categories</h4>
									<p class="card-category">Here is all category that we have</p>
								</div>
								<div class="card-body table-full-width table-responsive">
									<table id="category"class="table table-hover table-striped">
										<thead>
											<th>ID</th>
											<th>Name</th>
											<th>Description</th>
											<th>Status</th>
											<th>Image</th>
											<th>Action</th>
										</thead>
										<tbody>
											<c:forEach var="category" items="${listCate}">
												<tr>
													<td>${category.id}</td>
													<td>${category.name}</td>
													<td>${category.description}</td>
													<td>${category.status == '0' ? 'Stop' : 'Selling'}</td>
													<td><img class="img" height="60px" width="60px"
														src="${pageContext.request.contextPath}/template/admin/upload/
													<c:choose>
														<c:when test="${category.image ne null}">
															${category.image}		
														</c:when>
														<c:otherwise>
															springmvc.png
														</c:otherwise>
													</c:choose>
												" />
													</td>
													<td><a class="mr-5"
														href="<c:url value="edit-category/${category.id}"/>">Edit</a>
														<a href="<c:url value="delete-category/${category.id}"/>">Delete</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="card card-plain table-plain-bg">
								<div class="card-header ">
									<h4 class="card-title">Products List</h4>
									<p class="card-category">Here</p>
								</div>
								<div class="card-body table-full-width table-responsive">
									<table id="product"class="table table-hover">
										<thead>																					
											<th>Name</th>
											<th>Category</th>
											<th>Description</th>
											<th>Status</th>
											<th>Original price</th>
											<th>Selling price</th>										
											<th>Quantity</th>
											<th>Trending</th>
											<th>Image</th>
											<th>Action</th>
										</thead>
										<tbody>
												<c:forEach var="product" items="${listProduct}">
												<tr>												
													<td>${product.name}</td>
													<td>${product.categoryName}</td>
													<td>${product.description}</td>
													<td>${product.status == '0' ? 'Stop' : 'Selling'}</td>
													<td>${product.og_price}</td>
													<td>${product.selling_price}</td>
													<td>${product.quantity}</td>
													<td>${product.trending == '0' ? 'No' : 'Trending'}</td>
													<td><img class="img" height="60px" width="60px"
														src="${pageContext.request.contextPath}/template/admin/upload/
													<c:choose>
														<c:when test="${product.image ne null}">
															${product.image}		
														</c:when>
														<c:otherwise>
															springmvc.png
														</c:otherwise>
													</c:choose>
												" />
													</td>
													<td><a class="mr-5"
														href="<c:url value="edit-product/${product.id}"/>">Edit</a>
														<a href="<c:url value="delete-product/${product.id}"/>">Delete</a>
													</td>
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
	</div>
</body>
<%@ include file="/common/admin/script.jsp"%>

</html>

