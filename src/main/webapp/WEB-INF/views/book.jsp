<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Просмотр книг</title>


      <%--popups--%>
    <link href="<c:url value='/resources/css/layout.css'/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value='/resources/css/modal.css'/>" rel="stylesheet" type="text/css" />




                    <%-- multibox  --%>

   <link href="<c:url value='/resources/css/book/multibox.css'/>" rel="stylesheet" type="text/css"/>
  <link rel="stylesheet" href="<c:url value='/resources/css/book/ie6.css'/>" type="text/css" media="all"/>
 <script type="text/javascript" src="<c:url value='/resources/js/book/mootools.js'/>"></script>
 <script type="text/javascript" src="<c:url value='/resources/js/book/overlay.js'/>"></script>
 <script type="text/javascript" src="<c:url value='/resources/js/book/multibox.js'/>"></script>



 <style type="text/css">

     #htmlElement {
         padding: 40px;
         background-color: #000;
     }

 </style>


</head>
<body>

<div id="container">



 <div id="example">
   <!--  <br><br><br><br><br><br><br><br>-->
     <div id="header" align="center"> <img src="<c:url value='/resources/images/header_bg.png'/>"/> </div>
     <div align="center"><a href="<c:url value='/menu?bookId=${bookId}'/>" align="center"><img src="<c:url value='/resources/images/main_menu.png'/>"/></a></div>
     <br>
     <div align="center" > <h2  id="countPage" > ${countPage} страниц </h2></div>
     <br>

     <div align=center>

         <c:forEach items="${pageList}" var="pageCount">
             <a href="<c:url value='/img_${bookId}?pageId=${pageCount}'/>" id="mb1" class="mb" title="Рисунок" target="_blank"><img
                     src="<c:url value='/img_${bookId}?pageId=${pageCount}'/>" alt="" border="0" height="500" width="300"/></a>

             <div class="multiBoxDesc mb1">Здесь будет описание фотографии &quot;Рисунок&quot;</div>
         </c:forEach>
         <br>
         <c:if test="${nextPage - 1 > 0}">
         <a href="<c:url value='/viewAllPage?bookId=${bookId}&page=${nextPage - 2}'/>"><img src="<c:url value='/resources/images/book/left.png'/>"/> <a/>
         </c:if>
         <c:if test="${!check}">
         <a href="#popup_fail" id="login_pop"><img src="<c:url value='/resources/images/scan.png'/>"/></a>
         <a href="<c:url value='/viewAllPage?bookId=${bookId}&page=${nextPage}'/>"><img src="<c:url value='/resources/images/next.png'/>"/> <a/>
             </c:if>

             <c:if test="${check}">
             <a href="#popup_good"><img src="<c:url value='/resources/images/ok.png'/>"/></a>
             <a href="#popup_fail" id="login_pop"><img src="<c:url value='/resources/images/scan.png'/>"/></a>
             </c:if>




     </div>

     <!-- popup form #1 -->
     <a href="#x" class="overlay" id="popup_fail"></a>
     <div class="popup">
         <h2>Причина:</h2>

         <form method="post" action="<c:url value='/fail?bookId=${bookId}'/>">
            <input type="text" id="login" value="" name="desc" size="60" required />
             <h2>Перевірив:</h2>
             <select name="worker" >

               <c:forEach items="${listWorkers}" var="worker" varStatus="loop">
                 <option value=${loop.index}> ${worker}</option>
               </c:forEach>
                   </select>

         <input type="submit" value="Okay" />
         <a class="close" href="#close"></a>

             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
             </form>
         </div>

     <a href="#x" class="overlay" id="popup_good"></a>
     <div class="popup">
         <h2>Примітка:</h2>

         <form method="post" action="<c:url value='/good?bookId=${bookId}'/>">
             <input type="text"  value="" name="desc" size="60"  />
             <h2>Перевірив:</h2>
             <select name="worker" >

                 <c:forEach items="${listWorkers}" var="worker" varStatus="loop">
                     <option value=${loop.index}> ${worker}</option>
                 </c:forEach>
             </select>

             <input type="submit" value="Okay" />
             <a class="close" href="#close"></a>
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
         </form>



     </div>


     </div>





     <script type="text/javascript">
         var box = {};
         window.addEvent('domready', function () {
             box = new MultiBox('mb', {descClassName: 'multiBoxDesc', useOverlay: true});
         });
     </script>

 </div>
</div>
</body>

</html>
