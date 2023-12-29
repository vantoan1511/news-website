<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký</title>
</head>
<body>
<div class="register-box">
    <div class="register-logo">
        <a href="<c:url value="/home"/>">
            <img src="<c:url value="/static/web/img/core-img/logo.png"/>" alt="logo">
        </a>
    </div>

    <div class="card card-outline card-success">
        <div class="card-body register-card-body">
            <c:if test="${accountExisted == true}">
                <div class="alert alert-default-danger" role="alert">
                    Tên đăng nhập đã tồn tại!
                </div>
            </c:if>

            <form:form action="/register" modelAttribute="user" method="post" accept-charset="UTF-8">

                <form:errors path="email" cssStyle="color: red"/>
                <div class="input-group mb-3">
                    <form:input path="email" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                placeholder="Email"/>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-envelope"></span>
                        </div>
                    </div>
                </div>
                <form:errors path="username" cssStyle="color: red"/>
                <div class="input-group mb-3">
                    <form:input path="username" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                placeholder="Username"/>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-user"></span>
                        </div>
                    </div>
                </div>
                <form:errors path="password" cssStyle="color: red"/>
                <div class="input-group mb-3">
                    <form:input path="password" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                placeholder="Password"/>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="icheck-primary">
                            <input type="checkbox" id="agreeTerms" name="terms" value="agree">
                            <label for="agreeTerms">
                                Tôi đã đọc và đồng ý với <a href="#">các điều khoản</a>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <!-- /.col -->
                    <div class="col">
                        <button type="submit" class="btn bg-gradient-success btn-block">Đăng ký</button>
                    </div>
                    <!-- /.col -->
                </div>
            </form:form>

            <div class="social-auth-links text-center">
                <p>- hoặc -</p>
                <a href="<c:url value="/login"/>" class="btn btn-block btn-outline-primary">
                    <i class="fab fa-sign-in"></i>
                    Đã có tài khoản
                </a>
            </div>
        </div>
        <!-- /.form-box -->
    </div><!-- /.card -->
</div>
</body>
</html>
