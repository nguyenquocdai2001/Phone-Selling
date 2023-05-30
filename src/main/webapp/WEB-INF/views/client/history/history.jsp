<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
    <head>
        <meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>History</title>
        <%@ include file="/common/public/info/linkinfo.jsp"%>
        
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
    </head>
<body>
	
	<%@ include file="/common/public/info/headerinfo.jsp"%>
        
        <div class="container">
            <header class="text-center bg-light text-dark py-3 rounded mt-3 mb-3">
				<h1 class="display-5 font-weight-bold ">HISTORY</h1>
			</header>

            <table class="table table-striped table-borderless table-hover text-center">
                <thead>
                    <tr class="text-dark bg-dark">
                    	<th class="text-center text-light">User ID</th>
                    	<th class="text-center text-light">Full name</th>
                        <th class="text-center text-light">Email</th>
                        <th class="text-center text-light">Address</th>
                        <th class="text-center text-light">Phone</th>
                        <th class="text-center text-light">Status</th>
						<th class="text-center text-light">Total Price</th>
                        <th class="text-center text-light">Action</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="order" items="${OrderHistory}" varStatus="loop">
                    <tr>
                 
	                    	<td>${order.userID}</td>
	                        <td>${order.name}</td>
	                        <td>${order.email}</td>
	                        <td>${order.address}</td>
	                        <td>${order.phone}</td>
	                        <td>${order.status}</td>
	                        <td>${order.totalPrice}</td>
	                        <td><a class="btn btn-dark btn-sm" href="${pageContext.request.contextPath}/detailHistoryUser/${order.id}">Details</a> 
                    </tr>
                    
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
