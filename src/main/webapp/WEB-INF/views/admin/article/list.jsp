<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Danh sách bài viết</title>
</head>

<body>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Danh sách bài viết</h1>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="card">
            <div class="card-body row">
                <div class="col-md-auto col-sm-3">
                    <button id="approve-btn"
                            class="btn btn-block bg-gradient-success" title="Chấp nhận">
                        <i class="fas fa-check"></i> Chấp nhận
                    </button>
                </div>
                <div class="col-md-auto col-sm-3">
                    <button id="refuse-btn"
                            class="btn btn-block btn-default" title="Từ chối">
                        <i class="fas fa-times text-danger"></i> Từ chối
                    </button>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <div class="row">
                    <div class="col-md-2">
                        <div class="row">
                            <select name="sort-by" id="sort-by" class="col form-control custom-select">
                                <option value="title">Tiêu đề</option>
                                <option value="categoryId">Chuyên mục</option>
                                <option value="createdDate">Ngày tạo</option>
                                <option value="modifiedDate">Ngày sửa đổi</option>
                                <option value="createdBy">Tác giả</option>
                                <option value="traffic">Lượng truy cập</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="row">
                            <select name="sort-order" id="sort-order" class="col form-control custom-select">
                                <option value="ASC">Tăng dần</option>
                                <option value="DESC">Giảm dần</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="row">
                            <select id="limit-select" class="col form-control custom-select">
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="20">20</option>
                                <option value="30">30</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body p-0">
                <table id="myTable" class="table table-bordered table-hover projects">
                    <thead>
                    <tr>
                        <th>
                            <div class="form-check icheck-material-red">
                                <input class="form-check-input check-all" type="checkbox" id="select-all">
                                <label for="select-all"></label>
                            </div>
                        </th>
                        <th>Id</th>
                        <th>Tiêu đề</th>
                        <th>Trạng thái</th>
                        <th>Nổi bật</th>
                        <th>Thể loại</th>
                        <th>Ngày tạo</th>
                        <th>Lần sửa cuối</th>
                        <th>Tác giả</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach varStatus="loop" var="article" items="${model.data}">
                        <tr>
                            <td>
                                <div class="form-check icheck-material-red">
                                    <input class="form-check-input check-box" type="checkbox" id="${article.id}"
                                           value="${article.id}">
                                    <label for="${article.id}"></label>
                                </div>
                            </td>
                            <td>${article.id}</td>
                            <td><a href="/admin/articles/${article.id}">${article.title}</a></td>
                            <td class="status-cell text-center">${article.statusName}</td>
                            <td>${article.featured}</td>
                            <td>${article.categoryName}</td>
                            <td class="created-date">
                                <fmt:formatDate value="${article.createdDate}" pattern="HH:mm dd/MM/yyyy"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${article.modifiedDate}" pattern="HH:mm dd/MM/yyyy"/>
                            </td>
                            <td>${article.createdBy}</td>
                            <td>
                                <c:if test="${article.statusCode ne 'draft' and article.statusCode ne 'trash'}">
                                    <div class="btn-group">
                                        <button type="button"
                                                class="btn btn-default dropdown-toggle dropdown-icon"
                                                data-toggle="dropdown"
                                                aria-expanded="false"></button>
                                        <div class="dropdown-menu dropdown-menu-right" role="menu" style>
                                            <c:if test="${article.statusCode eq 'pending'}">
                                                <a class="dropdown-item btn btn-default btn-sm approve-single"
                                                   href="#" data-article-id="${article.id}">
                                                    <i class="fas fa-check text-success"></i> Chấp nhận
                                                </a>
                                                <a class="dropdown-item btn btn-default btn-sm refuse-single"
                                                   href="#" data-article-id="${article.id}">
                                                    <i class="fas fa-times text-danger"></i> Từ chối
                                                </a>
                                            </c:if>
                                            <c:if test="${article.statusCode eq 'published'}">
                                                <a class="dropdown-item btn btn-default btn-sm unpublish-single"
                                                   href="#" data-article-id="${article.id}">
                                                    <i class="fas fa-times text-danger"></i> Hủy đăng tải
                                                </a>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="card-footer clear-fix row">
                <div class="col-auto">
                    <span>Trang ${model.page} - Hiển thị ${model.data.size()} trong số ${model.totalItems} mục.</span>
                </div>
                <div class="col-auto">
                    <ul id="pagination" class="pagination-sm"></ul>
                </div>
            </div>
        </div>
    </section>
    <!---->

    <form id="form-submit" action="${pageContext.request.contextPath}/admin/articles" method="get">
        <input type="hidden" name="tab" value="${param.tab}">
        <input type="hidden" value="${model.page}" name="page" id="page">
        <input type="hidden" value="${model.limit}" name="limit" id="limit">
        <input type="hidden" value="${model.sortBy}" name="sortBy" id="sortBy">
        <input type="hidden" value="${model.sortOrder}" name="sortOrder" id="sortOrder">
    </form>
</div>
<script>
    $(document).ready(() => {

        paginate();

        $('#select-all').change(function () {
            $('.check-box').prop('checked', this.checked);
        });

        $('.check-box').change(() => {
            $('#select-all').prop('checked', $('.check-box:checked').length === $('.check-box').length);
        });

        $('.approve-single').click(function (e) {
            e.preventDefault();
            const id = $(this).data('article-id');
            var api = '/api/v1/articles/' + id + '/approve';
            handlePostRequest(id, api, undefined, result => {
                location.reload();
            }, error => {
                showToast({position: 'top-end', title: 'Lỗi', icon: 'error', timer: 2000})
            });
        });

        $('.refuse-single').click(function (e) {
            e.preventDefault();
            const id = $(this).data('article-id');
            var api = '/api/v1/articles/' + id + '/refuse';
            handlePostRequest(id, api, undefined, result => {
                location.reload();
            }, error => {
                showToast({position: 'top-end', title: 'Lỗi', icon: 'error', timer: 2000})
            });
        });

        $('.unpublish-single').click(function (e) {
            e.preventDefault();
            const id = $(this).data('article-id');
            var api = '/api/v1/articles/' + id + '/unpublish';
            handlePostRequest(id, api, undefined, result => {
                location.reload();
            }, error => {
                showToast({position: 'top-end', title: 'Lỗi', icon: 'error', timer: 2000})
            });
        });

        $('#approve-btn').click(function (e) {
            e.preventDefault();
            const ids = $('tbody input[type=checkbox]:checked').map((index, element) => $(element).val()).get();
            if (ids.length < 1) {
                showErrorToast('Không có mục nào được chọn', 2000)
            } else {

            }
        })
        $('#refuse-btn').click(function (e) {
            e.preventDefault();
            const ids = $('tbody input[type=checkbox]:checked').map((index, element) => $(element).val()).get();
            if (ids.length < 1) {
                showErrorToast('Không có mục nào được chọn', 2000)
            } else {

            }
        })
    });

    function publishArticle(model, e, approve = true) {
        e.preventDefault();
        const id = $(model).data('article-id');
        var api = '/api/v1/articles/' + id + '/';
        if (approve) api += 'approve';
        else api += 'unpublish';
        handlePostRequest(id, api, undefined, result => {
            location.reload();
        }, error => {
            showToast({position: 'top-end', title: 'Lỗi', icon: 'error', timer: 2000})
        });
    }

    function paginate() {

        var currentPage = ${model.page};
        var totalPages = ${model.totalPages};
        var sortBy = '${model.sortBy}';
        var sortOrder = '${model.sortOrder}';
        var limit = ${model.limit};
        var totalItems = ${model.totalItems};

        $('#sort-by option').each(function () {
            var value = $(this).attr('value');
            if (sortBy === value) {
                $(this).attr('selected', true);
            }
        });

        $('#sort-order option').each(function () {
            if (sortOrder === $(this).attr('value')) {
                $(this).attr('selected', true);
            }
        });

        $('#sort-by').on('change', function () {
            $('#sortBy').val($(this).val());
            $('#form-submit').submit();

        });

        $('#sort-order').on('change', function () {
            $('#sortOrder').val($(this).val());
            $('#form-submit').submit();

        });

        $('#limit-select option[value="' + limit + '"]').prop('selected', true);

        $('#limit-select').on('change', function () {
            $('#page').val(1);
            $('#limit').val($(this).val());
            $('#form-submit').submit();
        })

        if (totalItems > 0) {
            $('#pagination').twbsPagination({
                startPage: currentPage,
                totalPages: totalPages,
                visiblePages: 7,
                first: 'Đầu',
                last: 'Cuối',
                next: 'Tiếp',
                prev: 'Lùi',
                onPageClick: function (event, page) {
                    if (currentPage !== page) {
                        $('#page').val(page);
                        $('#form-submit').submit();
                    }
                }
            });
        }
    }

</script>
</body>
</html>