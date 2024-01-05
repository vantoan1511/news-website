<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>${article.title}</title>
</head>
<body>
<section class="single">
    <div class="container">
        <div class="row">

            <%@ include file="../components/web/recent.jsp" %>

            <div class="col-md-8">
                <ol class="breadcrumb">
                    <li><a href="#">Trang chủ</a></li>
                    <li class="active">${article.categoryName}</li>
                </ol>
                <article class="article main-article">
                    <header>
                        <h1>${article.title}</h1>
                        <ul class="details">
                            <li>Đã đăng lúc <fmt:formatDate value="${article.publishedDate}"
                                                            pattern="HH:mm dd/MM/yyyy"/>
                            </li>
                            <li><a href="/categories/${article.categoryCode}">${article.categoryName}</a></li>
                            <li>Bởi <a href="#">${article.createdBy}</a></li>
                        </ul>
                    </header>
                    <div class="main">${article.content}</div>
                    <footer>
                        <div class="col"></div>
                        <div class="col">
                            <a href="#" class="love"><i class="ion-android-favorite-outline"></i>
                                <div>${article.traffic}</div>
                            </a>
                        </div>
                    </footer>
                </article>
                <div class="sharing">
                    <div class="title"><i class="ion-android-share-alt"></i> Sharing is caring</div>
                    <ul class="social">
                        <li>
                            <a href="#" class="facebook">
                                <i class="ion-social-facebook"></i> Facebook
                            </a>
                        </li>
                        <li>
                            <a href="#" class="twitter">
                                <i class="ion-social-twitter"></i> Twitter
                            </a>
                        </li>
                        <li>
                            <a href="#" class="googleplus">
                                <i class="ion-social-googleplus"></i> Google+
                            </a>
                        </li>
                        <li>
                            <a href="#" class="linkedin">
                                <i class="ion-social-linkedin"></i> Linkedin
                            </a>
                        </li>
                        <li>
                            <a href="#" class="skype">
                                <i class="ion-ios-email-outline"></i> Email
                            </a>
                        </li>
                        <li class="count">
                            20
                            <div>Shares</div>
                        </li>
                    </ul>
                </div>
                <div class="line">
                    <div>Author</div>
                </div>
                <div class="author">
                    <figure>
                        <img src="/static/web/images/img01.jpg">
                    </figure>
                    <div class="details">
                        <div class="job">${article.createdBy}</div>
                        <h3 class="name">John Doe</h3>
                        <p>Nulla sagittis rhoncus nisi, vel gravida ante. Nunc lobortis condimentum elit, quis porta
                            ipsum rhoncus vitae. Curabitur magna leo, porta vel fringilla gravida, consectetur in
                            libero. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum.</p>
                        <ul class="social trp sm">
                            <li>
                                <a href="#" class="facebook">
                                    <svg>
                                        <rect/>
                                    </svg>
                                    <i class="ion-social-facebook"></i>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="twitter">
                                    <svg>
                                        <rect/>
                                    </svg>
                                    <i class="ion-social-twitter"></i>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="youtube">
                                    <svg>
                                        <rect/>
                                    </svg>
                                    <i class="ion-social-youtube"></i>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="googleplus">
                                    <svg>
                                        <rect/>
                                    </svg>
                                    <i class="ion-social-googleplus"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="line">
                    <div>You May Also Like</div>
                </div>
                <div class="row">
                    <article class="article related col-md-6 col-sm-6 col-xs-12">
                        <div class="inner">
                            <figure>
                                <a href="#">
                                    <img src="/static/web/images/news/img03.jpg">
                                </a>
                            </figure>
                            <div class="padding">
                                <h2><a href="#">Duis aute irure dolor in reprehenderit in voluptate</a></h2>
                                <div class="detail">
                                    <div class="category"><a href="category.html">Lifestyle</a></div>
                                    <div class="time">December 26, 2016</div>
                                </div>
                            </div>
                        </div>
                    </article>
                    <article class="article related col-md-6 col-sm-6 col-xs-12">
                        <div class="inner">
                            <figure>
                                <a href="#">
                                    <img src="/static/web/images/news/img08.jpg">
                                </a>
                            </figure>
                            <div class="padding">
                                <h2><a href="#">Duis aute irure dolor in reprehenderit in voluptate</a></h2>
                                <div class="detail">
                                    <div class="category"><a href="category.html">Lifestyle</a></div>
                                    <div class="time">December 26, 2016</div>
                                </div>
                            </div>
                        </div>
                    </article>
                </div>
                <div class="line thin"></div>
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
                        </div>
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
            </div>
        </div>
    </div>
</section>
</body>
</html>
