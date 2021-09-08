            <%@ page contentType="text/html;charset=UTF-8" language="java" %>
             <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <html>
            <head>
                <title>Title</title>
            </head>
            <body>
            <%
            String id =(String)request.getAttribute("id");
            %>
             <c:if test="${id!=null}">
                 <%   out.println("Remember this is your ID number="+id+"!!!!!!!") ;%>
             </c:if>

               <p>Complete the registration process</p>
               <form method="POST">
               <table>

                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name"></td>
                </tr>

                <tr>
                    <td>Salary:</td>
                    <td><input type="salary" name="salary"></td>
                </tr>
                 <tr>
                      <td> Department name:</td>
                      <td><input type="text" name="departmentName"></td>
                 </tr>

                 <tr>
                      <td>Position name:</td>
                      <td><input type="text" name="positionName"></td>
                 </tr>

                   <p><input type="submit" value="Sign up"> </input></p>
                   <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork6-1.0-SNAPSHOT/views/startApp.jsp' ;" value ="Return"></input></p>
               </table>
               </form>
            </body>
            </html>
