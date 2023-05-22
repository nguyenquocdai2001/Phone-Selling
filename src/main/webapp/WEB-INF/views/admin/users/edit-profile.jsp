<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
<%@ include file="/common/admin/link.jsp"%>
</head>
<body>
	<div class="wrapper">
		<%@ include file="/common/admin/sidebar.jsp"%>
		<div class="main-panel">
			<%@ include file="/common/admin/header.jsp"%>

			<div class="container" style="margin-top: 30px; margin-bottom: 30px;">
				<div class="row bg-light"
					style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
					<div class="col-sm-12">
						<header class="text-center py-3 rounded ">
							<h1 class="display-5 font-weight-bold text-secondary text-center">Profile</h1>
						</header>
						<form action="${pageContext.request.contextPath}/editProfile" method="POST">
							<div class="row register-form">
								<div class="col-md-6">
									<div class="form-group">
										<label class="text-dark ">Email</label> <input type="text"
											class="form-control" name="email" placeholder=""
											id="email" value="${users.email}" readonly>
									</div>
									<div class="form-group">
										<label class="text-dark ">Password</label> <input
											type="password" class="form-control" name="password"
											 value="${users.password}" readonly>
									</div>
									<div class="form-group">
										<label class="text-dark ">Confirm password</label> <input
											type="password" class="form-control" name="confirmPassword"
											placeholder="" id="confirmPassword"
											value="${users.password}" readonly>
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label class="text-dark ">Fullname</label> <input type="text"
											class="form-control" name="name" placeholder=""
											id="name" value="${users.name}" >
									</div>

									<div class="form-group">
										<label class="text-dark ">Phone</label> <input type="number"
											class="form-control" name="phone" placeholder=""
											id="phone" value="${users.phone}" >
									</div>
									<div class="form-group">
										<label class="text-dark ">Address</label> <input type="text"
											class="form-control" name="address"
											placeholder="" id="address" value="${users.address}" >
									</div>
								</div>

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

							<div class="form-group">
								<button type="submit" class="btn btn-primary">Save</button>
								<a class="btn btn-secondary" href="${pageContext.request.contextPath}/home">Back</a>
							</div>
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

