<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 28.04.15
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>


    <script type="text/javascript" src="<c:url value='/resources/js/jquery-2.0.3.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/jquery.minical.plain.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/employee/calendar.js'/>"></script>

    <link rel='StyleSheet' href="<c:url value='/resources/css/report.css'/>" type='text/css'>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery.minical.plain.css'/>">
</head>

<br>
<br>
<br>
<br>
<br>

<div align="center" id="form">
    <form method="post" action="/date">
        <label>Выберете дату: </label>
        <%--<img src="/resources/images/icon_calendar.png">--%>
        <input class="edit" type="text" name="date">
        <input class="edit" type="submit" value="OK">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    </form>

    <br>


    <form method="post" action="/range">

        <label>Выберете диапазон: </label>
        <%--<img src="/resources/images/icon_calendar.png">--%>
        <input class="edit" type="text" name="dateStart">
        <input class="edit" type="text" name="dateEnd">
        <input class="edit" type="submit" value="OK">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>


</body>
</html>
