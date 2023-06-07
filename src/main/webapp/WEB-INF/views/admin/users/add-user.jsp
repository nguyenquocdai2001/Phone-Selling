<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Register Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        
         <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="/common/public/info/linkinfo.jsp"%>
</head>
<body>
	<div class="se-pre-con"></div>
	<%@ include file="/common/public/info/headerinfo.jsp"%>

			<div class="container" style="margin-top: 30px; margin-bottom: 30px;">
				<div class="row bg-light"
					style="border: 1px darkgrey solid; border-radius: 10px; margin-left: 40px;">
					<div class="col-sm-12">
						<header class="text-center py-3 rounded ">
							<h1 class="display-5 font-weight-bold text-secondary text-center">Sign
								up</h1>
						</header>
						<form action="${pageContext.request.contextPath}/checkRegister" method="POST">
							<div class="row register-form">
								<div class="col-md-5">
									<div class="form-group">
										<label class="text-dark ">Email</label> 
										
											<input type="text"
												class="form-control <c:if test='${empty check.email}'> ${valid} </c:if>" name="email" placeholder="Enter Email"
												id="email" value="${check.email}" >
										
									</div>
									<div class="form-group">
										<label class="text-dark ">Password</label>
										
										 <input
											type="password" class="form-control <c:if test='${empty check.password}'> ${valid} </c:if>" name="password"
											placeholder="Enter Password" value="${check.password}"  ">
											
									</div>
									<div class="form-group">
										<label class="text-dark ">Confirm password</label> 
										
										<input
											type="password" class="form-control <c:if test='${empty check.password}'> ${valid} </c:if>" name="confirmPassword"
											placeholder="Enter Confirm password" id="confirmPassword"
											value="${check.password}" >
											
									</div>
								</div>

								<div class="col-md-5">
									<div class="form-group">
										<label class="text-dark ">Fullname</label> 
										
										<input type="text"
											class="form-control <c:if test='${empty check.name}'> ${valid} </c:if>" name="name" placeholder="Enter Fullname"
											id="name" value="${check.name}" >
											
									</div>

									<div class="form-group">
										<label class="text-dark ">Phone</label> 
										
										<input type="number"
											class="form-control <c:if test='${empty check.phone}'> ${valid} </c:if>" name="phone" placeholder="Enter Phone"
											id="phone" value="${check.phone}" >
											
									</div>
									<div class="form-group">
										<label class="text-dark ">Address</label> 
										
										<input type="text"
											class="form-control <c:if test='${empty check.address}'> ${valid} </c:if>" name="address"
											placeholder="Enter Address" id="address" value="${check.address}" >
											
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
								<button type="submit" class="btn btn-success">Create</button>
								<button type="reset" class="btn btn-secondary">Cancel</button>
								<p class="mt-3">
									Already have an account? <a href="./login">Sign in</a>
								</p>
							</div>
						</form>
					</div>
				</div>
			</div>
</body>
</html>

