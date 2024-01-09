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
        <div class="col-md-12">
            <h3 class="title">Suy nghĩ của bạn</h3>
        </div>
        <div class="form-group col-md-12">
            <label for="text">Bình luận <span class="required"></span></label>
            <textarea class="form-control"
                      id="text"
                      name="text"
                      placeholder="Viết bình luận của bạn tại đây ..."></textarea>
        </div>
        <div class="form-group col-md-12">
            <button onclick="handleReviewSubmitButtonClick(event, '#review-form')"
                    id="review-submit-btn"
                    class="btn btn-primary">Gửi
            </button>
        </div>
        <input type="hidden" name="articleId" value="${article.slug}">
        <input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}">
    </form>
    <h2 class="title">3 Bình luận
        <sec:authorize access="isAnonymous()">
            <a onclick="handleLoginButton(event, this)"
               href="#">Đăng nhập để bình luận</a>
        </sec:authorize>
    </h2>
    <div class="comment-list">
        <div class="item">
            <div class="user">
                <figure>
                    <img src="/static/web/images/img01.jpg">
                </figure>
                <div class="details">
                    <h5 class="name">Mark Otto</h5>
                    <div class="time">24 Hours</div>
                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore <a href="#">magna</a> aliqua. Ut enim ad
                        minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo.
                    </div>
                    <footer>
                        <a href="#">Reply</a>
                    </footer>
                </div>
            </div>
            <div class="reply-list">
                <div class="item">
                    <div class="user">
                        <figure>
                            <img src="/static/web/images/img01.jpg">
                        </figure>
                        <div class="details">
                            <h5 class="name">Mark Otto</h5>
                            <div class="time">24 Hours</div>
                            <div class="description">
                                Quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                consequat. Duis aute irure dolor in reprehenderit in voluptate velit
                                esse
                                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                                cupidatat non
                                proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                            </div>
                            <footer>
                                <a href="#">Reply</a>
                            </footer>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
