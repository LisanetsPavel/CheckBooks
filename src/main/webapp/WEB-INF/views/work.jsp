<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<link href="<c:url value='/resources/css/admin.css'/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div  id="field">
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
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
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>


	</sec:authorize>
  <div id="menu">
	<a href="/downloadGoodBooks"> DOWNLOAD_GOOD_BOOKS  </a>
	<br>
	  <a href="/moveGoodBooks"> MOVE_GOOD_BOOKS  </a>
    <br>
    <a href="/downloadFailBooks"> DOWNLOAD_FAIL_BOOKS  </a>
   </div>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
         <h2>Hello admin </h2>
	   <a href = "<c:url value='/admin'/>"> ADMIN </a>
	</sec:authorize>
</div>
</body>
</html>