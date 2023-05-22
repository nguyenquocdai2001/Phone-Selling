<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Product adding</title>
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
									<h4 class="card-title">Add Product</h4>
								</div>
								<div class="card-body">
									<form method="POST" action="addProduct"
										enctype="multipart/form-data">
										<div class="row">
											<div class="col-md-3 px-1">
												<div class="form-group">
													<label>Category</label> <select class="form-control" 
													required name="category_id" id="category_id">
														<option value="" selected>Category</option>
														<c:forEach var="cate" items="${listCate}">
															<option value="${cate.id}">${cate.name}</option>													
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="col-md-6 pr-1">
												<div class="form-group">
													<label>Name</label> <input type="text" class="form-control"
														placeholder="Product name" name="name" required>
												</div>
											</div>
										
										</div>
											<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>Description</label>
													<textarea rows="4" cols="80" class="form-control"
														name="description"
														placeholder="Here can be your description" required></textarea>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 pr-1">
												<div class="form-group">
													<label>Original Price</label> <input type="text"
														class="form-control" name="og_price"
														placeholder="Original Price" required>
												</div>
											</div>
											<div class="col-md-6 pl-1">
												<div class="form-group">
													<label>Selling Price</label> <input type="text"
														class="form-control" name="selling_price"
														placeholder="Selling Price" required>
												</div>
											</div>
										</div>
									
										<div class="row">
											<div class="col-md-4 pr-1">
												<div class="form-group">
													<label>Status</label> <select class="form-control"
														name="status" id="status">
														<option value="1">Selling</option>
														<option value="0">Stop</option>
													</select>

												</div>
											</div>
											<div class="col-md-4 pr-1">
												<div class="form-group">
													<label>Trending</label> <select class="form-control"
														name="trending" id="trending">
														<option value="1">Trending</option>
														<option value="0">No</option>
													</select>

												</div>
											</div>

											<div class="col-md-4 px-1">
												<div class="form-group">
													<label for="file">Image</label> <input class="form-control"
														type="file" id="file" name="file" required />
												</div>
											</div>

										</div>
										<div class="row">
											<div class="col-md-6 mb-3"">
												<div class="form-group">
													<label>trending</label> <input type="number" 
														name="quantity" class="form-control" placeholder="Quantity" required/>
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
						<div class="col-md-4">
							<div class="card card-user">
								<div class="card-image">
									<img
										src="https://ununsplash.imgix.net/photo-1431578500526-4d9613015464?fit=crop&fm=jpg&h=300&q=75&w=400"
										alt="...">
								</div>
								<div class="card-body">
									<div class="author">
										<a href="#"> <img class="avatar border-gray"
											src="<c:url value="/template/admin/img/faces/face-3.jpg"/>">

											<h5 class="title">Mike Andrew</h5>
										</a>
										<p class="description">michael24</p>
									</div>
									<p class="description text-center">
										"Lamborghini Mercy <br> Your chick she so thirsty <br>
										I'm in that two seat Lambo"
									</p>
								</div>
								<hr>
								<div class="button-container mr-auto ml-auto">
									<button href="#" class="btn btn-simple btn-link btn-icon">
										<i class="fa fa-facebook-square"></i>
									</button>
									<button href="#" class="btn btn-simple btn-link btn-icon">
										<i class="fa fa-twitter"></i>
									</button>
									<button href="#" class="btn btn-simple btn-link btn-icon">
										<i class="fa fa-google-plus-square"></i>
									</button>
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

