<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
          <c:forEach var="item" items="${str}">
           <br>
               <a   href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/department/page?id=${item.id}">${item.name}</a>
       </c:forEach>
</body>
</html>
