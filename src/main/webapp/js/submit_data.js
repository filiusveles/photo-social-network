function autor(){
    event.preventDefault();
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
       success: function(){
            concole.log(true);
       }

   })
}


