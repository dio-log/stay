$('#allAgree').on('click',function(){
	let chk = $('input[type=checkbox]');
if($(this).is(':checked')){
	chk.prop("checked",true);
}else{
	chk.prop("checked",false);
}
})

$('#doPayBtn').on('click',function(){
	$('#outerPayBox').fadeIn();
})
$('#cancelBtn').on('click',function(){
	$('#outerPayBox').fadeOut();
})

$('#p_point').on('keyup',function(){
	console.log($(this).attr('max'))
	if($(this).val()>$(this).attr('max')){
		$(this).val($(this).attr('max'));
	}
})

$('#compBtn').on('click',function(){
	$('#payForm').submit();
})