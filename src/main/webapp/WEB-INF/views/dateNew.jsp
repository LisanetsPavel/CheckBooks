<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 29.04.15
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel='StyleSheet' href="<c:url value='/resources/css/report.css'/>" type='text/css'>
  <title></title>

</head>
<body>
<br>

<div align="center"><a href="<c:url value="/report"/>" align="center"><img
        src="<c:url value="/resources/images/main_menu.png"/>"/></a></div>
<br>

<div align="center" id="select"><h1> Выбраная дата: ${date} ${dateStart} - ${dateEnd} </h1></div>

<table border="1" width="70%" cellpadding="5" class="table" align="center">

  <tr>
    <th>ФИО</th>
    <th>количество книг</th>
    <th>количество страниц</th>

  </tr>
  <c:forEach items="${workAndEmployees}" var="workAndEmployeesList" varStatus="loop">

    <tr>
      <th> ${workAndEmployeesList.get(0).employee.ename} </th>
      <th> ${workAndEmployeesList.size()} </th>
      <th> ${countPages.get(loop.index)} </th>

    </tr>


  </c:forEach>


</table>
<br>
<br>
<br>


<table border="1" width="70%" cellpadding="5" class="table" align="center">
  <tr>

    <th>дата</th>
    <th>book ID</th>
    <th>количество страниц</th>
  </tr>

  <c:forEach items="${workAndEmployees}" var="workAndEmployeesList">

    <tr>
      <th colspan="3"> ${workAndEmployeesList.get(0).employee.ename}   </th>
    </tr>

    <c:forEach items="${workAndEmployeesList}" var="worker">

      <tr>

        <th>${worker.edate}</th>
        <th>${worker.bookId}</th>
        <th>${worker.bookSize}</th>
      </tr>

    </c:forEach>

  </c:forEach>
</table>

</body>
</html>
