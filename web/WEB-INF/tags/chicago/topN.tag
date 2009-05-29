<%--

    Vypis hi-scores

--%>
<%@tag import="java.util.*, cz.cvut.fel.fiserm5.javabeans.Hrac, cz.cvut.fel.fiserm5.tools.HracComparator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@attribute name="n"%>

<fmt:bundle basename="chicago">
<h2><fmt:message key="topN.title" /></h2>
<c:if test="${(errorMessage != null)}">
    <h4 class="error">${errorMessage}</h4>
</c:if>
<form name="topn" method="post" action="${action}">
    <input type="hidden" name="token" value="${token}" />
    <label for="topn"><fmt:message key="topN.resultCount" />: </label>
    <input id="topn" type="text" name="howMany" size="3" value="" />
    <input type="submit" name="topn" value="Uloz" />
</form>
<c:set var="i" scope="request" value="0"/>
<table class="score-list">
    <thead>
        <tr>
            <th><fmt:message key="topN.position" /></th>
            <th><fmt:message key="playerInfo.name" /></th>
            <th><fmt:message key="playerInfo.score" /></th>
        </tr>
    </thead>
    <c:forEach var="hrac" items="${hraci}" varStatus="status">
        <c:if test="${(i < n)}">
            <c:choose>
                <c:when test="${((i % 2) == 0)}" >
                    <tr class="idle">
                </c:when>
                <c:otherwise>
                    <tr class="even">
                    </c:otherwise>
                </c:choose>
                <td>${i + 1}.</td>
                <td>${hrac.name}</td>
                <td>${hrac.score} b</td>
            </tr>
        </c:if>
        <c:set var="i" scope="request" value="${i + 1}"/>
    </c:forEach>
</table>
</fmt:bundle>