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
	<section>
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
													</c:choose>
												"
					alt="" />
			</div>
			<div class="col2">
				<h1>${product.name}</h1>			
				<p>${product.description}</p>			
				<h4>
					Original Price : <s>${product.og_price}</s> 				
				</h4>
				<h4>Selling Price : ${product.selling_price}</h4>			
				<input type="button" value="-" onclick="decreaseQuantity()" /> 
				<input style=" width: 60px; height: 25px; text-align: center;" type="text" id="quantity" value="1" /> 
				<input type="button" value="+" onclick="increaseQuantity()" /> </span> <br /> <br /> 
				<a class="button-cart" href="checkout.html">Add to cart</a> &nbsp;
				&nbsp; <a class="wishlist" href="">Add to wishlist</a>
			</div>
		</div>
	</section>
	<section class="video">
		<h1 style="text-align: center">Video</h1>
		<video src="../Images/videoplayback.mp4" type="video/mp4" controls
			controlsList="nodownload" poster="../Images/nike-zoom.gif"
			style="display: block; margin: 30px auto; width: 70%"></video>
	</section>

</body>
</html>