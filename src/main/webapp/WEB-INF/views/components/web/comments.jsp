<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../../common/taglib.jsp" %>

<div class="comments">
    <h2 class="title">3 Responses <a href="#">Write a Response</a></h2>
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
    <form class="row">
        <div class="col-md-12">
            <h3 class="title">Leave Your Response</h3>
        </div>
        <div class="form-group col-md-4">
            <label for="name">Name <span class="required"></span></label>
            <input type="text" id="name" name="" class="form-control">
        </div>
        <div class="form-group col-md-4">
            <label for="email">Email <span class="required"></span></label>
            <input type="email" id="email" name="" class="form-control">
        </div>
        <div class="form-group col-md-4">
            <label for="website">Website</label>
            <input type="url" id="website" name="" class="form-control">
        </div>
        <div class="form-group col-md-12">
            <label for="message">Response <span class="required"></span></label>
            <textarea class="form-control" name="message"
                      placeholder="Write your response ..."></textarea>
        </div>
        <div class="form-group col-md-12">
            <button class="btn btn-primary">Send Response</button>
        </div>
    </form>
</div>
