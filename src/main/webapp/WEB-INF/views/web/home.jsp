<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<section class="home">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-sm-12 col-xs-12">

                <%@include file="../../../common/web/headline.jsp" %>

                <%@include file="../../../common/web/featured.jsp" %>

                <div class="line">
                    <div>Latest News</div>
                </div>

                <%@include file="../../../common/web/latest.jsp" %>

                <div class="line top">
                    <div>Related News</div>
                </div>

                <%@include file="../../../common/web/related.jsp" %>
            </div>

            <%@include file="../../../common/web/sidebar.jsp" %>

        </div>
    </div>
</section>

<%@ include file="../../../common/web/bestOfTheWeek.jsp" %>
</body>
</html>
