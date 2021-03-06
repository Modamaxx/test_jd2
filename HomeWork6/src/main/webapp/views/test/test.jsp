<html>
	<body>
		<form id="benderform" action="http://localhost:8080/CRMServer/test_body" method="POST">
			<input type="text" name="name">
			<input type="text" name="salary">
			<input type="text" name="department.id">
			<input type="submit">
		</form>
		<script type="text/javascript">
			document.getElementById('benderform').addEventListener('submit', submitForm);

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