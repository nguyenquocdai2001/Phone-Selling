  <div class="sidebar" data-image="<c:url value="/template/admin/img/sidebar-5.jpg "/>">
            <!--
        Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

        Tip 2: you can also add an image using data-image tag
    -->
            <div class="sidebar-wrapper">
                <div class="logo">
                    <a href="${pageContext.request.contextPath}/home" class="simple-text">
                        PHONE SELLING
                    </a>
                </div>
                <ul class="nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/home">
                            <i class="nc-icon nc-chart-pie-35"></i>
                            <p>Dashboard</p>
                        </a>
                    </li>
                         
                    
                    <c:set var="userRole" value="${userRole}" />

					<c:if test="${userRole == 'admin'}">
	                    <li>
	                        <a class="nav-link" href="${pageContext.request.contextPath}/addCategory">
	                            <i class="nc-icon nc-paper-2"></i>
	                            <p>Add Category</p>
	                        </a>
	                    </li>
                    </c:if>                   
                    <c:set var="userRole" value="${userRole}"/>

					<c:if test="${userRole == 'admin'}">
	                    <li>
	                        <a class="nav-link" href="${pageContext.request.contextPath}/productandcategory">
	                            <i class="nc-icon nc-notes"></i>
	                            <p>Products List</p>
	                        </a>
	                    </li>
                    </c:if>
                    <c:set var="userRole" value="${userRole}"/>

					<c:if test="${userRole == 'admin'}">
	               <li>
                        <a class="nav-link" href="${pageContext.request.contextPath}/product">
                            <i class="nc-icon nc-mobile"></i>
                            <p>Add Product</p>
						</a>
					</li>
                    </c:if>
                              
                </ul>
            </div>
        </div>