<%@ page import="com.vtoan1517.utils.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../../common/taglib.jsp" %>

<div id="comments-section"
     class="comments">
    <form accept-charset="UTF-8"
          <sec:authorize access="isAnonymous()">class="hidden"</sec:authorize>
          class="row"
          id="review-form">
        <div id="leave-review" class="col-md-12">
            <h3 class="title">Suy nghĩ của bạn</h3>
        </div>
        <div class="form-group col-md-12">
            <label for="text">Bình luận <span class="required"></span></label>
            <div id="reply-to-text"
                 class="hidden">
                <blockquote id="reply-text"></blockquote>
            </div>
            <textarea class="form-control"
                      id="text"
                      name="text"
                      placeholder="Viết bình luận của bạn tại đây ..."></textarea>
        </div>
        <div class="form-group col-md-12">
            <button onclick="handleReviewSubmitButton(event, '#review-form')"
                    id="review-submit-btn"
                    class="btn btn-primary">Gửi
            </button>
            <a onclick="handleReplyButton(this, false)"
               id="cancel-btn"
               href="#leave-review"
               class="btn btn-danger">Hủy
            </a>
        </div>
        <input type="hidden" name="id" value="">
        <input type="hidden" name="articleSlug" value="${article.slug}">
        <input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}">
        <input type="hidden" name="rootId" value="">
    </form>
    <h2 class="title">${comments.getTotalElements()} Bình luận
        <sec:authorize access="isAnonymous()">
            <a onclick="handleLoginButton(event, this)"
               href="#">Đăng nhập để bình luận</a>
        </sec:authorize>
    </h2>
    <div id="comment-list" class="comment-list">
        <c:forEach items="${comments.getContent()}" var="review">
            <div id="${review.id}" class="item">
                <div class="user">
                    <figure>
                        <img src="/static/web/images/img01.jpg">
                    </figure>
                    <div class="details">
                        <h5 class="name">${review.userFirstName} ${review.userLastName}</h5>
                        <div class="time"><fmt:formatDate value="${review.createdDate}"/></div>
                        <c:if test="${not empty review.parentText}">
                            <div class="reply-to-text">
                                <blockquote>${review.parentText}</blockquote>
                            </div>
                        </c:if>
                        <div class="description">${review.text}</div>
                        <sec:authorize access="isAuthenticated()">
                            <footer>
                                <a onclick="handleReplyButton(this)"
                                   data-item-id="${review.id}"
                                   class="reply-button"
                                   href="#leave-review">Trả lời <i class="ion-reply"></i> </a>
                                <c:if test="${review.createdBy eq pageContext.request.userPrincipal.name}">
                                    <a onclick="handleUpdateReviewButton(this)"
                                       data-item-id="${review.id}"
                                       href="#leave-review"> Sửa <i class="ion-edit"></i></a>
                                    <a onclick="handleDeleteReviewButton(this)"
                                       data-item-id="${review.id}"
                                       href="#comment-list"> Xóa <i class="ion-android-delete"></i></a>
                                </c:if>
                            </footer>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="row">
        <div class="col text-center">
            <c:if test="${comments.hasNext()}">
                <input type="hidden" name="page" value="${comments.getNumber()}">
                <input type="hidden" name="limit" value="${comments.getSize()}">
                <button onclick="handleLoadMoreReviews(event, ${article.id}, ${comments.getNumber()}, ${comments.getSize()})"
                        id="load-more-review-btn"
                        class="btn btn-magz">Xem thêm <i class="ion-android-arrow-down"></i></button>
            </c:if>
        </div>
    </div>
</div>
