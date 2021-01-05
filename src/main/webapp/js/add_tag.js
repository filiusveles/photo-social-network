function submitTag(form) {
    let obj = {};
    let formData = new FormData(form);
    formData.forEach((value,key) => obj[key]=value);
    sendTag(obj);
}

function sendTag(data){
    var sendPage = "/post/" + data["postId"] + "/add_tag";
    let request = new Request(sendPage, {
        method: 'POST',
        body: JSON.stringify(data, ['tag']),
        headers: {
            'Content-Type': 'application/json',
        },
    });
    fetch(request).then(
        function(response) {
            response.text().then(function(text){
                if(text == "ok"){
                    document.location.href = document.location.href;
                }
            });
        },
        function(error) {
            console.error(error);
        }
    );
}