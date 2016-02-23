<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@page session="true"%>
<html>
<head>
	<link href="<c:url value='/resources/css/admin.css'/>" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<c:url value='/resources/js/login/login.js'/>"></script>
</head>


<body>
  <div id="field">
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<a href = "<c:url value='/work'/>"> PREV </a>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>

		<form  name='loginForm' action="<c:url value='/register' />" method='POST' onSubmit="return checkPw(this)">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<table>
				<tr>  <td> <input type="text" onFocus="if(this.value=='Логин')this.value=''"   required class='text_input' name='username' value="Логин"> </td> </tr>
				<tr>  <td> <input type="password" onFocus="if(this.value=='Пароль')this.value=''"   required class='text_input' name='pasword' value="Пароль" > </td> </tr>
				<tr>  <td> <input type="password" onFocus="if(this.value=='Повторите пароль')this.value=''"   required class='text_input' name='repeadPassword' value="Повторите пароль" > </td> </tr>
				<p>Выберите роль:</p>
				<p><select name = "roles"  required >
					<option ></option>
					<option value="Admin">Админ</option>
					<option value="Superadmin">Суперадмин</option>
				</select></p>
				</table>
			<div class="login-button"> <input  name="submit" type="submit" value="submit" /> </div>



		</form>


	</c:if>



</div>
</body>
</html>