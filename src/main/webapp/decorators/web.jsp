<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><dec:title default="Trang chủ"/></title>
    <!-- Favicon -->
    <link rel="icon" href="<c:url value="/static/web/img/core-img/favicon.ico"/>">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="<c:url value="/static/web/style.css"/>">
    <%--RemixIcon--%>
    <link rel="stylesheet" href="/static/remixicon/remixicon.css">
</head>
<body>

<%@include file="/common/web/header.jsp" %>

<dec:body/>

<%@include file="/common/web/footer.jsp" %>

<!-- ##### All Javascript Script ##### -->
<!-- jQuery-2.2.4 js -->
<script src="<c:url value="/static/web/js/jquery/jquery-2.2.4.min.js"/>"></script>
<!-- Popper js -->
<script src="<c:url value="/static/web/js/bootstrap/popper.min.js"/>"></script>
<!-- Bootstrap js -->
<script src="<c:url value="/static/web/js/bootstrap/bootstrap.min.js"/>"></script>
<!-- All Plugins js -->
<script src="<c:url value="/static/web/js/plugins/plugins.js"/>"></script>
<!-- Active js -->
<script src="<c:url value="/static/web/js/active.js"/>"></script>
</body>
</html>
