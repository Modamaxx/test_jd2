<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
   <p>All employers</p>

<table>
        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?countEmployer=50&pageNumber=${pageNumber}">50</a>
        <br>
        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?countEmployer=100&pageNumber=${pageNumber}">100</a>
        <br>
        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?countEmployer=150&pageNumber=${pageNumber}">150</a>
        <br>
        <br>


       <c:forEach var="item" items="${employers}">
        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/signIn?id=${item.id}">${item.name}</a>
           <br>
       </c:forEach>
</table>

<c:choose>
    <c:when test="${pageNumber<=2}">
         <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=1&countEmployer=${countEmployer}">1</a>
         <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=2&countEmployer=${countEmployer}">2</a>
         <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=3&countEmployer=${countEmployer}">3</a>
         <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=4&countEmployer=${countEmployer}">4</a>
         <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=5&countEmployer=${countEmployer}">5</a>
    </c:when>

    <c:otherwise>
        <c:choose>
        <c:when test="${pageNumber>=pageEnd-1}">
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=${pageEnd-4}&countEmployer=${countEmployer}">${pageEnd-4}</a>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=${pageEnd-3}&countEmployer=${countEmployer}">${pageEnd-3}</a>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=${pageEnd-2}&countEmployer=${countEmployer}">${pageEnd-2}</a>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=${pageEnd-1}&countEmployer=${countEmployer}">${pageEnd-1}</a>
             <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=${pageEnd}&countEmployer=${countEmployer}">${pageEnd}</a>
        </c:when>


        <c:otherwise>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=${pageNumber-2}&countEmployer=${countEmployer}">${pageNumber-2}</a>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=${pageNumber-1}&countEmployer=${countEmployer}">${pageNumber-1}</a>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=${pageNumber}&countEmployer=${countEmployer}">${pageNumber}</a>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=${pageNumber+1}&countEmployer=${countEmployer}">${pageNumber+1}</a>
            <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=${pageNumber+2}&countEmployer=${countEmployer}">${pageNumber+2}</a>
        </c:otherwise>
       </c:choose>

    </c:otherwise>
</c:choose>

     <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=${pageEnd}&countEmployer=${countEmployer}">end</a>
     <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?pageNumber=1&countEmployer=${countEmployer}">start</a>


</body>
</html>
