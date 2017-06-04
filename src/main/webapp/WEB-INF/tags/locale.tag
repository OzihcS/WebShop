<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<select id='lang'>
    <c:choose>
        <c:when test="${requestScope.current_locale == 'ru'}">
            <option selected style="background: url(../../images/lang/ru.png)" value="ru">Russian</option>
            <option style="background: url(../../images/lang/en.png)" value="en">English</option>
        </c:when>
        <c:when test="${requestScope.current_locale == 'en'}">
            <option style="background: url(../../images/lang/ru.png)" value="ru">Russian</option>
            <option selected style="background: url(../../images/lang/en.png)" value="en">English</option>
        </c:when>
    </c:choose>
</select>
