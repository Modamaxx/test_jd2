<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
        <%
          String startAppTime =(String)request.getAttribute("startAppTime");
          String storageType =(String)request.getAttribute("storageType");
        %>

   <table border="1">

   <tr>

       <tr>
           <th>Application start time</th>
           <th>Storage type</th>

       </tr>
        <tr>
         <td Width="50%"> <% out.println(startAppTime); %></td>
         <td Width="50%"> <% out.println(storageType); %></td>

        </tr>


   </tr>
          <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork5-1.0-SNAPSHOT/views/menu.jsp' ;" value ="Return"></input></p>
      </table>
</body>
</html>