<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>report</title>
    <link rel='StyleSheet' href="<c:url value='/resources/css/index.css'/>" type='text/css'>
    <link rel='StyleSheet' href="<c:url value='/resources/css/report.css'/>" type='text/css'>

</head>
<body >

<table border="1" width="70%" cellpadding="5" id="table" align="center" >
<br>
    <div align="center"><a href="<c:url value="/index?bookId=${bookId}"/>" align="center"><img src="<c:url value="/resources/images/search.png"/>"/></a>
        <a href="<c:url value="/select"/>" align="center"><img src="<c:url value="/resources/images/employee.png"/>"/></a>
    </div>
 <br>
     <tr>
         <th><h2>Проверенные книги (ОК)</h2><h3> ${countGood} </h3></th>
         <th><h2>Непроверенные книги</h2><h3> ${countNoCheck} </h3></th>
         <th  width="70%" ><h2>Проверенные книги (ПЕРЕСКАНИРОВАТЬ)</h2><h3> ${countFail} </h3></th>
     </tr>
    <tr id="contentTr" >
    <td valign="top">  <c:forEach items="${listGood}" var="book">
     <a href="<c:url value="/viewAllPage?bookId=${book.id}"/>">   ${book.id} </a>  <br><br>
    </c:forEach></td>
    <td valign="top" ><c:forEach items="${listNoCheck}" var="book">
        <a href="<c:url value="/viewAllPage?bookId=${book.id}"/>">   ${book.id} </a>  <br><br>
    </c:forEach></td>

   <td valign="top" width="70%" >  <c:forEach items="${listFail}" var="book">

        <a href="<c:url value="/viewAllPage?bookId=${book.id}"/>"> ${book.id} причина: ${book.description}   </a>  <br><br>
    </c:forEach></td>
   </tr>
</table>

</body>
</html>
