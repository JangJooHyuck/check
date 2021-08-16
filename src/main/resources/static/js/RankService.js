//tab 화면 만들기
$(document).ready(function(){
	
	$('ul.tables li').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('ul.tables li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})
})
//참고 url https://imivory.tistory.com/8