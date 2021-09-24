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


<c:choose>
    <c:when test="${pageNumber<=2}">
        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/testServlet?pageNumber=1">1</a>
                      <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/testServlet?pageNumber=2">2</a>
                        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/testServlet?pageNumber=3">3</a>
                        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/testServlet?pageNumber=4">4</a>
                        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/testServlet?pageNumber=5">5</a>
    </c:when>
    <c:otherwise>
     <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/testServlet?pageNumber=${pageNumber-2}">${pageNumber-2}</a>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/testServlet?pageNumber=${pageNumber-1}">${pageNumber-1}</a>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/testServlet?pageNumber=${pageNumber}">${pageNumber}</a>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/testServlet?pageNumber=${pageNumber+1}">${pageNumber+1}</a>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/testServlet?pageNumber=${pageNumber+2}">${pageNumber+2}</a>
    </c:otherwise>
</c:choose>



</body>
</html>
