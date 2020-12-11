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
        if($('#create_post').css('display') == 'none'){
            $('#create_post').css('display','block');
            $('.show_field').text('Отмена');
        }else{
            $('#create_post').css('display','none');
            $('.show_field').text('Добавить');
        }

    });
});

$(document).ready(function(){
    $('.show_tag_field').on('click', function(){
        if($('#add_tag').css('display') == 'none'){
            $('#add_tag').css('display','block');
            $('.show_tag_field').text('Отмена');
        }else{
            $('#add_tag').css('display','none');
            $('.show_tag_field').text('Добавить');
        }

    });
});