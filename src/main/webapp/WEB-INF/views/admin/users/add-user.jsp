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

<%@ include file="/common/public/info/linkinfo.jsp"%>
</head>
<body>
	<div class="se-pre-con"></div>
	<%@ include file="/common/public/info/headerinfo.jsp"%>

	<div class="container " style="margin-top: 30px; margin-bottom: 30px;">
		<div class="row bg-light"
			style="border: 1px darkgrey solid; border-radius: 10px; padding-right: 80px; width: 50%; margin: 0 auto;">
			<div class="col-sm-12">
				<header class="text-center py-3 rounded ">
					<h1 class="display-5 font-weight-bold text-secondary text-center">Sign
						up</h1>
				</header>
				<form action="${pageContext.request.contextPath}/checkRegister"
					method="POST">
					<div class="row register-form">
						<div class="col-md-5">
							<div class="form-group">
								<label class="text-dark ">Email</label> <input type="text"
									class="form-control <c:if test='${empty check.email}'> ${valid} </c:if>"
									name="email" placeholder="Enter Email" id="email"
									value="${check.email}">

							</div>
							<div class="form-group">
								<label class="text-dark ">Password</label> <input
									type="password"
									class="form-control <c:if test='${empty check.password}'> ${valid} </c:if>"
									name="password" placeholder="Enter Password"
									value="${check.password}">

							</div>
							<div class="form-group">
								<label class="text-dark ">Confirm password</label> <input
									type="password"
									class="form-control <c:if test='${empty check.password}'> ${valid} </c:if>"
									name="confirmPassword" placeholder="Enter Confirm password"
									id="confirmPassword" value="${check.password}">
							</div>



							<div class="form-group">
								<label class="text-dark ">Gender</label>
								<div class="form-group">
									<select name="gender"
										class="form-control <c:if test='${empty check.gender}'> ${valid} </c:if>">
										<option value="${check.gender}">${check.gender}</option>
										<option value="Male">Male</option>
										<option value="Female">Female</option>
									</select>
								</div>
							</div>
						</div>
						
						<div class="col-md-5">
							<div class="form-group">
								<label class="text-dark ">Fullname</label> <input type="text"
									class="form-control <c:if test='${empty check.name}'> ${valid} </c:if>"
									name="name" placeholder="Enter Fullname" id="name"
									value="${check.name}">

							</div>

							<div class="form-group">
								<label class="text-dark ">Phone</label> <input type="number"
									class="form-control <c:if test='${empty check.phone}'> ${valid} </c:if>"
									name="phone" placeholder="Enter Phone" id="phone"
									value="${check.phone}">

							</div>
							<div class="form-group">
								<label class="text-dark ">Address</label> <input type="text"
									class="form-control <c:if test='${empty check.address}'> ${valid} </c:if>"
									name="address" placeholder="Enter Address" id="address"
									value="${check.address}">
							</div>
							
							<div class="form-group">
								<label class="text-dark ">Titles of address</label>
								<div class="form-group">
									<select name="titlesOfAddress"
										class="form-control <c:if test='${empty check.titlesOfAddress}'> ${valid} </c:if>">
										<option value="${check.titlesOfAddress}">${check.titlesOfAddress}</option>
										<option value="Mr">Mr</option>
										<option value="Mrs">Mrs</option>
										<option value="Miss">Miss</option>
										<option value="Sir">Sir</option>
										<option value="Madam">Madam</option>
										<option value="Lady">Lady</option>
									</select>
								</div>
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

