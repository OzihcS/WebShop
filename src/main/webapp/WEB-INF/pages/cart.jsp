<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Super Market | Cart</title>
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
            <li class="active">Cart</li>
        </ol>
    </div>
</div>
<!-- //breadcrumbs -->
<div class="products">
    <c:if test="${empty requestScope.cart}">
        <h3>Cart is empty</h3>
    </c:if>
    <c:if test="${not empty requestScope.cart}">
        <table id="cart" class="table table-hover table-condensed">
            <thead>
            <tr>
                <th style="width:50%">Product</th>
                <th style="width:10%">Price</th>
                <th style="width:8%">Quantity</th>
                <th style="width:22%" class="text-center">Subtotal</th>
                <th style="width:10%"></th>
            </tr>
            </thead>
            <c:forEach items="${requestScope.cart}" var="item">
                <tbody id="${item.key.id}">
                <tr>
                    <td data-th="Product">
                        <div class="row">
                            <div class="col-sm-2 hidden-xs"><img src="http://placehold.it/100x100" alt="..."
                                                                 class="img-responsive"/></div>
                            <div class="col-sm-10">
                                <h4 class="nomargin">${item.key.name}</h4>
                                <p>${item.key.description}</p>
                            </div>
                        </div>
                    </td>
                    <td data-th="Price">${item.key.price}</td>
                    <td data-th="Quantity">
                        <input type="number" id="q${item.key.id}" onchange="changeQuantity(${item.key.id})"
                               class="form-control text-center" value="${item.value}">
                    </td>
                    <td data-th="Subtotal" class="text-center">${(item.key.price * item.value)}</td>
                    <td class="actions" data-th="">
                        <button onclick="remove(${item.key.id})" class="btn btn-danger btn-sm"><i
                                class="fa fa-trash-o"></i></button>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
            <tfoot>
            <tr class="visible-xs">
                <td class="text-center"><strong>Total ${requestScope.total}</strong></td>
            </tr>
            <tr>
                <td><a href="products" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a>
                </td>
                <td colspan="2" class="hidden-xs"></td>
                <td class="hidden-xs text-center"><strong>Total ${requestScope.total}</strong></td>
                <td><a href="#" class="btn btn-success btn-block" onclick="makeOrder()">Checkout <i
                        class="fa fa-angle-right"></i></a></td>
            </tr>
            </tfoot>
        </table>
    </c:if>
</div>
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
<%-- <script src="js/minicart.min.js"></script>
<script>
    // Mini Cart
    paypal.minicart.render({
        action: '#'
    });
    if (~window.location.search.indexOf('reset=true')) {
        paypal.minicart.reset();
    }

</script> --%>
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
    function remove(id) {
        $.ajax({
            type: "POST",
            url: "removeItem?item_id=" + id,
            success: function () {
                $('#' + id).remove();
            }
        });
    }
</script>

<script>
    function changeQuantity(id) {
        var quantity = $('#q' + id).val();
        $.ajax({
            type: "GET",
            url: "changeQuantity?item_id=" + id + "&quantity=" + quantity,
        });
    }
</script>
<script>
    function makeOrder() {
        $.ajax({
            type: "GET",
            url: "makeOrder",
            success: function () {
                window.location.href = "makeOrder"
            },
            error: function () {
                window.location.href = "/WebShop/login";
            }
        });
    }
</script>
<!-- //main slider-banner -->
</body>
</html>
