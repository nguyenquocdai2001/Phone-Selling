<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
    <head>
        <meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>History</title>
        <%@ include file="/common/public/info/linkinfo.jsp"%>
       
    </head>
<body>
	
	<%@ include file="/common/public/info/headerinfo.jsp"%>
        
        <div class="container">
            <header class="text-center bg-light text-dark py-3 rounded mt-3 mb-3">
				<h1 class="display-5 font-weight-bold ">HISTORY</h1>
			</header>

            <table class="table table-striped table-borderless table-hover text-center">
                <thead>
                    <tr class="text-dark ">
                    	<th class="text-center ">User ID</th>
                    	<th class="text-center ">Full name</th>
                        <th class="text-center ">Email</th>
                        <th class="text-center ">Address</th>
                        <th class="text-center ">Phone</th>
                        <th class="text-center ">Status</th>
						<th class="text-center ">Total Price</th>
                        <th class="text-center "></th>
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
	                        <td><fmt:formatNumber value="${order.totalPrice}"
											type="currency" currencyCode="VND" /></td>	                        
	                        <td><a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/detailHistoryUser/${order.id}">Details</a> 
                    </tr>
                    
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
