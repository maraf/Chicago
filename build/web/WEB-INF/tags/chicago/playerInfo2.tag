<%@tag import="cz.cvut.fel.fiserm5.javabeans.*" %>
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
</table>
<form name="logout" method="post" action="${action}">
    <input type="hidden" name="token" value="${token}" />
    <label for="logout"><fmt:message key="playerInfo.logout" /> </label>
    <input id="logout" type="submit" name="logout" value="Logout" />
    <input type="submit" name="again" value="Hrat znovu" />
</form>
</fmt:bundle>