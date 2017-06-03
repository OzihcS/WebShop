<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty requestScope.products}">
<div class="products-right-grid">
    <div class="products-right-grids">
        <div class="sorting-left">
            <select form="filters" id="country1" name="grid" onchange="change_country(this.value)" class="frm-field required sect">
              <c:choose>
                <c:when test="${requestScope.paramList['grid'] == 6}">
                  <option value="3">Item on page 3</option>
                  <option value="6"selected>Item on page 6</option>
                  <option value="9">Item on page 9</option>
                </c:when>
                <c:when test="${requestScope.paramList['grid'] == 9}">
                  <option value="3">Item on page 3</option>
                  <option value="6">Item on page 6</option>
                  <option value="9"selected>Item on page 9</option>
                </c:when>
                <c:otherwise>
                  <option value="3" selected>Item on page 3</option>
                  <option value="6">Item on page 6</option>
                  <option value="9">Item on page 9</option>
                </c:otherwise>
              </c:choose>
            </select>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
</c:if>
