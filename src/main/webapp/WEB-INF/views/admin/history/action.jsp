
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<body>

	<c:forEach var="order" items="${Income}" varStatus="loop">
		<tr>
			<td>${order.created_at}</td>
			<td><fmt:formatNumber value="${order.price}" type="number" /></td>
		</tr>
	</c:forEach>

	<hr>
	<c:set var="sum" value="0" />
	<c:forEach var="item" items="${Income}">
		<c:set var="sum" value="${sum + item.price}" />
	</c:forEach>
	Total Income:
	<fmt:formatNumber value="${sum}" type="number" />
	
</body>
