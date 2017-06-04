<!--
author: W3layouts
author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${requestScope.current_locale}"/>
<fmt:setBundle basename="lang" var="lang"/>
<html>
<head>
    <title>Super Market | Registered</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Super Market Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);
        function hideURLbar() {
            window.scrollTo(0, 1);
        }

    </script>
    <!-- //for-mobile-apps -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- font-awesome icons -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/validator/validator_functions.js"></script>
    <!-- jQuery validator -->
    <!-- <script type="text/javascript" src="js/validator/jQuery_validator.js"></script> -->
    <!-- JS validator -->
    <!--<script type="text/javascript" src="js/validator/js_validator.js"></script>-->
    <!-- //js -->
    <!-- start-smoth-scrolling -->
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });

    </script>
    <script>
        jQuery(document).ready(function ($) {
            $('#lang').change(function () {
                var lang = $('#lang').val();
                var url = window.location;
                if (url.search.includes('?')) {
                    if (url.search.includes('?lang=')) {
                        var search = url.search.substring(url.search.indexOf('lang=' + 1, 'lang='.length + 2));
                        url = url.href.replace(search, '?lang=' + lang);
                    } else {
                        url = "lang" + lang;
                    }
                } else {
                    url += "?lang=" + lang;
                }
                window.location = url;
            });
        });
    </script>
    <!-- start-smoth-scrolling -->
</head>
<body>
<!-- header -->
<div class="agileits_header">
    <div class="container">
        <div class="w3l_offers">
        </div>
        <div class="agile-login">
            <ul>
                <li><a href="registration"><fmt:message key="create.account" bundle="${lang}"/></a></li>
                <li><a href="login"><fmt:message key="login" bundle="${lang}"/></a></li>
                <li><a href="logout"><fmt:message key="logout" bundle="${lang}"/></a></li>
                <custom:locale/>
            </ul>
        </div>
        <div class="product_list_header">
            <form action="cart" method="get" class="last">
                <button class="w3view-cart" type="submit" name="submit" value=""><i class="fa fa-cart-arrow-down"
                                                                                    aria-hidden="true"></i></button>
            </form>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<div class="logo_products">
    <div class="container">
        <div class="w3ls_logo_products_left1">
            <ul class="phone_email">
                <li><i class="fa fa-phone" aria-hidden="true"></i><fmt:message key="order.or.call" bundle="${lang}"/> :
                    (+0123) 234 567
                </li>
            </ul>
        </div>
        <div class="w3ls_logo_products_left">
            <h1><a href="index.html">Super Market</a></h1>
        </div>
        <div class="w3l_search">
            <form action="#" method="post">
                <input type="search" name="Search" placeholder="<fmt:message key="search" bundle="${lang}"/> "
                       required="">
                <button type="submit" class="btn btn-default search" aria-label="Left Align">
                    <i class="fa fa-search" aria-hidden="true"> </i>
                </button>
                <div class="clearfix"></div>
            </form>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<!-- //header -->
<!-- navigation -->
<div class="navigation-agileits">
    <div class="container">
        <nav class="navbar navbar-default">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header SALE UP TO 70% OFF. USnav_2">
                <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse"
                        data-target="#bs-megadropdown-tabs">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.html" class="act"><fmt:message key="home" bundle="${lang}"/></a>
                    </li>
                    <li><a href="products"><fmt:message key="all.products" bundle="${lang}"/></a></li>
                </ul>
            </div>
        </nav>
    </div>
</div>
<!-- //navigation -->
<!-- breadcrumbs -->
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="index.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span><fmt:message
                    key="home" bundle="${lang}"/></a></li>
            <li class="active"><fmt:message key="create.account" bundle="${lang}"/></li>
        </ol>
    </div>
</div>
<!-- //breadcrumbs -->
<!-- register -->
<div class="register">
    <div class="container">
        <h2><fmt:message key="register.here" bundle="${lang}"/></h2>
        <div class="login-form-grids">
            <h5><fmt:message key="profile.info" bundle="${lang}"/></h5>
            <form name="registration" enctype="multipart/form-data" id="registration" action="registration"
                  method="post">
                <input type="text" class="text" name="first name" id="first_name"
                <c:if test="${requestScope.errors != null}">
                <c:choose>
                <c:when test="${not empty requestScope.errors['first name']}">
                       placeholder="${requestScope.errors['first name']}" ;
                       style="background-color:#ffff66">
                </c:when>
                <c:otherwise>
                    value="${requestScope.userDTO.firstName}">
                </c:otherwise>
                </c:choose>
                </c:if>
                <c:if test="${requestScope.errors == null}">placeholder="<fmt:message key="first.name"
                                                                                      bundle="${lang}"/>"></c:if>
                <input type="text" class="text" name="last name" id="last_name"
                <c:if test="${requestScope.errors != null}">
                <c:choose>
                <c:when test="${not empty requestScope.errors['last name']}">
                       placeholder="${requestScope.errors['last name']}" ;
                       style="background-color:#ffff66">
                </c:when>
                <c:otherwise>
                    value="${requestScope.userDTO.lastName}">
                </c:otherwise>
                </c:choose>
                </c:if>
                <c:if test="${requestScope.errors == null}">placeholder="<fmt:message key="last.name" bundle="${lang}"/>"></c:if>
                <div class="register-check-box">
                    <div class="check">
                        <label class="checkbox"><input type="checkbox" name="checkbox"><i> </i><fmt:message
                                key="subscrie" bundle="${lang}"/></label>
                    </div>
                </div>
                <br>
                <input type="file" name="avatar" multiple="true"/>
                <h6><fmt:message key="login.info" bundle="${lang}"/></h6>
                <input type="text" class="text" name="e-mail" id="email"
                <c:if test="${requestScope.errors != null}">
                <c:choose>
                <c:when test="${not empty requestScope.errors['e-mail']}">
                       placeholder="${requestScope.errors['e-mail']}" ;
                       style="background-color:#ffff66">
                </c:when>
                <c:otherwise>
                    value="${requestScope.userDTO.email}">
                </c:otherwise>
                </c:choose>
                </c:if>
                <c:if test="${requestScope.errors == null}">placeholder="<fmt:message key="email" bundle="${lang}"/>..."></c:if>
                <input type="password" class="text" name="password" id="pass"
                <c:if test="${requestScope.errors != null}">
                <c:choose>
                <c:when test="${not empty requestScope.errors['password']}">
                       placeholder="${requestScope.errors['password']}" ;
                       style="background-color:#ffff66">
                </c:when>
                <c:otherwise>
                    placeholder="<fmt:message key="password" bundle="${lang}"/>">
                </c:otherwise>
                </c:choose>
                </c:if>
                <c:if test="${requestScope.errors == null}">placeholder="<fmt:message key="password" bundle="${lang}"/>"></c:if>
                <input type="password" class="text" name="password confirmation" id="confirm_pass"
                <c:if test="${requestScope.errors != null}">
                <c:choose>
                <c:when test="${not empty requestScope.errors['password confirmation']}">
                       placeholder="${requestScope.errors['password confirmation']}" ;
                       style="background-color:#ffff66">
                </c:when>
                <c:otherwise>
                    placeholder="<fmt:message key="password.confirmation" bundle="${lang}"/>">
                </c:otherwise>
                </c:choose>
                </c:if>
                <c:if test="${requestScope.errors == null}">placeholder="<fmt:message key="password.confirmation"
                                                                                      bundle="${lang}"/>"></c:if>
                <custom:captcha/>
                <div class="register-check-box">
                    <div class="check">
                        <label class="checkbox"><input type="checkbox" name="checkbox"><i> </i><fmt:message
                                key="terms.and.conditions" bundle="${lang}"/> </label>
                    </div>
                </div>
                <input type="submit" id="register" value="<fmt:message key="register" bundle="${lang}"/>"
                       name="register"/>
            </form>
        </div>
    </div>
</div>
<!-- //register -->
<!-- //footer -->
<div class="footer">
    <div class="container">
        <div class="w3_footer_grids">
            <div class="col-md-3 w3_footer_grid">
                <h3>Contact</h3>
                <ul class="address">
                    <li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>1234k Avenue, 4th block, <span>New York City.</span>
                    </li>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a
                            href="mailto:info@example.com">info@example.com</a></li>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+1234 567 567</li>
                </ul>
            </div>
            <div class="col-md-3 w3_footer_grid">
                <h3>Information</h3>
                <ul class="info">
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="">About Us</a></li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="">Contact Us</a></li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="">Short Codes</a>
                    </li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="">FAQ's</a></li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="products">Special
                        Products</a></li>
                </ul>
            </div>
            <div class="col-md-3 w3_footer_grid">
                <h3>Profile</h3>
                <ul class="info">
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="products">Store</a></li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="">My Cart</a></li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="login">Login</a></li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="registration">Create Account</a>
                    </li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="footer-copy">
        <div class="container">
            <p>© 2017 Super Market. All rights reserved | Design by <a href="http://w3layouts.com/">W3layouts</a></p>
        </div>
    </div>
</div>
<!-- //footer -->
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
<!-- top-header and slider -->
<!-- here stars scrolling icon -->
<script type="text/javascript">
    $(document).ready(function () {
        $().UItoTop({easingType: 'easeOutQuart'});
    });
</script>
<!-- //here ends scrolling icon -->
<!-- main slider-banner -->
<script src="js/skdslider.min.js"></script>
<link href="css/skdslider.css" rel="stylesheet">
<script type="text/javascript">
    jQuery(document).ready(function () {
        jQuery('#demo1').skdslider({
            'delay': 5000,
            'animationSpeed': 2000,
            'showNextPrev': true,
            'showPlayButton': true,
            'autoSlide': true,
            'animationType': 'fading'
        });
        jQuery('#responsive').change(function () {
            $('#responsive_wrapper').width(jQuery(this).val());
        });
    });

</script>
<!-- //main slider-banner -->
</body>
</html>
