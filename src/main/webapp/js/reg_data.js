function send(){
    var firstname = document.getElementById("firstname").value;
    var lastname = document.getElementById("lastname").value;
    var phone = document.getElementById("phone").value;
    var nickname = document.getElementById("nickname").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    $.ajax({
        url:"/register_user",
        method: "post",
        contentType: "application/json",
        data:JSON.stringify({
            "firstname":firstname,
            "lastname":lastname,
            "phone":phone,
            "nickname":nickname,
            "email":email,
            "password":password
        }),
        success: function(redirect){
            document.location.href=redirect;
        }

    })
}