<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>Welcome ${employer.name} </p>
    <p>Your data  ${employer.salary} </p>
    <p> ${employer.department.name} </p>
    <p> ${employer.position.name} </p>

<a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/CardPosition">Departments</a>
<a href="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/CardEmployers">Employers</a>
<select>

  <option>Employers</option>
  <option>Position</option>
</select>

</body>
</html>
