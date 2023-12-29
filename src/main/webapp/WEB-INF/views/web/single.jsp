<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>${article.title}</title>
</head>
<body>
<!-- ##### Post Details Title Area Start ##### -->
<div class="post-details-title-area bg-overlay clearfix" style="background-image: url(${article.thumbnailUrl})">
    <div class="container-fluid h-100">
        <div class="row h-100 align-items-center">
            <div class="col-12 col-lg-8">
                <!-- Post Content -->
                <div class="post-content">
                    <p class="tag"><span>${article.categoryName}</span></p>
                    <p class="post-title">${article.title}</p>
                    <div class="d-flex align-items-center">
                        <span class="post-date mr-30">
                            <i class="fa fa-clock-o" aria-hidden="true"></i> Lúc
                            <fmt:formatDate value="${article.createdDate}"
                                            pattern="HH:mm dd/MM/yyyy"/>
                        </span>
                        <span class="post-date">Bởi ${article.createdBy}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- ##### Post Details Title Area End ##### -->

<!-- ##### Post Details Area Start ##### -->
<section class="post-news-area section-padding-100-0 mb-70">
    <div class="container">
        <div class="row justify-content-center">
            <!-- Post Details Content Area -->
            <div class="col-12 col-lg-8">
                <div class="post-details-content mb-100">
                    ${article.content}
                </div>

                <!-- Comment Area Start -->
                <div class="comment_area clearfix mb-100">
                    <h4 class="mb-50">Comments</h4>
                </div>

                <div class="post-a-comment-area mb-30 clearfix">
                    <h4 class="mb-50">Leave a reply</h4>
                </div>
            </div>

            <!-- Sidebar Widget -->
            <div class="col-12 col-sm-9 col-md-6 col-lg-4">
                <div class="sidebar-area">

                    <!-- Newsletter Widget -->
                    <div class="single-widget-area newsletter-widget mb-30">
                        <h4>Subscribe to our newsletter</h4>
                        <form action="#" method="post">
                            <input type="email" name="nl-email" id="nlemail" placeholder="Your E-mail">
                            <button type="submit" class="btn newsbox-btn w-100">Subscribe</button>
                        </form>
                        <p class="mt-30">Nullam lacinia ex eleifend orci porttitor, suscipit interdum augue condimentum.
                            Etiam pretium turpis eget nibh . volutpat lobortis.</p>
                    </div>

                    <!-- Add Widget -->
                    <div class="single-widget-area add-widget mb-30">
                        <a href="#">
                            <img src="img/bg-img/add3.png" alt="">
                        </a>
                    </div>

                    <!-- Latest News Widget -->
                    <div class="single-widget-area news-widget mb-30">
                        <h4>Latest News</h4>

                        <!-- Single News Area -->
                        <div class="single-blog-post d-flex style-4 mb-30">
                            <!-- Blog Thumbnail -->
                            <div class="blog-thumbnail">
                                <a href="#"><img src="img/bg-img/16.jpg" alt=""></a>
                            </div>

                            <!-- Blog Content -->
                            <div class="blog-content">
                                <span class="post-date">June 20, 2018</span>
                                <a href="#" class="post-title">Nearly a quarter have no emergency savings</a>
                            </div>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-blog-post d-flex style-4 mb-30">
                            <!-- Blog Thumbnail -->
                            <div class="blog-thumbnail">
                                <a href="#"><img src="img/bg-img/17.jpg" alt=""></a>
                            </div>

                            <!-- Blog Content -->
                            <div class="blog-content">
                                <span class="post-date">June 20, 2018</span>
                                <a href="#" class="post-title">Nearly a quarter have no emergency savings</a>
                            </div>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-blog-post d-flex style-4 mb-30">
                            <!-- Blog Thumbnail -->
                            <div class="blog-thumbnail">
                                <a href="#"><img src="img/bg-img/18.jpg" alt=""></a>
                            </div>

                            <!-- Blog Content -->
                            <div class="blog-content">
                                <span class="post-date">June 20, 2018</span>
                                <a href="#" class="post-title">Top bitcoin exchange says over $30 million stolen</a>
                            </div>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-blog-post d-flex style-4 mb-30">
                            <!-- Blog Thumbnail -->
                            <div class="blog-thumbnail">
                                <a href="#"><img src="img/bg-img/19.jpg" alt=""></a>
                            </div>

                            <!-- Blog Content -->
                            <div class="blog-content">
                                <span class="post-date">June 20, 2018</span>
                                <a href="#" class="post-title">Top bitcoin exchange says over $30 million stolen</a>
                            </div>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-blog-post d-flex style-4 mb-30">
                            <!-- Blog Thumbnail -->
                            <div class="blog-thumbnail">
                                <a href="#"><img src="img/bg-img/20.jpg" alt=""></a>
                            </div>

                            <!-- Blog Content -->
                            <div class="blog-content">
                                <span class="post-date">June 20, 2018</span>
                                <a href="#" class="post-title">Dow falls 287 points as trade war fears escalate</a>
                            </div>
                        </div>
                    </div>

                    <!-- Single Widget Area -->
                    <div class="single-widget-area">

                        <!-- Single News Area -->
                        <div class="single-blog-post style-2 mb-5">
                            <!-- Blog Thumbnail -->
                            <div class="blog-thumbnail">
                                <a href="#"><img src="img/bg-img/14.jpg" alt=""></a>
                            </div>

                            <!-- Blog Content -->
                            <div class="blog-content">
                                <span class="post-date">June 20, 2018</span>
                                <a href="#" class="post-title">Elon Musk: Tesla worker admitted to sabotage</a>
                                <a href="#" class="post-author">By Michael Smith</a>
                            </div>
                        </div>

                        <!-- Single News Area -->
                        <div class="single-blog-post style-2 mb-5">
                            <!-- Blog Thumbnail -->
                            <div class="blog-thumbnail">
                                <a href="#"><img src="img/bg-img/15.jpg" alt=""></a>
                            </div>

                            <!-- Blog Content -->
                            <div class="blog-content">
                                <span class="post-date">June 20, 2018</span>
                                <a href="#" class="post-title">Rachel Sm ith breaks down while discussing border
                                    crisis</a>
                                <a href="#" class="post-author">By Michael Smith</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>
<!-- ##### Post Details Area End ##### -->
</body>
</html>
