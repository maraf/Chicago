<%@tag import="cz.cvut.fel.fiserm5.javabeans.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<fmt:bundle basename="chicago">
<h2><fmt:message key="playerInfo.title" /></h2>
<table>
    <tr>
        <td><fmt:message key="playerInfo.name" />: </td>
        <td><strong>${aktualniHrac.name}</strong></td>
    </tr>
    <tr>
        <td><fmt:message key="playerInfo.score" />: </td>
        <td><strong>${aktualniHrac.score}</strong></td>
    </tr>
    <tr>
        <td><fmt:message key="playerInfo.round" />: </td>
        <td>${aktualniHrac.round + 1}</td>
    </tr>
    <tr>
        <td><fmt:message key="playerInfo.inRound" />: </td>
        <td>${aktualniHrac.inThisRound + 1}</td>
    </tr>
</table>
<form name="logout" method="post" action="${action}">
    <input type="hidden" name="token" value="${token}" />
    <label for="logout"><fmt:message key="playerInfo.logout" /> </label>
    <input id="logout" type="submit" name="logout" value="Logout" />
</form>
</fmt:bundle>