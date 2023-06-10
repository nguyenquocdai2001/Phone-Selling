<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orders</title>
<%@ include file="/common/admin/link.jsp"%>
</head>
<body>

	<div class="wrapper">
		<%@ include file="/common/admin/sidebar.jsp"%>
		<div class="main-panel">
			<%@ include file="/common/admin/header.jsp"%>

			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card card-plain table-plain-bg">
								<div class="card-header">
									<h4 class="card-title">ORDERS</h4>
									<p class="card-category">List of client's orders</p>
								</div>
								<div class="card-body table-full-width table-responsive">
									<table
										class="table table-striped table-borderless table-hover text-center"
										id="orders">
										<thead>
											<tr class="text-dark">
												<th class="text-center text-dark">User ID</th>
												<th class="text-center text-dark">Full name</th>
												<th class="text-center text-dark">Email</th>
												<th class="text-center text-dark">Address</th>
												<th class="text-center text-dark">Phone</th>
												<th class="text-center text-dark">Status</th>
												<th class="text-center text-dark">Total Price</th>
												<th class="text-center text-dark">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="order" items="${OrderHistory}"
												varStatus="loop">
												<tr>
													<td>${order.userID}</td>
													<td>${order.name}</td>
													<td>${order.email}</td>
													<td>${order.address}</td>
													<td>${order.phone}</td>
													<td>${order.status  == '0' ? 'UnPaid' : 'Paid' }</td>
													<td><fmt:formatNumber value="${order.totalPrice}"
															type="number" /></td>
													<td><a class="btn btn-dark btn-sm"
														href="${pageContext.request.contextPath}/detailOrderUserByAdmin/${order.id}">Details</a>
														<a class="btn btn-dark btn-sm"
														href="${pageContext.request.contextPath}/UpdateStatusOrder/${order.id}">Paid</a>
													</td>
												</tr>

											</c:forEach>
										</tbody>
									</table>
									<hr>
									<c:set var="sum" value="0" />
									<c:forEach var="item" items="${OrderHistory}">
										<c:choose>
											<c:when test="${item.status == 1}">
												<c:set var="sum" value="${sum + item.totalPrice}" />
											</c:when>
										</c:choose>
									</c:forEach>
									Total Income:
									<fmt:formatNumber value="${sum}" type="number" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="card card-plain table-plain-bg">
								<div class="card-header">
									<h4 class="card-title">Monthly Income</h4>
									<p class="card-category">List of monthly income</p>
								</div>
								<form id="searchForm"
									action="${pageContext.request.contextPath}/searchIncome"
									method="POST">
									<div class="card-body table-full-width table-responsive">
										<input  type="date" name="startdate"> <input
											type="date" name="enddate">
										<button class="rounded" type="button" onclick="searchIncome()">
											<i class="fa fa-search"></i>Search
										</button>
										<div>
											<canvas id="myChart"> </canvas>
										</div>
										<table
											class="table table-striped table-borderless table-hover "
											id="Income">
											<thead>
												<tr class="text-dark">
													<th class="text-center text-dark">Date time</th>
													<th class="text-center text-dark">Total income</th>

												</tr>
											</thead>
											<tbody>
												<c:set var="sum" value="0" />
												<c:forEach var="order" items="${Income}" varStatus="loop">
													<tr>
														<td>${order.created_at}</td>
														<td><fmt:formatNumber value="${order.price}"
																type="number" /></td>
													</tr>
													<c:set var="sum" value="${sum + order.price}" />
												</c:forEach>																									
											</tbody>
										</table>
									</div>
								</form>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<%@ include file="/common/admin/footer.jsp"%>
	</div>

</body>

<%@ include file="/common/admin/script.jsp"%>
<c:if test="${not empty status}">
	<script>
		swal("${status}");
	</script>
</c:if>

<script>
  const ctx = document.getElementById('myChart');
  var chart = null; 
    function searchIncome() {
        var startdate = document.getElementsByName("startdate")[0].value;
        var enddate = document.getElementsByName("enddate")[0].value;

        fetch("${pageContext.request.contextPath}/searchIncome", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: "startdate=" + startdate + "&enddate=" + enddate
        })
        .then(function(response) {
            return response.text();
        })
        .then(function(data) {
            var incomeTable = document.getElementById("Income");
            incomeTable.getElementsByTagName("tbody")[0].innerHTML = data;          
            updateChart(); // Cập nhật biểu đồ sau khi nhận kết quả tìm kiếm
        })
        .catch(function(error) {
            console.log(error);
        });
    }

   // Khởi tạo biến chart là null

    function updateChart() {
      if (chart) {
        chart.destroy();  // Huỷ bỏ biểu đồ hiện tại nếu tồn tại
      }

      var incomeLabels = [];
      var incomeData = [];

      // Lấy dữ liệu từ bảng và chuyển đổi thành các mảng
      $("#Income tbody tr").each(function() {
        var label = $(this).find("td:first-child").text();
        var data = $(this).find("td:last-child").text();

        incomeLabels.push(label);
        incomeData.push(parseFloat(data));
      });

      // Tạo biểu đồ mới trên canvas
      chart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: incomeLabels,
          datasets: [{
            label: 'Total income',
            data: incomeData,
            borderWidth: 1
          }]
        },
        options: {
          scales: {
            y: {
              beginAtZero: true
            }
          }
        }
      });
    }
 // A $( document ).ready() block.
    $( document ).ready(function() {
    	updateChart();
    });
</script>


</html>
