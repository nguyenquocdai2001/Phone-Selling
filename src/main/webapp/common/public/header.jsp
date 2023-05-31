<header id="home">
      <nav >
        <ul>
          <li class="primary-nav">
            <img src="<c:url value='/template/public/Images/img-DS.png'/>"  alt="logo" />

            <a href="${pageContext.request.contextPath}/clienthome">PHONE SELLING</a>

            <a href="./clienthome">REACH2</a>

          </li>
          	   
          <li class="secondary-nav">
            <a href="${pageContext.request.contextPath}/views">
              <i class="fas fa-shopping-cart"></i> CART
            </a>

          </li>
          <li class="secondary-nav">
            <a href=""></a>
          </li>   

          <c:if test="${empty userSession}">
	          <li class="secondary-nav">
		         <a href="${pageContext.request.contextPath}/login">LOGIN</a>
		      </li>
		      <li class="secondary-nav">
		         <a href="${pageContext.request.contextPath}/addUser">SIGN UP</a>
		      </li>
		  </c:if>
	      
          <c:set var="userSession" value="${userSession}" />
		  <c:if test="${not empty userSession}">
		  	  <li class="secondary-nav"><a class="nav-link"
					href="${pageContext.request.contextPath}/logout"> <span
					class="no-icon">LOG OUT</span></a>
			  </li>
	          <li class="secondary-nav"><a href="${pageContext.request.contextPath}/changePasswordUser/${userSession.id}">CHANGE PASSWORD</a></li>
	          <li class="secondary-nav"><a href="${pageContext.request.contextPath}/historyUser">HISTORY</a></li>
	          <li class="secondary-nav"><a href="${pageContext.request.contextPath}/editProfileUser/${userSession.id}"><c:out value="${helloUser}" /></a></li>
          </c:if>
        </ul>
      </nav>
        <video autoplay muted loop  width="100%;" height="100%" style="object-fit: fill;">
        <source src="<c:url value='/template/public/Images/large_2x.mp4'/>" type="video/mp4">
      
    </video>
      <div class="pxtext" >
        <span class="border">
         REACHING 2 Technology
        </span>
        <p><a href="${pageContext.request.contextPath}/allProduct">Shop Now</a></p>
      </div>
      
    </header>