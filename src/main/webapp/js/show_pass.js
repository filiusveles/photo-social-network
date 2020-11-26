$(document).ready(function(){
	$('.showPass').on('click', function(){
		if($('.show_input_pass').attr('type') == 'password'){
			$(this).text('Скрыть');
			$('.show_input_pass').attr('type', 'text');
		}else{
			$(this).text('Показать');
			$('.show_input_pass').attr('type', 'password');
		}
		return false;
	});
});


$(document).ready(function(){
    $('.show_field').on('click', function(){
        $('#create_post').style.display='block';
    });
})