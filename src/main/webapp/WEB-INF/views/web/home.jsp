<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
<!-- ##### Breaking News Area Start ##### -->
<section class="breaking-news-area">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12">
                <!-- Breaking News Widget -->
                <div class="breaking-news-ticker d-flex flex-wrap align-items-center">
                    <div class="title">
                        <h6>Trending</h6>
                    </div>
                    <div id="breakingNewsTicker" class="ticker">
                        <ul>
                            <c:forEach var="article" items="${latestArticles}">
                                <li><a href="/${article.slug}">${article.title}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- ##### Intro News Area Start ##### -->
<section class="intro-news-area">
    <div class="container">
        <div class="row justify-content-center">
            <!-- Intro News Tabs Area -->
            <div class="col-12 col-lg-8">
                <div class="intro-news-tab">

                    <!-- Intro News Filter -->
                    <div class="intro-news-filter d-flex justify-content-between">
                        <h6>All the news</h6>
                        <nav>
                            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link active" id="nav1" data-toggle="tab" href="#nav-1" role="tab"
                                   aria-controls="nav-1" aria-selected="true">Latest</a>
                                <a class="nav-item nav-link" id="nav2" data-toggle="tab" href="#nav-2" role="tab"
                                   aria-controls="nav-2" aria-selected="false">Popular</a>
                                <a class="nav-item nav-link" id="nav3" data-toggle="tab" href="#nav-3" role="tab"
                                   aria-controls="nav-3" aria-selected="false">International</a>
                                <a class="nav-item nav-link" id="nav4" data-toggle="tab" href="#nav-4" role="tab"
                                   aria-controls="nav-4" aria-selected="false">Local</a>
                            </div>
                        </nav>
                    </div>

                    <div class="tab-content" id="nav-tabContent">
                        <div class="tab-pane fade show active" id="nav-1" role="tabpanel" aria-labelledby="nav1">
                            <div class="row">
                                <!-- Single News Area -->
                                <div class="col-12">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/24.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">
                                                June 20, 2018
                                            </span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author mb-30">By Michael Smith</a>
                                            <p>Nullam lacinia ex eleifend orci porttitor, suscipit interdum augue
                                                condimentum. Etiam pretium turpis eget nibh laoreet iaculis. Proin ac
                                                urna at lectus volutpat lobortis. Vestibulum venenatis iaculis diam
                                                vitae lobortis. Donec tincidunt viverra elit, sed consectetur est pr
                                                etium ac. Mauris nec mauris tellus. </p>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/14.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/15.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Rachel Sm ith breaks down while discussing
                                                border crisis</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/4.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/5.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Rachel Sm ith breaks down while discussing
                                                border crisis</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="nav-2" role="tabpanel" aria-labelledby="nav2">
                            <div class="row">
                                <!-- Single News Area -->
                                <div class="col-12">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/25.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author mb-30">By Michael Smith</a>
                                            <p>Nullam lacinia ex eleifend orci porttitor, suscipit interdum augue
                                                condimentum. Etiam pretium turpis eget nibh laoreet iaculis. Proin ac
                                                urna at lectus volutpat lobortis. Vestibulum venenatis iaculis diam
                                                vitae lobortis. Donec tincidunt viverra elit, sed consectetur est pr
                                                etium ac. Mauris nec mauris tellus. </p>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/14.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/15.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Rachel Sm ith breaks down while discussing
                                                border crisis</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/4.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/5.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Rachel Sm ith breaks down while discussing
                                                border crisis</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="nav-3" role="tabpanel" aria-labelledby="nav3">
                            <div class="row">
                                <!-- Single News Area -->
                                <div class="col-12">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/26.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author mb-30">By Michael Smith</a>
                                            <p>Nullam lacinia ex eleifend orci porttitor, suscipit interdum augue
                                                condimentum. Etiam pretium turpis eget nibh laoreet iaculis. Proin ac
                                                urna at lectus volutpat lobortis. Vestibulum venenatis iaculis diam
                                                vitae lobortis. Donec tincidunt viverra elit, sed consectetur est pr
                                                etium ac. Mauris nec mauris tellus. </p>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/14.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/15.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Rachel Sm ith breaks down while discussing
                                                border crisis</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/4.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/5.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Rachel Sm ith breaks down while discussing
                                                border crisis</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="nav-4" role="tabpanel" aria-labelledby="nav4">
                            <div class="row">
                                <!-- Single News Area -->
                                <div class="col-12">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/27.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author mb-30">By Michael Smith</a>
                                            <p>Nullam lacinia ex eleifend orci porttitor, suscipit interdum augue
                                                condimentum. Etiam pretium turpis eget nibh laoreet iaculis. Proin ac
                                                urna at lectus volutpat lobortis. Vestibulum venenatis iaculis diam
                                                vitae lobortis. Donec tincidunt viverra elit, sed consectetur est pr
                                                etium ac. Mauris nec mauris tellus. </p>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/14.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/15.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Rachel Sm ith breaks down while discussing
                                                border crisis</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/4.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Elon Musk: Tesla worker admitted to
                                                sabotage</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single News Area -->
                                <div class="col-12 col-sm-6">
                                    <div class="single-blog-post style-2 mb-5">
                                        <!-- Blog Thumbnail -->
                                        <div class="blog-thumbnail">
                                            <a href="#"><img src="/static/web/img/bg-img/5.jpg" alt=""></a>
                                        </div>

                                        <!-- Blog Content -->
                                        <div class="blog-content">
                                            <span class="post-date">June 20, 2018</span>
                                            <a href="#" class="post-title">Rachel Sm ith breaks down while discussing
                                                border crisis</a>
                                            <a href="#" class="post-author">By Michael Smith</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
<!-- ##### Intro News Area End ##### -->
</body>
</html>
