<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${model.title}</title>
</head>
<body>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <h1>Bài viết: ${model.title}</h1>
            </div>
        </div>
        <!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <%--@elvariable id="model" type=""--%>
        <form:form modelAttribute="model" id="form">
            <form:hidden path="id"/>
            <div class="card">
                <div class="card-body row">
                    <div class="col-auto">
                        <a href="${pageContext.request.contextPath}/admin/articles" class="btn btn-block btn-default">
                            <i class="fas fa-undo text-success"></i> Quay lại
                        </a>
                    </div>
                    <c:if test="${model.id ne 0}">
                        <div class="col-sm-auto">
                            <a href="/${model.slug}?previewMode=true" id="preview-btn"
                               class="btn btn-block btn-default">
                                <i class="fa fa-window-restore" aria-hidden="true"></i> Chế độ xem
                            </a>
                        </div>
                        <c:if test="${model.statusCode eq 'published'}">
                            <div class="col-sm-auto">
                                <button id="unpublish-btn" class="btn btn-block bg-gradient-success">
                                    <i class="fas fa-paper-plane" aria-hidden="true"></i> Hủy đăng tải
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${model.statusCode eq 'pending'}">
                            <div class="col-sm-auto">
                                <button id="publish-btn" class="btn btn-block bg-gradient-success">
                                    <i class="fas fa-paper-plane" aria-hidden="true"></i> Chấp nhận
                                </button>
                            </div>
                        </c:if>
                    </c:if>
                </div>
            </div>
            <div class="card">
                <div id="preview-frame-header" class="card-header" style="display: none">
                    <button id="close-preview" class="btn btn-default"><i class="fas fa-times text-danger"></i> Đóng xem
                        trước
                    </button>
                </div>
                <div id="preview-frame-body" class="card-body" style="display: none"></div>
                <div id="default-body" class="card-body">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="title">Tiêu đề</label>
                            <form:input path="title" cssClass="form-control form-control-border" disabled="true"
                                        placeholder="VD: Bài viết 1"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="slug">Alias</label>
                            <form:input path="slug" id="slug" class="form-control form-control-border" readonly="true"
                                        placeholder="Được khởi tạo tự động dựa trên tiêu đề"/>
                            <p class="text-gray">Alias sẽ được sử dụng làm một phần cho URL bài viết</p>
                        </div>
                    </div>
                    <ul class="nav nav-tabs" id="custom-content-tab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="custom-content-below-home-tab" data-toggle="pill"
                               href="#general" role="tab" aria-controls="general"
                               aria-selected="true">Thông tin chung</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="custom-content-below-profile-tab" data-toggle="pill"
                               href="#custom-content-below-profile" role="tab"
                               aria-controls="custom-content-below-profile" aria-selected="false">Nội dung</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="custom-content-below-messages-tab" data-toggle="pill"
                               href="#custom-content-below-messages" role="tab"
                               aria-controls="custom-content-below-messages" aria-selected="false">Hình ảnh & Đa phương
                                tiện</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="custom-content-below-settings-tab" data-toggle="pill"
                               href="#custom-content-below-settings" role="tab"
                               aria-controls="custom-content-below-settings" aria-selected="false">Tuỳ chỉnh</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="custom-content-below-tabContent">
                        <div class="tab-pane fade active show" id="general" role="tabpanel"
                             aria-labelledby="custom-content-below-home-tab">
                            <div class="row">
                                <div class="col-md-8 col-sm-12">
                                    <div class="form-group">
                                        <label>Mô tả ngắn</label>
                                        <form:textarea path="description" cssClass="form-control form-control-border"
                                                       rows="5" disabled="true"
                                                       placeholder="Mô tả bài viết"/>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div class="form-group">
                                        <label for="statusCode">Trạng thái</label>
                                        <form:select path="statusCode" id="statusCode" disabled="true"
                                                     cssClass="form-control">
                                            <form:options items="${status}"/>
                                        </form:select>
                                    </div>
                                    <div class="form-group">
                                        <label for="categoryCode">Chuyeen muc</label>
                                        <form:select path="categoryCode" id="categoryCode" disabled="true"
                                                     cssClass="form-control form-control-border custom-select">
                                            <form:option value="" label="--Chọn chuyên mục--"/>
                                            <form:options items="${categories}"/>
                                        </form:select>
                                    </div>
                                    <div class="form-group">
                                        <div class="icheck-material-lightgreen icheck-inline">
                                            <form:checkbox path="featured" id="featured" disabled="true"/>
                                            <label for="featured">Đánh dấu nổi bật</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>Truy cập</label>
                                        <form:select path="accessCode" id="accessCode" disabled="true"
                                                     cssClass="form-control form-control-border custom-select">
                                            <form:options items="${access}"/>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="custom-content-below-profile" role="tabpanel"
                             aria-labelledby="custom-content-below-profile-tab">
                            <form:textarea path="content" cssClass="form-control" disabled="true"/>
                        </div>
                        <div class="tab-pane fade" id="custom-content-below-messages" role="tabpanel"
                             aria-labelledby="custom-content-below-messages-tab">
                            <div class="form-group">
                                <label for="thumbnailUrl">Ảnh bìa</label>
                                <form:input path="thumbnailUrl" cssClass="form-control" disabled="true"/>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="custom-content-below-settings" role="tabpanel"
                             aria-labelledby="custom-content-below-settings-tab">
                        </div>
                    </div>
                </div>
            </div>
            <form:hidden path="traffic"/>
        </form:form>
    </section>
</div>
<c:url var="ArticleAPI" value="'/api/v1/articles'"/>
<script>
    $(document).ready(() => {

        // Enable local "video" plugin from /video/video/ folder.
        CKEDITOR.plugins.addExternal('video', '/ckeditor/video/', 'plugin.js');
        CKEDITOR.replace('content', {
            extraPlugins: 'video'
        });

        $('#saveBtn').click(e => savePost(e, false, false));
        $('#saveAndCloseBtn').click(e => savePost(e, true, false));
        $('#saveAndNewBtn').click(e => savePost(e, false, true));

        $('#publish-btn').click(e => publishArticle(e));
        $('#unpublish-btn').click(e => publishArticle(e, false));

        $('#preview-btn').click(function (e) {
            e.preventDefault();
            $('#preview-frame-header').show();
            $('#default-body').hide();
            var iframeDiv = $('#preview-frame-body');
            var siteUrl = $(this).attr('href');
            var iframe = $('<iframe>', {
                src: siteUrl,
                width: '100%',
                height: '768px'
            });
            iframeDiv.empty();
            iframeDiv.append(iframe);
            iframeDiv.show();
        });

        $('#close-preview').click(function (e) {
            e.preventDefault();
            $('#default-body').show();
            $('#preview-frame-header').hide();
            $('#preview-frame-body').hide();
        });
    });

    function savePost(e, saveAndClose, saveAndNew) {
        e.preventDefault();
        var formData = $('#form').serializeArray();
        var content = CKEDITOR.instances.content.getData();
        var data = {};
        $.each(formData, (i, v) => data[v.name] = v.value);
        data["content"] = content;
        console.log(JSON.stringify(data));
        var id = ${model.id};
        handlePostRequest(id, ${ArticleAPI}, data, result => {
            showAlert({
                title: 'Thành công', text: 'Cập nhật dữ liệu thành công!', icon: 'success', callback: () => {
                    if (saveAndClose) location.replace('/admin/articles')
                    else if (saveAndNew) location.replace('/admin/articles/new')
                    else location.replace('/admin/articles/' + result.id)
                }
            })
        }, error => {
            showToast({
                position: 'top-end',
                title: 'Lỗi',
                icon: 'error',
                timer: 2000
            })
        });
    }

    function publishArticle(e, approve = true) {
        e.preventDefault();
        const id = ${model.id};
        var api = ${ArticleAPI}+'/' + id + '/';
        if (approve) api += 'approve';
        else api += 'unpublish';
        handlePostRequest(id, api, undefined, result => {
            location.reload();
        }, error => {
            showToast({position: 'top-end', title: 'Lỗi', icon: 'error', timer: 2000})
        });
    }
</script>
</body>
</html>