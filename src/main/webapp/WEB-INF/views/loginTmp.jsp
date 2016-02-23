<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
	<link href="<c:url value='/resources/css/book/multibox.css'/>" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="<c:url value='/resources/css/layout.css'/>" type="text/css" media="all"/>
	<link rel='StyleSheet' href="<c:url value='/resources/css/login.css'/>" type='text/css'>
	<link rel='StyleSheet' href="<c:url value='/resources/css/index.css'/>" type='text/css'>
</head>
<body onload='document.loginForm.username.focus();'>

	<h1>Авторизация</h1>

	<div id="login_container">

		<h3>Введи логин и пароль</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>

			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' required class='text_input' name='username'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' required class='text_input' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
	</div>

</body>
</html>