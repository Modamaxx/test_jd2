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
    <form method="POST">
      <table>

       <tr>
           <td>Recipient:</td>
           <td><input type="text" name="recipient"></td>
       </tr>

       <tr>
           <td>Messages:</td>
           <td><input type="text" name="message"></td>
       </tr>

          <p><input type="submit" value="Send"> </input></p>
          <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork5-1.0-SNAPSHOT/views/menu.jsp' ;" value ="Return"></input></p>
      </table>
      </form>
</body>
</html>
