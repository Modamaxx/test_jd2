<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <p>All employers</p>

   <table>
       <c:forEach var="item" items="${employers}">
           <tr>
               <td>${item.name}</td>
                <td>${item.salary}</td>
                <td>${item.department.name}</td>
                <td>${item.position.name}</td>
           </tr>
       </c:forEach>


   </table>
</body>
</html>
