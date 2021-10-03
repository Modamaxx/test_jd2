<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
   <p>All employers</p>

        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?limit=50&page=${page}">50</a>
        <br>
        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?limit=100&page=${page}">100</a>
        <br>
        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?limit=150&page=${page}">150</a>
        <br>
        <br>

       <form  method="GET" >
                <td>Enter Name:</td>
                <td><input name="SearchName"></td>

                <td>Enter Salary:</td>
                <td><input name="Salary"></td>

                <tr>
                     <select name="Sort">
                             <option> ascending </option>
                             <option> descending </option>
                     </select>
                </tr>

                <td> <input type="hidden" name="limit" value="${limit}"><br>
                <td> <input type="hidden" name="page" value="${page}"><br>

                <p><input type="submit" value="Search"> </input></p>
       </form>

       <c:forEach var="item" items="${employers}">
        <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/signIn?id=${item.id}">${item.name}</a>
           <br>
       </c:forEach>

        <c:if test="${page != null}">

                <c:if test="${page != 1}">
                    <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?limit=${limit}&page=${page-1}"> << </a>
                    <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?limit=${limit}&page=${1}">1</a>
                </c:if>
            <c:forEach begin="${page}" end="${page+10}" var="i">
                <c:if test="${i<=pageCount}">
                    <c:choose>
                         <c:when test="${page eq i}">
                              <td>${i}</td>
                         </c:when>
                             <c:otherwise>
                                <td><a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?limit=${limit}&page=${i}">${i}</a></td>
                             </c:otherwise>
                    </c:choose>
                </c:if>
            </c:forEach>
                <c:if test="${page != pageCount}">
                    <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?limit=${limit}&page=${page+1}"> >> </a>
                    <a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer?limit=${limit}&page=${pageCount}"> ${pageCount} </a>
                </c:if>
        </c:if>
</body>
</html>
