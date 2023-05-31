<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
    <head>
        <meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Detail History User</title>
        <%@ include file="/common/public/info/linkinfo.jsp"%>

    </head>
<body>
	
	<%@ include file="/common/public/info/headerinfo.jsp"%>
	
        <div class="container">
            <header class="text-center bg-light text-dark py-3 rounded mt-3 mb-3">
				<h1 class="display-5 font-weight-bold ">DETAIL HISTORY</h1>
			</header>

            <table class="table table-striped table-borderless table-hover text-center">
                <thead>
                    <tr class="text-dark ">
                    	<th class="text-center ">ID</th>
                    	<th class="text-center ">Name product</th>
                    	<th class="text-center ">Quantity</th>
                        <th class="text-center ">Price</th>
                        <th class="text-center ">Created at</th>
                    </tr>
                </thead>
                <tbody>
                	
	                    	<c:forEach var="ItemOrder" items="${AllOrderItemHistory}" varStatus="loop">
	                    		<tr>
	                    			
				                    	<td>${ItemOrder.id}</td>
				                        <td>${ItemOrder.nameProduct}</td>
				                        <td>${ItemOrder.orderItemQty}</td>
				                        <td>${ItemOrder.price}</td>
				                        <td>${ItemOrder.created_at}</td>

				                </tr>
		                     </c:forEach>

                </tbody>
            </table>
        </div>
    </body>
</html>
