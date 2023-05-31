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

	<%@ include file="/common/public/info/headerinfo.jsp"%>
	<div class="container-fluid py-4">
		<section>
			<div class="p-3 mb-2 bg-secondary text-white border-top">
				<div class="container">
					<h6 class="mb-0">
					<a style="color: white !important;"
			href="${pageContext.request.contextPath}/category">Category</a> 
					</h6>
				</div>
			</div>

			<div class="py-5">
				<div class="container">
					<div class="row">
						<c:forEach var="cate" items="${listCate}">
						<c:choose>
						<c:when test="${cate.status != 0}">
								<div class="col-md-3 mb-3">
								<a href="${pageContext.request.contextPath}/category/${cate.id}">
									<div class="card">
										<img
											src="${pageContext.request.contextPath}/template/admin/upload/
													<c:choose>
														<c:when test="${cate.image ne null}">
															${cate.image}		
														</c:when>
														<c:otherwise>
															springmvc.png
														</c:otherwise>
													</c:choose>"
											alt="" />
										<div class="card-body">
											<h5>${cate.name}</h5>
											
										</div>
									</div>
								</a>
							</div>
						</c:when>
						</c:choose>
						</c:forEach>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>