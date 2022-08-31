let signFormResult = false;
let u_nick = false;


function isFormOk() {
	if ($("#agreeAge").is(":checked") & u_nick) {
		$("#signCompleteBtn").attr("disabled", false);
	} else {
		$("#signCompleteBtn").attr("disabled", true);
	}
}
function specialSymbolCheck(e) {
	let len = e.value.length;
	for (let i = 0; i < len; i++) {
		let ch = e.value.charCodeAt(i);
		if (
			(ch >= 65 && ch <= 90) ||
			(ch >= 97 && ch <= 122) ||
			(ch >= 48 && ch <= 57)
		) {
		} else {
			return false;
		}
	}
	return true;
}

$("#agreeTotal").on("click", function() {
	if ($("#agreeTotal").is(":checked")) {
		$("input:checkbox").prop("checked", true);
	} else {
		$("input:checkbox").prop("checked", false);
	}
});

$("input:checkbox").on("click", function() {
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

$("input:checkbox").on("click", function() {
	isFormOk();
});
var pattern = /([^가-힣\x20])/i;

$("#u_nick").on("keyup", function() {
	if ($(this).val().length < 2) {
		$("#nickWarning").html("2자리 이상 한글만 입력해주세요");
		isFormOk();
	} else if ($(this).val().length >= 2) {
		if (pattern.test($(this).val())) {
			$("#nickWarning").html("올바른 형식이 아닙니다");
			u_nick = false;
			isFormOk();
		} else {

			let u_nick_value = $("#u_nick").val();
			$.ajax({
				url: "checkOverlapId.m",
				type: "post",
				dataType: "text",
				data: { "u_nick_value": u_nick_value },
				success: function(data) {
					if (data == "false") {
						$("#nickWarning").html("사용가능한 닉네임입니다");
						u_nick = true;
					} else {
						$("#nickWarning").html("사용이 불가능한 닉네임입니다");
						u_nick = false;
					}
					isFormOk();
				},
				error: function(e) {
					console.log("아이디중복체크 에러 : " + e);
				},
			});
		}
	}
});

$('#u_phone').on('keyup', function(e) {
	if (e.keyCode != 8) {
		if ($(this).val().length == 3) {
			$(this).val($(this).val() + "-")
		}
		if ($(this).val().length == 8) {
			$(this).val($(this).val() + "-")
		}
		if ($(this).val().length > 13) {
			$(this).val($(this).val().substring(0, 13))
		}
	}



})


