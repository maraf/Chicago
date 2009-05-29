<%@tag import="cz.cvut.fel.fiserm5.javabeans.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<fmt:bundle basename="chicago">
<h2><fmt:message key="game.title" /></h2>
<c:if test="${(aktualniHrac.inThisRound == 2)}">
    <c:choose>
        <c:when test="${(aktualniHrac.round == 6)}" >
            <strong><fmt:message key="game.save1" /></strong>
        </c:when>
        <c:otherwise>
            <strong><fmt:message key="game.save2" /></strong>
        </c:otherwise>
    </c:choose>
</c:if>
<form name="game-form" method="post" action="${action}">
    <input type="hidden" name="token" value="${token}" />
    <c:set var="i" value="0" />
    <c:forEach var="number" items="${aktualniHrac.numbers}" varStatus="status">
        <div class="cube">
            <div class="kostka value-${number}"></div>
            <label for="kostka${i + 1}"><b><fmt:message key="game.cube" /> ${i + 1}</b>, <fmt:message key="game.value" />: <b>${number}</b></label>
            <input id="kostka${i + 1}" type="checkbox" name="kostka${i + 1}"<c:if test="${(aktualniHrac.cubes[i])}"> checked="checked"</c:if> />
        </div>
        <c:set var="i" value="${i + 1}" />
    </c:forEach>
    <div class="clear"></div>
    <div class="submit">
        <label for="hazej"><fmt:message key="game.confirm1" /> </label>
        <input id="hazej" type="submit" name="hazej" value="<fmt:message key="game.confirm2" />" />
    </div>
</form>
</fmt:bundle>