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

       <c:forEach items="${letters}" var="item" >
       <tr>
           <th>Date</th>
           <th>From whom</th>
           <th>Message</th>
       </tr>
        <tr>
         <td Width="20%"> ${item.data}</td>
         <td Width="20%"> ${item.from}</td>
         <td Width="60%"> ${item.message}</td>
        </tr>
       </c:forEach>

   </tr>
          <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork5-1.0-SNAPSHOT/views/menu.jsp' ;" value ="Return"></input></p>
      </table>
</body>
</html>
