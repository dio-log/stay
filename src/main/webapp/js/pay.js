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