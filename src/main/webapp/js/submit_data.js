function autor(){
    var email = document.getElementById("username").value;
    var password = document.getElementById("password").value;
   $.ajax({
       url: "/log_in",
       method: 'post',
       contentType: "application/json",
       data:JSON.stringify({
            "email": email,
            "password": password
       }),
       success: function(redirect){
            into(redirect);
       }

   })
}

function reg(){
   document.location.href="/registration";
}

function into(redirect){
    document.location.href=redirect;
}


