<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1 class="m-0">${pageTitle}</h1>
            </div>
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <c:forEach varStatus="index" var="breadcrumb" items="${breadcrumbs}">
                        <c:choose>
                            <c:when test="${index.last}">
                                <li class="breadcrumb-item active">${breadcrumb}</li>
                            </c:when>
                            <c:otherwise>
                                <li class="breadcrumb-item"><a href="/admin/home">${breadcrumb}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ol>
            </div>
        </div>
    </div>
</div>
