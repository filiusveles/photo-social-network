document.getElementById('loginForm').addEventListener('submit', submitForm);

function submitForm(event) {
    // Отменяем стандартное поведение браузера с отправкой формы
    event.preventDefault();
    // event.target — это HTML-элемент form
    let formData = new FormData(event.target);
    // Собираем данные формы в объект
    let obj = {};
    formData.forEach((value, key) => obj[key] = value);
    // Собираем запрос к серверу
    let request = new Request(event.target.action, {
        method: 'POST',
        body: JSON.stringify(obj),
        headers: {
            'Content-Type': 'application/json',
        },
    });
    fetch(request).then(
        function(response) {
            response.json().then(function(json){
                header
                /*fetch("/" + json.nickname, {
                        method: 'GET',
                        headers: {
                            Authentication: "Bearer " + json.jwtToken,
                        }
                })
                .then(response => {
                    if(response.redirected){
                        document.location = response.url;
                    }
                }
                );*/
            });
        },
        function(error) {
            console.error(error);
        }
    );
}

