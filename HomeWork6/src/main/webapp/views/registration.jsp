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
                 <%   out.println("Remember this is your ID number="+id+"") ;%>
             </c:if>



               <p>Complete the registration process</p>
  <form id="2" method="POST" action="http://localhost:8080/HomeWork6-1.0-SNAPSHOT/api/employer/registration">
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
                     Department name:
                      <select name="department.name">
                         <c:forEach items="${strDepartments}" var="item" >
                            <option> ${item} </option>
                         </c:forEach>
                      </select>
                 </tr>

                 <tr>
                     Position name:
                     <select name="position.name">
                       <c:forEach items="${strPositions}" var="item" >
                             <option> ${item} </option>
                        </c:forEach>
                     </select>
                 </tr>

                   <p><input type="submit" value="registration"> </input></p>
                   <p><input type="button" onclick="location.href='http://localhost:8080/HomeWork6-1.0-SNAPSHOT/startApp' ;" value ="Return"></input></p>
               </table>
  </form>


             <script type="text/javascript">
               			document.getElementById('2').addEventListener('submit', submitForm);

               			function submitForm(event) {
               			    // Отменяем стандартное поведение браузера с отправкой формы
               			    event.preventDefault();
               			    // event.target — это HTML-элемент form
               			    let formData = new FormData(event.target);
               			    // Собираем данные формы в объект
               			    let obj = {};
               			    formData.forEach(
               			    	(value, key) => {
               						if(key.includes('.')){
               							var partName = key.split(".");
               			    			obj[partName[0]] = {};
               			    			obj[partName[0]][partName[1]] = value;
               			    		} else {
               							obj[key] = value;
               			    		}
               			    	}
               			    );
               			    // Собираем запрос к серверу
               			    let request = new Request(event.target.action, {
               			        method: 'POST',
               			        body: JSON.stringify(obj),
               			        headers: {
               			            'Content-Type': 'application/json',
               			        },
               			    });
               			    // Отправляем (асинхронно!)
               			    fetch(request).then(
               			        function(response) {
               			            // Запрос успешно выполнен
               			            console.log(response);
               			            // return response.json() и так далее см. документацию
               			        },
               			        function(error) {
               			            // Запрос не получилось отправить
               			            console.error(error);
               			        }
               			    );
               			    // Код после fetch выполнится ПЕРЕД получением ответа
               			    // на запрос, потому что запрос выполняется асинхронно,
               			    // отдельно от основного кода
               			    console.log('Запрос отправляется');
               			}
             </script>



            </body>
            </html>
