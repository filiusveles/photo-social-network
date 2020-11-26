/*function autor(){
    var email = document.getElementById("username").value;
    var password = document.getElementById("password").value;
   $.ajax({
       url: "/login",
       method: 'post',
       contentType: "application/json",
       data:JSON.stringify({
            "email": email,
            "password": password
       }),
*//*       success: function(redirect){
            document.location.href=redirect;
       }*//*
       success: function(media){
            document.location.href=media.pageUrl;
            user_data(media);
       }

   });
}

function reg(){
   document.location.href="/registration";
}

function into(redirect){
    document.location.href=redirect;
}*/


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

    // Отправляем (асинхронно!)
    fetch(request).then(
        function(response) {
            response.text().then(function(text){
                document.location.href=text;
            });
        },
        function(error) {
            // Запрос не получилось отправить
            console.error(error);
        }
    );
}

