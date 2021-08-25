<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
       <p>${person.login}</p>

   <table border="1">

   <tr>

       <c:forEach items="${users}" var="item" >
       <tr>
           <th>Login</th>
           <th>fio</th>
           <th>birthday</th>
           <th>Registration time</th>
       </tr>
        <tr>
         <td Width="20%"> ${item.login}</td>
         <td Width="20%"> ${item.fio}</td>
         <td Width="20%"> ${item.birthday}</td>
         <td Width="20%"> ${item.dateTimeSignUp}</td>
        </tr>
       </c:forEach>

   </tr>
          <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork5-1.0-SNAPSHOT/views/menu.jsp' ;" value ="Return"></input></p>
      </table>
</body>
</html>