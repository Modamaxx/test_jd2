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
   <form  method="POST">
 <table>

     <tr>
         <td>Login:</td>
         <td><input type="text" name="login"></td>
     </tr>

     <tr>
         <td>Password:</td>
         <td><input type="password" name="password"></td>
      </tr>

        <p><input type="submit" value="Login"> </input></p>
        <p><input type="button" onclick="location.href='http://localhost:8080/learning-1.0.0/views/StartApp.jsp' ;" value ="Return"></input></p>
    </table>

   </form>
</body>
</html>
