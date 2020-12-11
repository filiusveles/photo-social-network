document.getElementById('add_tag').addEventListener('submit', submitForm);


function submitForm(event) {
    // Отменяем стандартное поведение браузера с отправкой формы
    event.preventDefault();
    var tag = document.getElementById('tag_name').value;
    var page = document.getElementById('post_id').value;
    var data = {"tag":tag};
    sub(data, page);
}

function sub(data, page){
    var sendPage = "/post/" + page + "/add_tag"

    // Собираем запрос к серверу
    let request = new Request(sendPage, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json',
        },
    });
    // Отправляем (асинхронно!)
    fetch(request).then(
        function(response) {
            response.text().then(function(text){
                if(text == "ok"){
                    document.location.href = document.location.href;
                }
            });
        },
        function(error) {
            // Запрос не получилось отправить
            console.error(error);
        }
    );
}