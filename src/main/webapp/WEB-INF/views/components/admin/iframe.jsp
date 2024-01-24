<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<div id="iframe-container" class="hidden">
    <button id="close-button"
            onclick="handlePreviewCloseButtonClick(event)"
            class="btn btn-default"><i class="ri-close-line text-danger"></i> Đóng
    </button>
    <iframe class="preview-frame" src="/${article.slug}?previewMode=true"></iframe>
</div>
