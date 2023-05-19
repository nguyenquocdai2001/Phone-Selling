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
							<div class="col-md-8">
								<div class="card">
									<div class="card-header">
										<h4 class="card-title">Add Category</h4>
									</div>
									<div class="card-body">
										<form action="addCategoryPost">
											<div class="row">
												<div class="col-md-6 pr-1">
													<div class="form-group">
														<label>Name</label> <input type="text"
															class="form-control" placeholder="Category name"
															name="name">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<div class="form-group">
														<label>Description</label>
														<textarea rows="4" cols="80" class="form-control"
														name="description"placeholder="Here can be your description" ></textarea>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-4 pr-1">
													<div class="form-group">
														<label>Status</label> <input type="number"
														name="status"class="form-control" placeholder="1 or 0" >
														
													</div>
												</div>
												<div class="col-md-4 px-1">
													<div class="form-group">
														<label>Image</label> <input type="text"
														name="image"class="form-control" placeholder="Image name">
													</div>
												</div>
										
											</div>

											<input type="submit"
												class="btn btn-info btn-fill pull-right">Update
												Category 
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

