<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Product editing</title>
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
						<div class="col-md-8">
							<div class="card">
								<div class="card-header">
									<h4 class="card-title">Edit Product</h4>
								</div>
								<div class="card-body">
									<form method="POST" action="product/${product.id}/edit"
										enctype="multipart/form-data">
										<div class="row">
											<div class="col-md-3 px-1">
												<div class="form-group">
													<label>Category</label> <select class="form-control" 
													required name="category_id" id="category_id">
														<option value="" selected>Category</option>
														<c:forEach var="cate" items="${listCate}">															
															<c:choose>
														<c:when test="${cate.status == 1}">
														<option value="${cate.id}"
															${product.category_id == cate.id  ? 'selected' : ''}>${cate.name}</option>	
														</c:when>
														</c:choose>																								
														</c:forEach>
													</select>
													
												</div>
											</div>
											<div class="col-md-6 pr-1">
												<div class="form-group">
													<label>Name</label> <input type="text" class="form-control"
														value="${product.name}" placeholder="Product name" name="name" required>
												</div>
											</div>
										
										</div>
											<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>Description</label>
													<textarea rows="4" cols="80" class="form-control"
														name="description"
													 placeholder="Here can be your description" required>${product.description}</textarea>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 pr-1">
												<div class="form-group">
													<label>Original Price</label> <input type="text"
														class="form-control" name="og_price" value="${product.og_price}"
														placeholder="Original Price" required>
												</div>
											</div>
											<div class="col-md-6 pl-1">
												<div class="form-group">
													<label>Selling Price</label> <input type="text"
														class="form-control" name="selling_price" value="${product.selling_price}"
														placeholder="Selling Price" required>
												</div>
											</div>
										</div>
									
										<div class="row">
											<div class="col-md-4 pr-1">
												<div class="form-group">
													<label>Status</label> <select class="form-control"
														name="status" id="status">
														<option value="1" ${product.status == 1 ? 'selected' : ''}>Selling</option>
														<option value="0"${product.status == 0 ? 'selected' : ''}>Stop</option>
													</select>

												</div>
											</div>
											<div class="col-md-4 pr-1">
												<div class="form-group">
													<label>Trending</label> <select class="form-control"
														name="trending" id="trending">
														<option value="1" ${product.trending == 1 ? 'selected' : ''}>Trending</option>
														<option value="0"${product.trending == 0 ? 'selected' : ''}>No</option>
													</select>

												</div>
											</div>

											<div class="col-md-4 px-1">
												<div class="form-group">
													<label class=""  for="file">Image</label> 	
												<input type="hidden" name="image" value="${product.image}"/>									
													<input class="form-control" type="file" name="file" id="file"  />						
												</div>
											</div>

										</div>
										<div class="row">
											<div class="col-md-6 mb-3">
												<div class="form-group">
													<label>Quantity</label> <input type="number" 
													min="0"	value="${product.quantity}" name="quantity" class="form-control" placeholder="Quantity" required/>
												</div>
											</div>
										</div>
										<input type="submit" class="btn btn-info btn-fill pull-right">Add
										Product
										<div class="clearfix"></div>
									</form>
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

