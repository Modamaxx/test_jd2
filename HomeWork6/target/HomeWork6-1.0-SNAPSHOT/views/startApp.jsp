<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <p>Welcome</p>
   <form method="POST" action="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/department">
        <p><input type="submit"  value ="Generating 5 departments"></input></p>
   </form>

   <form method="POST" action="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/position">
         <p><input type="submit"  value ="Generating 10 Position"></input></p>
   </form>

   <form method="POST" action="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer">
         <p><input type="submit" value ="Generating 10_000 Employers"></input></p>
   </form>

   <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork6-1.0-SNAPSHOT/signIn' ;" value ="Enter"></input></p>
   <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork6-1.0-SNAPSHOT/signUp' ;" value ="Register"></input></p>



</body>
</html>
