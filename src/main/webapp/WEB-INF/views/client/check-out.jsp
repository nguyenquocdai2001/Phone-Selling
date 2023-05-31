<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />


<title>Check Out</title>
<%@ include file="/common/public/info/linkinfo.jsp"%>
</head>
<body>
	<div class="se-pre-con"></div>
	<%@ include file="/common/public/info/headerinfo.jsp"%>

	<div class="container" style="margin-top: 10px;">
		<div class="row bg-light"
			style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding-right: 40px;">
			<div class="col-sm-12">
				<header class="text-center py-3 rounded ">
					<h1 class="display-5 font-weight-bold text-secondary text-center">Information</h1>
				</header>
				<form action="${pageContext.request.contextPath}/confirmCheckout"
					method="post">

					<div class="form-group">
						<label class="text-dark ">Email</label> <input type="text"
							class="form-control" name="email" id="email"
							value="<c:out value="${users.email}" />" readonly>
					</div>

					<div class="form-group">
						<label class="text-dark ">Fullname</label> <input type="text"
							class="form-control" name="name"
							value="<c:out value="${users.name}" />" readonly>
					</div>

					<div class="form-group">
						<label class="text-dark ">Phone number</label> <input type="text"
							class="form-control" name="phone" id="phone"
							value="<c:out value="${users.phone}" />">
					</div>

					<div class="form-group">
						<label class="text-dark ">Address</label> <input type="text"
							class="form-control" name="address" id="address"
							value="<c:out value="${users.address}" />">
					</div>

					<div class="form-group">
						<label class="text-dark">Total price</label> <input type="text"
							class="form-control" name="totalPrice" id="totalPrice"
							value="<c:out value="${TOTAL_PRICE}" />" readonly>
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
					<c:if test='${TOTAL_PRICE > 0}'>
						<button class="btn btn-success" type="submit">Save</button>
					</c:if>
					<a class="btn btn-secondary"
						href="${pageContext.request.contextPath}/views">Back</a>
				</form>
			</div>

		</div>
	</div>
</body>
<c:if test="${not empty status}">
	<script>
		swal("${status}");
	</script>
</c:if>
</html>

