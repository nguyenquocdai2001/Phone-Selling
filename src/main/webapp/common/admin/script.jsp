	<!--   Core JS Files   -->
	<script
		src="<c:url value='/template/admin/js/core/jquery.3.2.1.min.js'/>"></script>
	<script src="<c:url value='/template/admin/js/core/popper.min.js'/>"></script>
	<script src="<c:url value='/template/admin/js/core/bootstrap.min.js'/>"></script>
	<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
	<script
		src="<c:url value='/template/admin/js/plugins/bootstrap-switch.js'/>"></script>
	<!--  Google Maps Plugin    -->
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
	<!--  Chartist Plugin  -->
	<script
		src="<c:url value='/template/admin/js/plugins/chartist.min.js'/>"></script>
	<!--  Notifications Plugin    -->
	<script
		src="<c:url value='/template/admin/js/plugins/bootstrap-notify.js'/>"></script>
	<!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc -->
	<script
		src="<c:url value='/template/admin/js/light-bootstrap-dashboard.js?v=2.0.0'/>"></script>
	<!-- Light Bootstrap Dashboard DEMO methods, don't include it in your project! -->
	<script src="<c:url value='/template/admin/js/demo.js'/>"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			// Javascript method's body can be found in assets/js/demos.js
			demo.initDashboardPageCharts();

			demo.showNotification();

		});
	</script>