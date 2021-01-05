document.getElementById('create_post').addEventListener('submit', submitPost);


function submitPost(event) {
    // Отменяем стандартное поведение браузера с отправкой формы
    event.preventDefault();
    var image = document.getElementById('image').files;
    var title = document.getElementById('post_title').value;
    var description = document.getElementById('send_description').value;

    var data = null;
    if(image.length > 0){
        getBase64(image[0], function(result){
            data = {"post_title":title,"description":description,"image":result};
            sendPost(data);
        });
    }
}

function sendPost(data){
    var page = document.getElementById('user_id').value;
    var sendPage = "/" + page + "/create_post"

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

function getBase64(file, cb) {
   var reader = new FileReader();
   reader.readAsDataURL(file);
   reader.onload = function () {
     console.log(reader.result);//outputs random looking characters for the image
     // Return the result in the callback
     cb(reader.result);
   };
   reader.onerror = function (error) {
     console.log('Error: ', error);
   };
}