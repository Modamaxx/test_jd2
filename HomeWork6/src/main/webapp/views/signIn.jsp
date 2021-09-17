<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

 <%
   String error =(String)request.getAttribute("error");
 %>
    <c:if test="${error!=null}">
         <% out.println(error); %>
    </c:if>
   <p>Enter all the necessary data</p>
   <form  method="GET">

 <table>
     <tr>
         <td>Id:</td>
         <td><input type="id" name="id"></td>
     </tr>

        <p><input type="submit" value="Login"> </input></p>
        <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork6-1.0-SNAPSHOT/views/startApp.jsp' ;" value ="Return"></input></p>
    </table>

   </form>
</body>
</html>
