
<!--   Core JS Files   -->
<script
	src="<c:url value='/template/admin/js/core/jquery.3.2.1.min.js'/>"></script>
<script src="<c:url value='/template/admin/js/core/popper.min.js'/>"></script>
<script src="<c:url value='/template/admin/js/core/bootstrap.min.js'/>"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script
	src="<c:url value='/template/admin/js/plugins/bootstrap-switch.js'/>"></script>
<!--  Notifications Plugin    -->
<script
	src="<c:url value='/template/admin/js/plugins/bootstrap-notify.js'/>"></script>

<!-- jquery autocomplete -->
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<!-- datatable -->
<script
	src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>

<!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc -->
<script
	src="<c:url value='/template/admin/js/light-bootstrap-dashboard.js?v=2.0.0'/>"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
<script type="text/javascript">
$(document).ready(function () {
    $('#product').DataTable();
});
$(document).ready(function () {
    $('#category').DataTable();
});
$(document).ready(function () {
    $('#orders').DataTable();
});

</script>
