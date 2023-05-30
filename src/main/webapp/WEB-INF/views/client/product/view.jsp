<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
double rate = (double) pageContext.findAttribute("rate");
int ratenum = (int) Math.round(rate);
int rateNum = (int) pageContext.findAttribute("rateNum");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Reach2 Store</title>
<%@ include file="/common/public/info/linkinfo.jsp"%>
<style type="text/css">
.checked {
	color: #ffe400;
}
a {
	text-decoration: none;
	
}
</style>
</head>
<body>
	<div class="se-pre-con"></div>
	<%@ include file="/common/public/info/headerinfo.jsp"%>
	<section style="margin: 0 0 0 0 !important">
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form action="${pageContext.request.contextPath}/add-rating"
						method="POST">
						<input type="hidden" name="prod_id" value="${product.id}">
						<input type="hidden" name="user_id" value="${userSession.id}">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id="exampleModalLabel">Rating
								${product.name}</h1>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div class="rating-css">
								<div class="star-icon">
									<c:choose>
										<c:when test="${user_rating}">
											<c:forEach var="i" begin="0"
												end="${user_rating.stars_rated-1}">
												<input type="radio" value="${i}" name="stars_rated" checked
													id="rating${i}">
												<label for="rating${i}" class="fa fa-star"></label>
											</c:forEach>
											<c:forEach var="j" begin="${user_rating.stars_rated}" end="5">
												<input type="radio" value="${j}" name="stars_rated"
													id="rating${j}">
												<label for="rating${j}" class="fa fa-star"></label>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach var="i" begin="1" end="5">
												<input type="radio" value="${i}" name="stars_rated" checked
													id="rating${i}">
												<label for="rating${i}" class="fa fa-star"></label>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="p-3 mb-2 bg-secondary text-white border-top">
			<div class="container">
				<h6 class="mb-0">
					<a style="color: #ebf0f5 !important"
						href="${pageContext.request.contextPath}/category"> Category </a>/
					<a style="color: #ebf0f5 !important"
						href="${pageContext.request.contextPath}/category/${product.categoryName}">
						${product.categoryName} </a>/ <a style="color: #ebf0f5 !important"
						href="${pageContext.request.contextPath}/category/${product.categoryName}">
						${product.name} </a>
				</h6>
			</div>
		</div>

		<div class="container pb-5">
			<div class="card shadow">
				<div class="card-body">
					<div class="row">
						<div class="col-md-4 border-right">
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
								alt="" class="w-100" />
						</div>
						<div class="col-md-7">
							<h1 class="mb-0">${product.name}
							<c:choose>
								<c:when test="${product.status ==1 }">
								<label style="font-size: 16px;"
									class="float-end badge bg-danger">${product.trending == '1' ? 'Trending' : ''}</label>
								</c:when>
							
							</c:choose>
								
							</h1>
							<hr>

							<lable class="me-3"> Original Price : <s>${product.og_price}$</s>
							</lable>
							<lable class="fw-bold">Selling Price :
							${product.selling_price}$</lable>

							<div class="rating mt-2">
								<%
								for (int i = 0; i < ratenum; i++) {
								%>
								<i class="fa fa-star checked"></i>
								<%
								}
								%>
								<%
								for (int j = ratenum + 1; j <= 5; j++) {
								%>
								<i class="fa fa-star"></i>
								<%
								}
								%>
								<span> <%
 if (rateNum > 0) {
 %> <%=rateNum%> Đánh giá <%
 } else {
 %> Chưa có đánh giá <%
 }
 %>
								</span>
							</div>
							<p class="mt-3">${product.description}</p>
							<hr>
							<c:choose>
							<c:when test="${product.status ==1 }">
							<c:choose>
								<c:when test="${product.quantity > 0}">
									<label class="badge bg-success">In stock</label>
								</c:when>
								<c:otherwise>
									<label class="badge bg-danger">Run out</label>
								</c:otherwise>
							</c:choose>
						
							<label>Quantity</label>
							<div class="row mt-2">
								<div class="col-md-3">
									<div class="input-group text-center mb-3">
										<input class="input-group-text" type="button" value="-"
											onclick="decreaseQuantity()" /> <input
											class="form-control text-center" type="text" id="quantity"
											value="1" /> <input class="input-group-text" type="button"
											value="+" onclick="increaseQuantity()" />
									</div>
								</div>
								<div class="col-md-7">
									<c:choose>
										<c:when test="${product.quantity > 0}">
											<a class="btn btn-dark  me-3 float-start"
												href="checkout.html">Add to cart<i
												class="fa fa-shopping-cart"></i>
											</a> &nbsp; &nbsp; 
								</c:when>
									</c:choose>
									<a class="btn btn-light me-3 float-start" href="">Add to
										wishlist <i class="fa fa-heart"></i>
									</a>
								</div>
							</div>
								</c:when>
								<c:otherwise>
							<label style="font-size: 24px;"
									class="float-end badge bg-danger">Temporary business suspension</label>
							</c:otherwise>
								</c:choose>
						</div>
						<hr>
						<div class="row">
							<div class="col-md-4">
								<c:choose>
									<c:when test="${userSession.id != null}">
										<button type="button" class="btn btn-link"
											data-bs-toggle="modal" data-bs-target="#exampleModal">Rating
											product</button>
										<a
											href="${pageContext.request.contextPath}/add-review/${product.id}/review"
											class="btn btn-link">Comment product</a>
									</c:when>
								</c:choose>
							</div>
							<div class="col-md-6">
								<c:forEach var="review" items="${listReview}">
							
									<div class="user-review">
										<label>${review.name}</label>
										<c:choose>
											<c:when test="${review.user_id == userSession.id}">
												<a
													href="${pageContext.request.contextPath}/edit-review/${review.id}/userreview">edit</a>
											</c:when>
										</c:choose>
										<br>

										<c:forEach var="stars" begin="1" end="${review.stars_rated}">
											<i class="fa fa-star checked"></i>
										</c:forEach>
										<c:forEach var="stars" begin="${review.stars_rated + 1}"
											end="5">
											<i class="fa fa-star"></i>
										</c:forEach>
										<fmt:parseDate  value="${review.created_at}" pattern="yyyy-MM-dd" type="date" var="myDate" />
										<fmt:formatDate value="${myDate}" var="newParsedDate" type="date" pattern="dd/MMM/yyyy" />
            							<small>Reviewed on ${newParsedDate}</small>
            							<p>${review.user_review}</p>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

</body>
</html>