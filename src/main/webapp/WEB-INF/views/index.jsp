<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>checkBooks</title>
    <link href="<c:url value='/resources/css/book/multibox.css'/>" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/layout.css'/>" type="text/css" media="all"/>
    <link rel='StyleSheet' href="<c:url value='/resources/css/login.css'/>" type='text/css'>
    <link rel='StyleSheet' href="<c:url value='/resources/css/index.css'/>" type='text/css'>


</head>
<body>
<div id="login_container">
    <div id="form_container">
           <form action="<c:url value='/viewAllPage'/>">
               <label id="label"><b>Введите ID</b></label>
               <input type="text" name="bookId"   required class='text_input' >
               <input type="image" src="<c:url value='/resources/images/find.png'/>"  id="index" >
               <a href = "<c:url value='/report'/>"><img src="<c:url value='/resources/images/report.png'/>" id="report"> </a>
               <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
           </form>
    </div>
    </div>
</body>
</html>
