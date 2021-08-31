<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
   <p>Hello, ${person.login}</p>
    <p>Choose what interests you</p>


        <p>
          <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork5-1.0-SNAPSHOT/Message' ;" value ="View Messages"></input></p>
        </p>

        <p>
             <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork5-1.0-SNAPSHOT/chats' ;" value ="Send messages"></input></p>
        </p>

        <p>
             <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork5-1.0-SNAPSHOT/user' ;" value ="Data about all users"></input></p>
        </p>

         <p>
                      <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork5-1.0-SNAPSHOT/about' ;" value ="Application start time"></input></p>
         </p>



         <p>
              <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork5-1.0-SNAPSHOT/logout' ;" value ="Exit"></input></p>
        </p>


</body>
</html>
