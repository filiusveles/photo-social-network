function user_data(user){
    $(document).ready(function(){
    	if(user.avatar != null)
    	{
        $('#avatar').attr('src', user.avatar);
    	}
        $('.nickname').val(user.nickname);
    })
}
function user_posts(image_url){
    $(document).ready(function(){
        $('#image').attr('src', image_url);
    });
}
