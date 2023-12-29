<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Đăng nhập</title>
</head>
<body>
<div class="login-box">
    <div class="login-logo">
        <a href="<c:url value="/home"/>">
            <img src="<c:url value="/static/web/img/core-img/logo.png"/>" alt="logo">
        </a>
    </div>
    <!-- /.login-logo -->
    <div class="card card-outline card-primary">
        <div class="card-body login-card-body">
            <c:if test="${message != null}">
                <div class="alert alert-default-${type}" role="alert">
                        ${message}
                </div>
            </c:if>
            <form action="/perform-login" method="post">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" name="username" placeholder="Tên đăng nhập"/>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-user"></span></div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" name="password" placeholder="Mật khẩu đăng nhập"/>
                    <div class="input-group-append">
                        <div class="input-group-text"><span class="fas fa-lock"></span></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="icheck-primary">
                            <input type="checkbox" id="remember">
                            <label for="remember">Ghi nhớ tôi</label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <button type="submit" class="btn bg-gradient-primary btn-block">Đăng nhập</button>
                    </div>
                </div>
            </form>

            <div class="social-auth-links text-center mb-3">
                <p>- hoặc -</p>
                <a href="<c:url value="/register"/>" class="btn btn-block btn-outline-success">
                    <i class="fas fa-user-plus"></i> Tạo tài khoản mới
                </a>
            </div>
            <!-- /.social-auth-links -->

            <p class="mb-1">
                <a href="forgot-password.html">Quên mật khẩu?</a>
            </p>
        </div>
        <!-- /.login-card-body -->
    </div>
</div>
</body>
</html>
