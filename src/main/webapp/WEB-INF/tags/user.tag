<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty sessionScope.currentUser}">
    <h2>User profile</h2>
    <div class="login-form-grids animated wow slideInUp" data-wow-delay=".5s">
        <img src="avatar">
        <h3>First name: ${sessionScope.currentUser.firstName}</h3>
        <h3>Last name: ${sessionScope.currentUser.lastName}</h3>
        <h3>Email address: ${sessionScope.currentUser.email}</h3>
        <div class="register-home">
            <a href="logout">Logout</a>
        </div>
    </div>
</c:if>
<c:if test="${empty sessionScope.currentUser}">
    <h2>Login Form</h2>
    <div class="login-form-grids animated wow slideInUp" data-wow-delay=".5s">
        <form action="login" method="post">
            <input type="email" name="e-mail" id="e-mail" placeholder="Email Address" required=" ">
            <input type="password" name="password" id="password" placeholder="Password" required=" ">
            <div class="forgot">
                <a href="#">Forgot Password?</a>
            </div>
            <input type="submit" value="Login">
        </form>
    </div>
    <h4>For New People</h4>
    <p><a href="registered.html">Register Here</a> (Or) go back to <a href="index.html">Home<span
            class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></p>
</c:if>
