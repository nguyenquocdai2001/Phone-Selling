<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
    <head>
        <meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Detail History User</title>
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
				<h1 class="display-5 font-weight-bold ">DETAIL HISTORY</h1>
			</header>

            <table class="table table-striped table-borderless table-hover text-center">
                <thead>
                    <tr class="text-dark bg-dark">
                    	<th class="text-center text-light">ID</th>
                    	<th class="text-center text-light">Name product</th>
                    	<th class="text-center text-light">Quantity</th>
                        <th class="text-center text-light">Price</th>
                        <th class="text-center text-light">Created at</th>
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
