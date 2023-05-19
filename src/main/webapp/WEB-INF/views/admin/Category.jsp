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
									<table class="table table-hover table-striped">
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
													<td>${category.image}</td>
													<td>
													<a class="mr-5" href="<c:url value="edit-category/${category.id}"/>">Edit</a>
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
									<h4 class="card-title">Table on Plain Background</h4>
									<p class="card-category">Here is a subtitle for this table</p>
								</div>
								<div class="card-body table-full-width table-responsive">
									<table class="table table-hover">
										<thead>
											<th>ID</th>
											<th>Name</th>
											<th>Salary</th>
											<th>Country</th>
											<th>City</th>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>Dakota Rice</td>
												<td>$36,738</td>
												<td>Niger</td>
												<td>Oud-Turnhout</td>
											</tr>
											<tr>
												<td>2</td>
												<td>Minerva Hooper</td>
												<td>$23,789</td>
												<td>Curaçao</td>
												<td>Sinaai-Waas</td>
											</tr>
											<tr>
												<td>3</td>
												<td>Sage Rodriguez</td>
												<td>$56,142</td>
												<td>Netherlands</td>
												<td>Baileux</td>
											</tr>
											<tr>
												<td>4</td>
												<td>Philip Chaney</td>
												<td>$38,735</td>
												<td>Korea, South</td>
												<td>Overland Park</td>
											</tr>
											<tr>
												<td>5</td>
												<td>Doris Greene</td>
												<td>$63,542</td>
												<td>Malawi</td>
												<td>Feldkirchen in Kärnten</td>
											</tr>
											<tr>
												<td>6</td>
												<td>Mason Porter</td>
												<td>$78,615</td>
												<td>Chile</td>
												<td>Gloucester</td>
											</tr>
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

