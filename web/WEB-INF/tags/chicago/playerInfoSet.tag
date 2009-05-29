<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<fmt:bundle basename="chicago">
<form name="setInfo" method="post" action="${action}">
    <input type="hidden" name="token" value="${token}" />
    <label for="name"><fmt:message key="playerInfo.login" />:</label>
    <input id="name" type="text" name="name" />
    <input type="submit" name="sendName" value="OK" />
</form>
</fmt:bundle>