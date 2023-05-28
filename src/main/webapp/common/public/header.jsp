<header id="home">
      <nav >
        <ul>
          <li class="primary-nav">
            <img src="<c:url value='/template/public/Images/img-DS.png'/>"  alt="logo" />
            <a href="./clienthome">REACH2</a>
          </li>
          	<c:choose>
				<c:when test="${userSession.id == null}">
			   <li class="secondary-nav">
            <a href="${pageContext.request.contextPath}/login">LOGIN</a>
          </li>
		</c:when>
	</c:choose>    
          <li class="secondary-nav">
            <a href="${pageContext.request.contextPath}/views">
              <i class="fas fa-shopping-cart"></i> CART
            </a>
          </li>   
          <li class="secondary-nav">
            <a href="HTML/about-us.html">ABOUT REACH2</a>
          </li>
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