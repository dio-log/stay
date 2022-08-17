let signFormResult = false;
let u_id = false;
let u_pw= false;

function specialSymbolCheck(e){
  let len = e.value.length;
  for(let i=0;i<len;i++){
    let ch = e.value.charCodeAt(i);
    if(ch>=65 && ch<=90 || ch>=97 && ch<=122 || ch>=48 && ch<=57){
    }else{
      return false;
    }
  }
  return true;
}

function pwCheck(e){
	let len = e.value.length;
	let strCnt = 0;
	let numCnt=0;
  for(let i=0;i<len;i++){
    let ch = e.value.charCodeAt(i);
    if(ch>=65 && ch<=90  || ch>=97 && ch<=122 ){
	 strCnt++;
    }else if(ch>=48 && ch<=57){
		numCnt++;
	}
    }
    console.log(strCnt+"dd"+numCnt);
    if(strCnt>0 && numCnt>0){
	return true;
}else{return false;}
}


function isFormOk(){
	if ($("#agreeAge").is(":checked")&&u_pw&&u_id) {
    $("#signCompleteBtn").attr("disabled", false);
  } else {
    $("#signCompleteBtn").attr("disabled", true);
  }
}


$("#pw1").on("keyup", function () {
  if ($(this).val().length >= 8) {
    if(!specialSymbolCheck(this)){
      $("#pwWarning").html("특수문자는 사용할 수 없습니다");
    }
    else if(!pwCheck(this)){
	 $("#pwWarning").html("비밀번호는 문자와 숫자가 모두 포함되어야합니다");

  }
    else if ($("#pw2").val() == $(this).val()) {
      $("#pwWarning").html("비밀번호가 일치합니다");
      u_pw =true;
      isFormOk();
      return;
    } else {
      $("#pwWarning").html("비밀번호가 일치하지 않습니다");
     
        
    }
  } else {
    $("#pwWarning").html("8자리이상 입력해주세요");
  
  }
  u_pw=false;
  isFormOk();
});

$("#pw2").on("keyup", function (e) {
  if ($(e.target).val().length >= 8) {
    if(!specialSymbolCheck(e.target)){
      $("#pwWarning").html("특수문자는 사용할 수 없습니다");
    }
      else if(!pwCheck(this)){
	 $("#pwWarning").html("비밀번호는 문자와 숫자가 모두 포함되어야합니다");
  }
    else if ($("#pw1").val() == $(e.target).val()) {
      $("#pwWarning").html("비밀번호가 일치합니다");
      u_pw =true;
      isFormOk();
      return;
     
    } else {
      $("#pwWarning").html("비밀번호가 일치하지 않습니다");
    }
  } else {
    $("#pwWarning").html("8자리이상 입력해주세요");
  }
  u_pw=false;
  isFormOk();
});

$("#agreeTotal").on("click", function () {
  if ($("#agreeTotal").is(":checked")) {
    $("input:checkbox").prop("checked", true);
  } else {
    $("input:checkbox").prop("checked", false);
  }
});

$("input:checkbox").on("click", function () {
  if (
    $("#agreeUse").is(":checked") &&
    $("#agreeFinance").is(":checked") &&
    $("#agreePersonal").is(":checked")
  ) {
    $("#agreeNextBtn").attr("disabled", false);
  } else {
    $("#agreeNextBtn").attr("disabled", true);
  }
});

$("input:checkbox").on("click", function () {
	isFormOk();
});

$("#u_id").on("keyup", function () {
  if ($(this).val().length >= 6) {
    if(!specialSymbolCheck(this)){
      $('#idWarning').html('특수문자는 사용할 수 없습니다');
      u_id=false;
    }else{
      $.ajax({
        url: 'checkOverlapId.m',
        type:'post',
        dataType: "text",
        data: $('#u_id').val(),
        success:function(data){
          if(data=='false'){
	
          $('#idWarning').html('사용가능한 아이디입니다');
          u_id=true;
        
          }else{
            $('#idWarning').html('사용이 불가능한 아이디입니다');
            u_id=false;
             
          }
          isFormOk();
        },
        error:function(e){
          console.log('아이디중복체크 에러 : '+e);
        }
      });
    }
    
  }
});

