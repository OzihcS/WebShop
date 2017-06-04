<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${requestScope.current_locale}"/>
<fmt:setBundle basename="lang" var="lang"/>


<div style="text-align: center;"><img src="captcha?captchaID=${requestScope.captchaID}"></div>

<c:if test="${requestScope.useHiddenInput}">
    <input type="hidden" name="captchaID" value="${requestScope.captchaID}">
</c:if>

<input type="text" class="text" name="captcha" id="captcha"

<c:if test="${requestScope.errors != null}">
    <c:choose>
        <c:when test="${not empty requestScope.errors['captcha']}">
            placeholder="${requestScope.errors['captcha']}" ;
            style="background-color:#ffff66">
        </c:when>
        <c:otherwise>
            placeholder="<fmt:message key="captcha" bundle="${lang}"/> ">
        </c:otherwise>
    </c:choose>
</c:if>
<c:if test="${requestScope.errors == null}">placeholder="<fmt:message key="captcha" bundle="${lang}"/>"></c:if>
