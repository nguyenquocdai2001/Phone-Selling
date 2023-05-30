
<!-- Navbar -->
<nav class="navbar navbar-expand-lg " color-on-scroll="500">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/"> Dashboard </a>
		<button href="" class="navbar-toggler navbar-toggler-right"
			type="button" data-toggle="collapse" aria-controls="navigation-index"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-bar burger-lines"></span> <span
				class="navbar-toggler-bar burger-lines"></span> <span
				class="navbar-toggler-bar burger-lines"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-end"
			id="navigation">
			<ul class="nav navbar-nav mr-auto">
				<li class="nav-item"><a href="${pageContext.request.contextPath}/home" class="nav-link"
					data-toggle="dropdown"> <i class="nc-icon nc-palette"></i> <span
						class="d-lg-none">Dashboard</span>
				</a></li>
				<li class="dropdown nav-item"><a href="#"
					class="dropdown-toggle nav-link" data-toggle="dropdown"> <i
						class="nc-icon nc-planet"></i> <span class="notification">5</span>
						<span class="d-lg-none">Notification</span>
				</a>
					<ul class="dropdown-menu">
						<a class="dropdown-item" href="#">Notification 1</a>
						<a class="dropdown-item" href="#">Notification 2</a>
						<a class="dropdown-item" href="#">Notification 3</a>
						<a class="dropdown-item" href="#">Notification 4</a>
						<a class="dropdown-item" href="#">Another notification</a>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nc-icon nc-zoom-split"></i> <span class="d-lg-block">&nbsp;Search</span>
				</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<c:set var="userSession" value="${userSession}" />

				<c:if test="${empty userSession}">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/login"> <span
							class="no-icon">Login</span>
					</a></li>
				</c:if>

				<c:set var="userSession" value="${userSession}" />
				<c:if test="${not empty userSession}">
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/home"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <span
							class="no-icon"><c:out value="${helloUser}" /></span>
					</a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/editProfile/${userSession.id}">Profile</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/changePassword/${userSession.id}">Change password</a> 
		
							<div class="divider"></div>
							<a class="dropdown-item" href="#">Separated link</a>
						</div></li>
				</c:if>

				<c:set var="userSession" value="${userSession}" />
				<c:if test="${not empty userSession}">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/logout"> <span
							class="no-icon">Log out</span>
					</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>
<!-- End Navbar -->