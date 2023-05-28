<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Change password</title>
<%@ include file="/common/admin/link.jsp"%>
</head>
<body>
	<div class="wrapper">
		<%@ include file="/common/admin/sidebar.jsp"%>
		<div class="main-panel">
			<%@ include file="/common/admin/header.jsp"%>
			<div class="container" style="margin-top: 10px; margin-bottom: 30px;">
				<div class="row bg-light"
					style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
					<div class="col-sm-12">
						<header class="text-center py-3 rounded ">
							<h1 class="display-5 font-weight-bold text-secondary text-center">Change
								Password</h1>
						</header>
						<form action="${pageContext.request.contextPath}/checkChangePassword" method="post">
							<div class="form-group">
								<label class="text-dark ">Email</label> <input type="text"
									class="form-control" name="email" placeholder="Enter Email"
									id="email" value="">
							</div>

							<div class="form-group">
								<label class="text-dark ">Old password</label> <input
									type="password" class="form-control" name="OldPassword"
									placeholder="Enter Old Password">
							</div>
	

							<div class="form-group">
								<label class="text-dark ">New password</label> <input
									type="password" class="form-control" name="NewPassword"
									placeholder="Enter New Password">
							</div>

							<div class="form-group">
								<label class="text-dark ">Confirm new password</label> <input
									type="password" class="form-control" name="ConfirmNewPassword"
									placeholder="Enter Confirm New Password">
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
								<button type="submit" class="btn btn-success">Save</button>
								<button type="reset" class="btn btn-secondary">Cancel</button>
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

