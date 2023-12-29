<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><dec:title default="Trang chủ"/></title>
    <link rel="shortcut icon" href="/static/web/img/core-img/favicon.ico" type="image/x-icon">

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="<c:url value="/static/admin/plugins/fontawesome-free/css/all.min.css"/>">
    <!-- Ionicons -->
    <link rel="stylesheet"
          href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <%--Remix Icon--%>
    <link rel="stylesheet" href="/static/remixicon/remixicon.css">
    <!-- iCheck -->
    <link rel="stylesheet"
          href="<c:url value="/static/admin/plugins/icheck-bootstrap/icheck-bootstrap.min.css"/>">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/icheck-material@1.0.1/icheck-material.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="/static/admin/dist/css/adminlte.min.css"/>">
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.9.0/dist/sweetalert2.min.css" rel="stylesheet">
    <%--Select2--%>
    <link rel="stylesheet" href="/static/admin/plugins/select2/css/select2.min.css">
    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

    <link rel="stylesheet" href="/static/custom/css/styles.css">

</head>
<body class="hold-transition sidebar-mini layout-fixed">

<div class="wrapper">

    <!-- Navbar -->
    <%@ include file="/common/admin/navbar.jsp" %>

    <!-- Main Sidebar Container -->
    <%@ include file="/common/admin/sidebar.jsp" %>

    <dec:body/>

    <!-- Footer -->
    <%@ include file="/common/admin/footer.jsp" %>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->

</div>

<!-- pagination -->
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Bootstrap 4 -->
<script
        src="<c:url value="/static/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/static/admin/dist/js/adminlte.js"/>"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.9.0/dist/sweetalert2.all.min.js"></script>
<%--Select2--%>
<script src="/static/admin/plugins/select2/js/select2.min.js"></script>
<!-- Custom -->
<script src="<c:url value="/static/custom/js/main.js"/>"></script>
<!-- Utils -->
<script src="<c:url value="/static/custom/js/utils.js"/>"></script>
</body>
</html>