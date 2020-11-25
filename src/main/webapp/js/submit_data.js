function autor(){
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
/*       success: function(redirect){
            document.location.href=redirect;
       }*/
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
}


