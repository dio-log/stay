let signFormResult = false;
let u_id = false;
let u_pw = false;
let u_nick = false;
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

function pwCheck(e) {
	let len = e.value.length;
	let strCnt = 0;
	let numCnt = 0;
	for (let i = 0; i < len; i++) {
		let ch = e.value.charCodeAt(i);
		if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) {
			strCnt++;
		} else if (ch >= 48 && ch <= 57) {
			numCnt++;
		}
	}
	console.log(strCnt + "dd" + numCnt);
	if (strCnt > 0 && numCnt > 0) {
		return true;
	} else {
		return false;
	}
}

function isFormOk() {
	if ($("#agreeAge").is(":checked") && u_pw && u_id && u_nick) {
		$("#signCompleteBtn").attr("disabled", false);
	} else {
		$("#signCompleteBtn").attr("disabled", true);
	}
}

$("#pw1").on("keyup", function() {
	if ($(this).val().length >= 8) {
		if (!specialSymbolCheck(this)) {
			$("#pwWarning").html("특수문자는 사용할 수 없습니다");
		} else if (!pwCheck(this)) {
			$("#pwWarning").html("비밀번호는 문자와 숫자가 모두 포함되어야합니다");
		} else if ($("#pw2").val() == $(this).val()) {
			$("#pwWarning").html("비밀번호가 일치합니다");
			u_pw = true;
			isFormOk();
			return;
		} else {
			$("#pwWarning").html("비밀번호가 일치하지 않습니다");
		}
	} else {
		$("#pwWarning").html("8자리이상 입력해주세요");
	}
	u_pw = false;
	isFormOk();
});

$("#pw2").on("keyup", function(e) {
	if ($(e.target).val().length >= 8) {
		if (!specialSymbolCheck(e.target)) {
			$("#pwWarning").html("특수문자는 사용할 수 없습니다");
		} else if (!pwCheck(this)) {
			$("#pwWarning").html("비밀번호는 문자와 숫자가 모두 포함되어야합니다");
		} else if ($("#pw1").val() == $(e.target).val()) {
			$("#pwWarning").html("비밀번호가 일치합니다");
			u_pw = true;
			isFormOk();
			return;
		} else {
			$("#pwWarning").html("비밀번호가 일치하지 않습니다");
		}
	} else {
		$("#pwWarning").html("8자리이상 입력해주세요");
	}
	u_pw = false;
	isFormOk();
});

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


$("#u_id").on("keyup", function() {
	if ($(this).val().length < 6) {
		$("#idWarning").html("6자리 이상 입력해주세요");
	} else if ($(this).val().length >= 6) {
		if (!specialSymbolCheck(this)) {
			$("#idWarning").html("특수문자는 사용할 수 없습니다");
			u_id = false;
		} else {
			let u_id_value = $("#u_id").val();
			console.log(u_id_value);
			$.ajax({
				url: "checkOverlapId.m",
				type: "post",
				dataType: "text",
				data: { u_id_value },
				success: function(data) {
					if (data == "false") {
						$("#idWarning").html("사용가능한 아이디입니다");
						u_id = true;
					} else {
						$("#idWarning").html("사용이 불가능한 아이디입니다");
						u_id = false;
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
$("#loginCheckBtn").on("click", function() {
	let loginInfo = {
		u_id: $("#u_id").val(),
		u_pw: $("#u_pw").val(),
	};
	let jsonStr = JSON.stringify(loginInfo);
	//traditional: true, ajax로 스트링어레이타입넘길떄
	$.ajax({
		url: "checkLogin.m",
		type: "post",
		data: { jsonStr },
		dataType: "text",
		contentType: "application/x-www-form-urlencoded",
		//traditional: true, 배열넘길떄 사용
		success: function(data) {
			// let jobj = JSON.parse(data);

			if (data == 'true') {
				location = "main.m";
			} else {
				alert("아이디 혹은 비밀번호가 맞지 않습니다");
			}
		},
		error: function(e) {
			alert(e);
		},
	});
});

$("#u_nick").on("keyup", function() {
	if ($(this).val().length < 2) {
		$("#nickWarning").html("2자리 이상 한글만 입력해주세요");
	} else if ($(this).val().length >= 2) {
		if (pattern.test($(this).val())) {
			$("#nickWarning").html("올바른 형식이 아닙니다");
			u_nick = false;
		} else {
			let u_nick_value = $("#u_nick").val();
			$.ajax({
				url: "checkOverlapNick.m",
				type: "post",
				dataType: "text",
				data: { "u_nick_value": u_nick_value },
				success: function(data) {
					if (data == "false") {
						$("#nickWarning").html("사용가능한 아이디입니다");
						u_nick = true;
					} else {
						$("#nickWarning").html("사용이 불가능한 아이디입니다");
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


window.Kakao.init('9b8cfe888abf4ea908007fe3bb9d7094');

function kakaoLoginCreateUser() {
	window.Kakao.Auth.login({
		scope: 'profile_nickname, account_email', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
		success: function(response) {
			console.log(response) // 로그인 성공하면 받아오는 데이터
			window.Kakao.API.request({ // 사용자 정보 가져오기 
				url: '/v2/user/me',
				success: (res) => {
					const kakao_account = res.kakao_account.email;
					sessionStorage.clear();
					location.href = `signForm.m?account=${kakao_account}`
				}
			});
			// window.location.href='/ex/kakao_login.html' //리다이렉트 되는 코드
		},
		fail: function(error) {
			console.log(error);
		}
	});
}

function kakaoLogin() {
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
							console.log(data)
							sessionStorage.clear();
							if (data == "true") {
								location = "main.m"
							} else {
								sessionStorage.clear();
								alert(`가입된 회원이 아닙니다. 회원가입 페이지로 이동합니다`)
								location.href = `otherSignForm.m?account=${kakao_account}`
							}
							
						}, error: function(e) {
							console.log(e)
						}

					})

				}
			});
			// window.location.href='/ex/kakao_login.html' //리다이렉트 되는 코드
		},
		fail: function(error) {
			console.log(error);
		}
	});
}
function unlinkApp() {
    Kakao.API.request({
      url: '/v1/user/unlink',
      success: function(res) {
        alert('success: ' + JSON.stringify(res))
      },
      fail: function(err) {
        alert('fail: ' + JSON.stringify(err))
      },
    })
  }

