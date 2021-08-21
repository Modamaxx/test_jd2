<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
          <p><input type="button" onclick="location.href='http://localhost:8080/learning-1.0.0/views/menu.jsp' ;" value ="Return"></input></p>
      </table>
      </form>
</body>
</html>
