<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<select id='lang'><script>locales("${requestScope.current_locale}", "${requestScope.locales}");</script></select>
