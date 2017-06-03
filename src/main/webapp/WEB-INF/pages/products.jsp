<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>
<html>
<head>
    <title>Super Market | All products</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Super Market Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
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
    <!-- start-smoth-scrolling -->
</head>
<body>
<!-- header -->
<div class="agileits_header">
    <div class="container">
        <div class="w3l_offers">
            <p>SALE UP TO 70% OFF. USE CODE "SALE70%" . <a href="products">SHOP NOW</a></p>
        </div>
        <div class="agile-login">
            <ul>
                <li><a href="registration"> Create Account </a></li>
                <li><a href="login">Login</a></li>
                <li><a href="logout">Logout</a></li>
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
                <li><i class="fa fa-phone" aria-hidden="true"></i>Order online or call us : (+0123) 234 567</li>
            </ul>
        </div>
        <div class="w3ls_logo_products_left">
            <h1><a href="index.html">super Market</a></h1>
        </div>
        <div class="w3l_search">
            <form action="#" method="post">
                <input type="search" name="Search" placeholder="Search for a Product..." required="">
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
            <div class="navbar-header nav_2">
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
                    <li class="active"><a href="index.html" class="act">Home</a></li>
                    <li><a href="products">All products</a></li>
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
            <li><a href="index.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
            <li class="active">All products</li>
        </ol>
    </div>
</div>
<!-- //breadcrumbs -->
<!--- gourmet --->
<div class="products">
    <div class="container">
        <div class="col-md-4 products-left">
            <div class="categories">
                <h2>FILTERS</h2>
                <ul class="cate">
                    <form class="filter-form" action="products" id="filters" name="filters" method="get">
                        <div class="categories">
                            <h3>Categories</h3>
                            <c:if test="${not empty requestScope.paramList['category']}">
                                <c:if test="${not empty requestScope.paramList['category'][0]}">
                                    <label>Beer</label><input type="checkbox" name='category' value="BEER" checked></br>
                                </c:if>
                                <c:if test="${empty requestScope.paramList['category'][0]}">
                                    <label>Beer</label><input type="checkbox" name='category' value="BEER"></br>
                                </c:if>

                                <c:if test="${not empty requestScope.paramList['category'][2]}">
                                    <label>Nuts</label><input type="checkbox" name='category' value="NUTS" checked></br>
                                </c:if>
                                <c:if test="${empty requestScope.paramList['category'][2]}">
                                    <label>Nuts</label><input type="checkbox" name='category' value="NUTS"></br>
                                </c:if>

                                <c:if test="${not empty requestScope.paramList['category'][1]}">
                                    <label>Chips</label><input type="checkbox" name='category' value="CHIPS" checked></br>
                                </c:if>
                                <c:if test="${empty requestScope.paramList['category'][1]}">
                                    <label>Chips</label><input type="checkbox" name='category' value="CHIPS"></br>
                                </c:if>
                            </c:if>
                            <c:if test="${empty requestScope.paramList['category']}">
                                <label>Beer</label><input type="checkbox" name='category' value="BEER"></br>
                                <label>Nuts</label><input type="checkbox" name='category' value="NUTS"></br>
                                <label>Chips</label><input type="checkbox" name='category' value="CHIPS"></br>
                            </c:if>
                        </div>
                        <c:if test="${not empty requestScope.paramList}">
                            <c:if test="${not empty requestScope.paramList['name']}">
                                <input type="text" name="name" placeholder="Product name..."
                                       value="${requestScope.paramList['name']}">
                            </c:if>
                            <c:if test="${empty requestScope.paramList['name']}">
                                <input type="text" name="name" placeholder="Product name...">
                            </c:if>
                            <c:if test="${not empty requestScope.paramList['manufacturer']}">
                                <input type="text" name="manufacturer" placeholder="Product manufacturer..."
                                       value="${requestScope.paramList['manufacturer']}">
                            </c:if>
                            <c:if test="${empty requestScope.paramList['manufacturer']}">
                                <input type="text" name="manufacturer" placeholder="Product manufacturer...">
                            </c:if>

                            <c:if test="${not empty requestScope.paramList['min_price']}">
                                <input type="text" name="min_price" placeholder="Min price..."
                                       value="${requestScope.paramList['min_price']}">
                            </c:if>
                            <c:if test="${empty requestScope.paramList['min_price']}">
                                <input type="text" name="min_price" placeholder="Min price...">
                            </c:if>

                            <c:if test="${not empty requestScope.paramList['max_price']}">
                                <input type="text" name="max_price" placeholder="Max price..."
                                       value="${requestScope.paramList['max_price']}">
                            </c:if>
                            <c:if test="${empty requestScope.paramList['max_price']}">
                                <input type="text" name="max_price" placeholder="Max price...">
                            </c:if>
                        </c:if>

                        <select name="order" id="country1">
                            <c:choose>
                                <c:when test="${(not empty requestScope.order) && requestScope.order == 'name DESC'}">
                                    <option value="price ASC">price up</option>
                                    <option value="price DESC">price down</option>
                                    <option value="name ASC">name up</option>
                                    <option selected value="name DESC">name down</option>
                                </c:when>
                                <c:when test="${(not empty requestScope.order) && requestScope.order == 'price ASC'}">
                                    <option selected value="price ASC">price up</option>
                                    <option value="price DESC">price down</option>
                                    <option value="name ASC">name up</option>
                                    <option value="name DESC">name down</option>
                                </c:when>
                                <c:when test="${(not empty requestScope.order) && requestScope.order ==  'price DESC'}">
                                    <option value="price ASC">price up</option>
                                    <option selected value="price DESC">price down</option>
                                    <option value="name ASC">name up</option>
                                    <option value="name DESC">name down</option>
                                </c:when>
                                <c:when test="${(not empty requestScope.order) && requestScope.order ==  'name ASC'}">
                                    <option value="price ASC">price up</option>
                                    <option value="price DESC">price down</option>
                                    <option selected value="name ASC">name up</option>
                                    <option value="name DESC">name down</option>
                                </c:when>
                                <c:otherwise>
                                    <option selected disabled>Sort by</option>
                                    <option value="price ASC">price up</option>
                                    <option value="price DESC">price down</option>
                                    <option value="name ASC">name up</option>
                                    <option value="name DESC">name down</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                        <input type="hidden" id="pagination" name="pagination" value="1">
                        <input type="button" onclick="clearFilterParams()" value="Clear">
                        <input type="submit" value="Find">
                    </form>
                </ul>
            </div>
        </div>
        <div class="col-md-8 products-right">
            <custom:grid/>
            <div class="agile_top_brands_grids">
                <c:if test="${empty requestScope.products}">
                    <div style="text-align: center;"><h2>Products not found</h2></div>
                </c:if>
                <c:if test="${not empty requestScope.products}">
                    <c:forEach items="${requestScope.products}" var="item">
                        <div class="col-md-4 top_brand_left">
                            <div class="hover14 column">
                                <div class="agile_top_brand_left_grid">
                                    <div class="agile_top_brand_left_grid_pos">
                                        <img src="images/offer.png" alt=" " class="img-responsive">
                                    </div>
                                    <div class="agile_top_brand_left_grid1">
                                        <figure>
                                            <div class="snipcart-item block">
                                                <div class="snipcart-thumb">
                                                    <a href="single?product=${item.id}"><img src="images/${item.image}"></a>
                                                    <p>${item.name}</p>
                                                    <h4>${item.price}</h4>
                                                </div>
                                                <div class="snipcart-details top_brand_home_details">
                                                    <form action="add" id="product_to_cart" method="post">
                                                        <input type="hidden" name="item_name" value="${item.name}">
                                                        <input type="hidden" name="amount" value="${item.price}">
                                                        <input type="button" name="submit" value="Add to cart"
                                                               class="button" onclick="add(${item.id})">
                                                    </form>
                                                </div>
                                            </div>
                                        </figure>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <div class="clearfix"></div>
        </div>
        <custom:pagination/>
    </div>
    <div class="clearfix"></div>
</div>
</div>
<!--- gourmet --->
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
                <h3>Category</h3>
                <ul class="info">
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="beer.html">Beer</a></li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="chips.html">Chips</a></li>
                    <li><i class="fa fa-arrow-right" aria-hidden="true"></i><a href="nuts.html">Nuts</a></li>
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
<div class="footer-botm">
    <div class="container">
        <div class="w3layouts-foot">
            <ul>
                <li><a href="#" class="w3_agile_facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                <li><a href="#" class="agile_twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                <li><a href="#" class="w3_agile_dribble"><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
                <li><a href="#" class="w3_agile_vimeo"><i class="fa fa-vimeo" aria-hidden="true"></i></a></li>
            </ul>
        </div>
        <div class="payment-w3ls">
            <img src="images/card.png" alt=" " class="img-responsive">
        </div>
        <div class="clearfix"></div>
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
<script>
    function setPagination(page) {
        document.getElementById('pagination').value = page;
        var url = document.URL;
        var path = 'http://localhost:8080/WebShop/products';

        if (url == path) {
            url = '?name=&manufacturer=&min_price=&max_price=&pagination=' + page + '&grid=3'
        } else {
            url = url.substring(url.indexOf('?'));
            var p = url.split('&');
            url = p[0] + '&' + p[1] + '&' + p[2] + '&' + p[3] + '&' + 'pagination=' + page + '&' + p[5];
        }
        path += url;
        window.location.replace(path);
    }
</script>

<script>
    function add(id) {
        $.ajax({
            type: "POST",
            url: "add?item_id=" + id
        });
    }
</script>
<!-- //main slider-banner -->
</body>
</html>
