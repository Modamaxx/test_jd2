<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <p>Welcome</p>
   <form method="POST" action="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/api/department/generation">
        <p><input type="submit"  value ="Generating 5 departments"></input></p>
   </form>

   <form method="POST" action="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/api/position/generation">
         <p><input type="submit"  value ="Generating 10 Position"></input></p>
   </form>

   <form method="POST" action="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/api/employer/generation">
         <p><input type="submit" value ="Generating 10_000 Employers"></input></p>
   </form>

 <form method="GET" action="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer/authorization">
         <p><input type="submit" value ="Enter"></input></p>
 </form>

<form method="GET" action="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer/registration">
      <p><input type="submit" value ="Register"></input></p>
</form>


</body>
</html>
