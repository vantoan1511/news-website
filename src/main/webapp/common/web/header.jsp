<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="com.vtoan1517.utils.SecurityUtils" %>
<%@include file="/common/taglib.jsp" %>
<!-- ##### Header Area Start ##### -->
<header class="header-area">
    <!-- Navbar Area -->
    <div class="newsbox-main-menu">
        <div class="classy-nav-container breakpoint-off">
            <div class="container-fluid">
                <!-- Menu -->
                <nav class="classy-navbar justify-content-between" id="newsboxNav">

                    <!-- Nav brand -->
                    <a href="<c:url value="/home"/>" class="nav-brand"><img
                            src="<c:url value="/static/web/img/core-img/logo-md.png"/>" alt=""></a>

                    <!-- Navbar Toggler -->
                    <div class="classy-navbar-toggler">
                        <span class="navbarToggler"><span></span><span></span><span></span></span>
                    </div>

                    <!-- Menu -->
                    <div class="classy-menu">

                        <!-- Close Button -->
                        <div class="classycloseIcon">
                            <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                        </div>

                        <!-- Nav Start -->
                        <div class="classynav">
                            <ul>
                                <li><a href="#">Tất cả</a>
                                    <div class="megamenu">
                                        <ul class="single-mega cn-col-4">
                                            <li class="title">Europe</li>
                                            <li><a href="#">United Kingdom</a></li>
                                            <li><a href="#">Germany</a></li>
                                            <li><a href="#">Latvia</a></li>
                                            <li><a href="#">Poland</a></li>
                                            <li><a href="#">Italy</a></li>
                                            <li><a href="#">France</a></li>
                                            <li><a href="#">Crotia</a></li>
                                        </ul>
                                        <ul class="single-mega cn-col-4">
                                            <li class="title">Africa</li>
                                            <li><a href="#">Algeria</a></li>
                                            <li><a href="#">Angola</a></li>
                                            <li><a href="#">Benin</a></li>
                                            <li><a href="#">Botswana</a></li>
                                            <li><a href="#">Burkina Faso</a></li>
                                            <li><a href="#">Burundi</a></li>
                                            <li><a href="#">Cameroon</a></li>
                                        </ul>
                                        <ul class="single-mega cn-col-4">
                                            <li class="title">Asia</li>
                                            <li><a href="#">Bangladesh</a></li>
                                            <li><a href="#">Chaina</a></li>
                                            <li><a href="#">India</a></li>
                                            <li><a href="#">Afganistan</a></li>
                                            <li><a href="#">Sri Lanka</a></li>
                                            <li><a href="#">Nepal</a></li>
                                            <li><a href="#">Bhutan</a></li>
                                        </ul>
                                        <ul class="single-mega cn-col-4">
                                            <li class="title">USA &amp; Canada</li>
                                            <li><a href="#">California</a></li>
                                            <li><a href="#">Florida</a></li>
                                            <li><a href="#">Alabama</a></li>
                                            <li><a href="#">New Yorks</a></li>
                                            <li><a href="#">Texas</a></li>
                                            <li><a href="#">Lowa</a></li>
                                            <li><a href="#">Montana</a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li><a href="#">Local News</a></li>
                                <li><a href="#">Pages</a>
                                    <ul class="dropdown">
                                        <li><a href="index.html">Home</a></li>
                                        <li><a href="catagory.html">Catagory</a></li>
                                        <li><a href="single-post.html">Single Post</a></li>
                                        <li><a href="contact.html">Contact</a></li>
                                        <li><a href="elements.html">Elements</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <ul>
                                <sec:authorize access="isAnonymous()">
                                    <li><a href="<c:url value="/login"/>"><i class="fa fa-sign-in"></i> Đăng nhập</a>
                                    </li>
                                    <li><a href="<c:url value="/register"/>"><i class="fa fa-user-plus"></i> Đăng ký</a>
                                    </li>
                                </sec:authorize>
                                <sec:authorize access="isAuthenticated()">
                                    <li><a href="#"><i class="fa fa-user"></i> Xin
                                        chào, <%=SecurityUtils.getPrincipal().getFirstName()%>
                                    </a>
                                    </li>
                                    <li><a href="<c:url value="/logout"/>"><i class="fa fa-sign-out"></i> Thoát</a>
                                    </li>
                                </sec:authorize>
                            </ul>
                            <!-- Header Add Area -->
                        </div>
                        <!-- Nav End -->

                    </div>
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- ##### Header Area End ##### -->