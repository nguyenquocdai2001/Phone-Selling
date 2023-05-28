<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<section>
		<div class="row" style="width: 80%; margin: 0 auto;">
			<div class="col">
				<div class="overlay-image">
					<a href="${pageContext.request.contextPath}/phone"> <img height="400px !important"
						class="image"
						src="<c:url value='/template/public/Images/iphone.jpg'/>"
						alt="Alt text" />
						<div class="normal">
							<div class="text">PHONE</div>
						</div>
						<div class="hover">
							<img height="400px !important" class="image"
								src="<c:url value='/template/public/Images/samsung-using.webp'/>"
								alt="Alt text hover" />
							<div class="text">PHONE</div>
						</div>
					</a>
				</div>
			</div>
			<div class="col">
				<div class="overlay-image">
					<a href="${pageContext.request.contextPath}/phone"> <img class="image"
						height="400px !important"
						src="<c:url value='/template/public/Images/apple-ear.jpg'/>"
						alt="Alt text" />
						<div class="normal">
							<div class="text">ACCESSORIES</div>
						</div>
						<div class="hover">
							<img class="image" height="400px !important"
								src="<c:url value='/template/public/Images/galaxy-watch.jpg'/>"
								alt="Alt text hover" />
							<div class="text">ACCESSORIES</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</section>
	<section class="preview">
		<h2 class="title-context" align="center">Category</h2>
		<div class="row" style="width: 83%; margin: 0 auto;">
			<c:forEach var="cate" items="${listCate}">
				<figure class="item">
					<a href="<c:url value="info/${product.id}"/>"> <img
						src="${pageContext.request.contextPath}/template/admin/upload/
													<c:choose>
														<c:when test="${cate.image ne null}">
															${cate.image}		
														</c:when>
														<c:otherwise>
															springmvc.png
														</c:otherwise>
													</c:choose>
												"
						alt="" width="180px" height="180px" />
						<figcaption>${cate.name}</figcaption>
					</a>
				</figure>
			</c:forEach>
	
		</div>
	</section>
	<section class="preview">
		<h2 class="title-context"align="center">Trending Product</h2>
		<div class="row" style="width: 83%; margin: 0 auto;">
			<c:forEach var="product" items="${listProduct}">
				<figure class="item">
					<a  href="<c:url value="./info/${product.id}"/>"> <img
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
						alt="" width="235px"  height="235px"/>
						<figcaption>${product.name}</figcaption>
					</a>
				</figure>
			</c:forEach>
	
		</div>
	</section>

	<h3 style="text-align: center">
		<a href="./HTML/survey.html">Click here to take a quick survey</a>
	</h3>
</body>
