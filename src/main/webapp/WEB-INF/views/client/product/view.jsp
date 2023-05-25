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
						<input type="hidden" name="user_id" value="${userSession.id}" >
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
												<input type="radio" value="${i}" name="stars_rated"
													checked id="rating${i}">
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
												<input type="radio" value="${i}" name="stars_rated"
													checked id="rating${i}">
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
						<div class="col1">
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
						<div class="col1 " style="text-align: left">
							<h1>${product.name}
								<label style="font-size: 16px;"
									class="float-start badge bg-danger">${product.trending == '1' ? 'Trending' : ''}</label>
							</h1>
							<hr>
							<p>${product.description}</p>
							<hr>
							<lable class="me-3"> Original Price : <s>${product.og_price}
								$</s> </lable>
							<lable class="fw-bold mt-3">
							<h5>Selling Price : ${product.selling_price} $</h5>
							</lable>
							<hr>
							<c:set var="ratenum" value="${average}" />
	
							<c:choose>
								<c:when test="${product.quantity > 0}">
									<label class="badge bg-success">In stock</label>
								</c:when>
								<c:otherwise>
									<label class="badge bg-danger">Run out</label>
								</c:otherwise>
							</c:choose>

							<label>Quantity</label>
							<div class="input-group text-center mb-3" style="width: 30%">
								<input class="input-group-text" type="button" value="-"
									onclick="decreaseQuantity()" style="width: 40px" /> <input
									class="form-control text-center"
									style="width: 30px !important type=" text" id="quantity"
									value="1" /> <input type="button" value="+"
									onclick="increaseQuantity()" style="width: 40px" /> </span> <br /> <br />
							</div>

							<a class="button-cart" href="${pageContext.request.contextPath}/add/${product.id}">Add to cart</a>
							&nbsp; &nbsp; <a class="wishlist" href="">Add to wishlist</a>

						</div>

					</div>
				</div>

								
			</div>

		</div>
	</section>
	<c:choose>
		<c:when test="${userSession.id != null}">
		 <button type="button"  class="btn btn-link" data-bs-toggle="modal" data-bs-target="#exampleModal">Rating product</button>
		</c:when>
	</c:choose>
  
</body>
</html>