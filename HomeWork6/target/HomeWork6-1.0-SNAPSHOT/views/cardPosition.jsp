<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <p>Position</p>

   <table>
       <c:forEach var="item" items="${str}">
           <tr>
               <td>${item}</td>
           </tr>
       </c:forEach>
   </table>
</body>
</html>
