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

$("#modifyNick").on("keyup", function() {
	if ($(this).val().length >= 2) {
		if (!specialSymbolCheck(this)) {
			$("#nickWarning").html("영문,숫자 이외의 문자는 사용할 수 없습니다");
		} else {
			let nickValue = $("#modifyNick").val();
			$.ajax({
				url: "checkOverlapNick.m",
				type: "post",
				dataType: "text",
				data: { nickValue },
				success: function(data) {
					if (data == "false") {
						$("#nickWarning").html("사용가능한 닉네임입니다");
					} else {
						$("#nickWarning").html("사용이 불가능한 닉네임입니다");
					}
				},
				error: function(e) {
					console.log("닉네임 수정 에러 : " + e);
				},
			});
		}
	}
});

// $("#modifyPhone").on("keyup", function () {
//   console.log($(this).val().length);
//   if($(this).val())
//   if ($(this).val().length == 3) {
//     $(this).val($(this).val() + "-");
//   } else if ($(this).val().length == 8) {
//     $(this).val($(this).val() + "-");
//   } else if ($(this).val().length > 13) {
//     $("#phoneWarning").html("올바른 형식이 아닙니다");
//   } else {
//     $("#phoneWarning").html("");
//   }
// });
$("#modifyPhoneInput").on("keyup", function() {
	if ($(this).val().length > 11) {
		$("#phoneWarning").html("올바른 형식이 아닙니다");
	} else if ($(this).val().length == 11) {
		$("#getAccessNum").attr("disabled", false);
	}
});

$("#myPagePwCheckBtn").on("click", function() {
	let pwValue = $("#myPagePwCheckInput").val();
	$.ajax({
		url: "selectMember.m",
		type: "post",
		dataType: "json",
		data: { pwValue },
		success: function(data) {
			console.log(data);

			console.log(data.u_phone);
			if (data.result == true) {
				$("#myPagePwCheckWrap").hide();
				$("#myPageModifyWrap").show();
				$("#u_id").html(data.u_id);
				$("#u_email").html(data.u_email);
				$("#u_phone").html(data.u_phone);
			} else {
				alert("비밀번호가 맞지 않습니다");
			}
		},
		error: function(req, status, e) {
			alert("에러 : " + req + "/" + status + "/" + e);
		},
	});
});

$('#modifyCancelBtn').on('click', function() {
	location = "myInfo.my";
})

window.Kakao.init('9b8cfe888abf4ea908007fe3bb9d7094');

function kakaoLoginCheck() {
	window.Kakao.Auth.login({
		scope: 'profile_nickname, account_email', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
		success: function(response) {
			console.log(response) // 로그인 성공하면 받아오는 데이터
			window.Kakao.API.request({ // 사용자 정보 가져오기 
				url: '/v2/user/me',
				success: (res) => {
					const kakao_account = res.kakao_account.email;
					$.ajax({
						url: 'otherLoginCheck.m',
						data: { "account": kakao_account },
						dataType: "text",
						type: "post",
						success: function(data) {
							if (data == "true") {
								$("#myPagePwCheckWrap").hide();
								$("#myPageModifyWrap").show();
								//$("#u_id").html(data.u_id);
								$("#u_email").html(kakao_account);
							}
						}
						, error: function(e) {
							console.log(e)
						}

					});
					// window.location.href='/ex/kakao_login.html' //리다이렉트 되는 코드
				},
				fail: function(error) {
					console.log(error);
				}
			});
		}
	})
}
