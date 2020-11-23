function autor(){
    event.preventDefault();
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
   $.ajax({
       url: "welcome/login",
       method: 'post',
       contentType: "application/json",
       data:JSON.stringify({
            "username": username,
            "password": password
       }),
       success: function(){
            concole.log(true);
       }

   })
}


