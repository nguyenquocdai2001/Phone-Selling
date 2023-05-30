<header>
	<nav>
		<ul>
			<li class="primary-nav"><img style="margin-top: auto !important"
				src="<c:url value='/template/public/Images/img-DS.png'/>" alt="logo" />
				<a href="${pageContext.request.contextPath}/clienthome">PHONE SELLING</a></li>
				
			<c:if test="${empty userSession}">
	          <li class="secondary-nav">
		         <a href="${pageContext.request.contextPath}/login">LOGIN</a>
		      </li>
		      <li class="secondary-nav">
		         <a href="${pageContext.request.contextPath}/addUser">SIGN UP</a>
		      </li>
		  </c:if>

			<li class="secondary-nav"><a
				href="${pageContext.request.contextPath}/views"> <i
					class="fas fa-shopping-cart"></i> CART
			</a></li>

			<c:set var="userSession" value="${userSession}" />
			<c:if test="${not empty userSession}">
				<li class="secondary-nav"><a class="nav-link"
						href="${pageContext.request.contextPath}/logout">LOG OUT</a>
				 </li>
				<li class="secondary-nav"><a
					href="${pageContext.request.contextPath}/changePassword/${userSession.id}">CHANGE
						PASSWORD</a></li>
						<li class="secondary-nav"><a href="${pageContext.request.contextPath}/historyUser">HISTORY</a></li>
				<li class="secondary-nav"><a
					href="${pageContext.request.contextPath}/editProfile/${userSession.id}"><c:out
							value="${helloUser}" /></a></li>
				<li class="secondary-nav"><a href=""></a></li>
			</c:if>

		</ul>
	</nav>
</header>