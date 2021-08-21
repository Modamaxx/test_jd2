<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
   <p>Hello, ${person.login}</p>
    <p>Choose what interests you</p>


        <p>
          <p><input type="button" onclick="location.href='http://localhost:8080/learning-1.0.0/Message' ;" value ="View Messages"></input></p>
        </p>

        <p>
          <p><input type="button" onclick="location.href='http://localhost:8080/learning-1.0.0/chats' ;" value ="Send messages"></input></p>
        </p>

        <p>
           <p><input type="button" onclick="location.href='http://localhost:8080/learning-1.0.0/views/StartApp.jsp' ;" value ="Exit"></input></p>
        </p>
</body>
</html>
