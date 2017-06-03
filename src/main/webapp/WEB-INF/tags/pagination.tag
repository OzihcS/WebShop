<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty requestScope.products}">
  <nav class="numbering">
    <ul class="pagination paging">
      <c:choose>
        <c:when test="${(requestScope.pages) == 1}">
          <li><a href="#" onclick="setPagination(1);">1<span class="sr-only"></span></a></li>
        </c:when>
        <c:when test="${(requestScope.pages) == 2}">
          <li><a  href="#" onclick="setPagination(1);">1<span class="sr-only"></span></a></li>
          <li><a onclick="setPagination(2);">2<span class="sr-only"></span></a></li>
        </c:when>
        <c:when test="${(requestScope.pages) == 3}">
          <li><a  href="#" onclick="setPagination(1);">1<span class="sr-only"></span></a></li>
          <li><a  href="#" onclick='setPagination(2);'>2<span class="sr-only"></span></a></li>
          <li><a  href="#" onclick='setPagination(3);'>3<span class="sr-only"></span></a></li>
        </c:when>
        <c:otherwise>
          <c:choose>
            <c:when test="${requestScope.currentPage -1 == 0}">
            </c:when>
            <c:when test="${requestScope.currentPage -2 == 0}">
              <li><a onclick="setPagination(1)">1<span class="sr-only"></span></a></li>
            </c:when>
            <c:otherwise>
              <li><a onclick="setPagination(1)">1<span class="sr-only"></span></a></li>
              <li><a>...<span class="sr-only"></span></a></li>
              <li><a onclick='setPagination(${requestScope.currentPage-1})'>${requestScope.currentPage-1}<span class="sr-only"></span></a></li>
            </c:otherwise>
          </c:choose>


          <li><a onclick='setPagination(${requestScope.currentPage})'>${requestScope.currentPage}<span class="sr-only"></span></a></li>

          <c:choose>
            <c:when test="${requestScope.currentPage + 2 == requestScope.pages}">
              <li><a onclick='setPagination(${requestScope.currentPage+1})'>${requestScope.currentPage+1}<span class="sr-only"></span></a></li>
              <li><a onclick='setPagination(${requestScope.pages})'>${requestScope.pages}<span class="sr-only"></span></a></li>
            </c:when>
            <c:when test="${requestScope.currentPage + 1 == requestScope.pages}">
              <li><a onclick='setPagination(${requestScope.pages})'>${requestScope.pages}<span class="sr-only"></span></a></li>
            </c:when>
              <c:when test="${requestScope.currentPage == requestScope.pages}"></c:when>
            <c:otherwise>
              <li><a onclick='setPagination(${requestScope.currentPage+1})'>${requestScope.currentPage+1}<span class="sr-only"></span></a></li>
              <li><a>...<span class="sr-only"></span></a></li>
              <li><a onclick='setPagination(${requestScope.pages})'>${requestScope.pages}<span class="sr-only"></span></a></li>
            </c:otherwise>
          </c:choose>
        </c:otherwise>
      </c:choose>
</ul>
</nav>
</c:if>
