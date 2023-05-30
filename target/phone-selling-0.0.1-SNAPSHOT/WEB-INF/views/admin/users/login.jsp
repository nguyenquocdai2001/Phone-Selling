<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<%@ include file="/common/admin/link.jsp"%>
</head>
<body>
	<div class="wrapper">
		<%@ include file="/common/admin/sidebar.jsp"%>
		<div class="main-panel">
			<%@ include file="/common/admin/header.jsp"%>

			<div class="container" style="margin-top: 50px; margin-bottom: 50px;">
				<div class="row bg-light"
					style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
					<div class="col-sm-12">
						<header class="text-center py-3 rounded ">
							<h1 class="display-5 font-weight-bold text-secondary text-center">Sign
								in</h1>
						</header>
						<form action="${pageContext.request.contextPath}/checklogin" method="post">
							<div class="form-group">
								<label class="text-dark">Email</label> <input type="text"
									class="form-control" name="email" placeholder="Enter email">
							</div>
							<div class="form-group">
								<label class="text-dark">Password </label> <input
									type="password" class="form-control" name="password"
									placeholder="Enter password">
							</div>

							<c:if test='${not empty message}'>
								<div class="alert alert-${message.type} alert-dismissible show"
									role="alert">
									${message.content}
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
							</c:if>

							<button type="submit" class="btn btn-primary">Login</button>
							<a href="/login"><button type="reset"
									class="btn btn-secondary">Cancel</button></a>
							<p class="mt-3">
								Don't have an account? <a href="addUser">Sign up</a>
							</p>
						</form>
					</div>
				</div>
			</div>

			<%@ include file="/common/admin/footer.jsp"%>
		</div>
	</div>
</body>
<%@ include file="/common/admin/script.jsp"%>
</html>
