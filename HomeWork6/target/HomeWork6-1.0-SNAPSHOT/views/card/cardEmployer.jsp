<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>  ID= ${employer.id}</p>
    <p>Welcome ${employer.name} </p>
    <p>Your data  ${employer.salary} </p>
    <p> ${employer.department.name} </p>
    <p> ${employer.position.name} </p>

<a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/position">Positions</a>
<a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/employer">Employers</a>
<a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/department">Departments</a>

</body>
</html>
