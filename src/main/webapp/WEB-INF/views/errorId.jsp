<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>error</title>
</head>
<body>
<style type="text/css">
    .fig {
        text-align: center;
    }
</style>
<p>


<p class="fig"><img src="<c:url value="/resources/images/error/error.png"/>" width="613" height="519"></p>
<div align="center"><h1> Книги з таким ID не знайдено в базі</h1></div>
<div align="center"><a href="<c:url value="/index?bookId=${bookId}"/>" align="center"><img src="<c:url value="/resources/images/main_menu.png"/>"/></a></div>

</body>
</html>