<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>login</title>

    <link rel='StyleSheet' href="<c:url value='/resources/css/login.css'/>" type='text/css'>
    <link rel='StyleSheet' href="<c:url value='/resources/css/index.css'/>" type='text/css'>


</head>
<body>
<div id="login_container">
    <div id="form_container">

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>

           <form name='loginForm' action="<c:url value='/j_spring_security_check' /> " method='POST'>

            <table>
               <tr>  <td> <input type="text" onFocus="if(this.value=='Логин')this.value=''"   required class='text_input' name='username' value="Логин"> </td> </tr>
               <tr>  <td> <input type="password" onFocus="if(this.value=='Пароль')this.value=''"   required class='text_input' name='password' value="Пароль" > </td> </tr>
            </table>
               <div class="login-button"> <input  name="submit" type="submit" value="submit" /> </div>
               <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

           </form>
    </div>
    </div>
</body>
</html>
