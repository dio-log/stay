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


let fullDate = new Date();
let year = fullDate.getFullYear();
let month = fullDate.getMonth() + 1;
$('#monthlyMonth').html(year + '-' + month);
getMonthlySales();

$($('#monthlyLeftBtn')).on('click', function() {
	fullDate.setMonth(fullDate.getMonth() - 1)
	$('#monthlyMonth').html(fullDate.getFullYear() + '-' + (fullDate.getMonth() + 1));
	getMonthlySales();

})

$($('#monthlyRightBtn')).on('click', function() {
	fullDate.setMonth(fullDate.getMonth() + 1)
	$('#monthlyMonth').html(fullDate.getFullYear() + '-' + (fullDate.getMonth() + 1));
	getMonthlySales();
})

$('#itemSelect').on('change', function() {
	getMonthlySales()
})
function getMonthlySales() {
	let item_no = $('#itemSelect').val();
	let tmpDate = new Date(fullDate.getFullYear(), fullDate.getFullYear(), 0)
	let firstDate = fullDate.getFullYear() + '-' + setZero(fullDate.getMonth()) + '-' + 1;
	let lastDate = fullDate.getFullYear() + '-' + setZero(fullDate.getMonth() + 1) + '-' + tmpDate.getDate();
	$('#rightTb').children().remove();
	$.ajax({
		url: "getMonthlySales.pay",
		type: "post",
		data: { "item_no": item_no, "firstDate": firstDate, "lastDate": lastDate },
		dataType: "json",
		success: function(data) {
			let cur = data[0].sumFee;
			let prev = data[1].sumFee
			$('#monthSales').html(data[0].sumFee);
			if (cur >= prev) {
				$('#comparePrevMonth').html(`${cur - prev} <img alt=""
											src="../../img/main/up.png">`)
			} else {
				$('#comparePrevMonth').html(`${prev - cur} <img alt=""
											src="../../img/main/down.png">`)
			}
			let curFirst = fullDate.getFullYear() + '-' + setZero(fullDate.getMonth()+1) + '-' + 1;
			getPaymentByRoom(item_no,curFirst,lastDate);
		}, erroe: function(e) {
			console.log(e);
		}
	})
}

function getPaymentByRoom(item_no,curFisrt,curLast) {
console.log(curFisrt);
console.log(curLast);

	$.ajax({
		url: "getPaymentByRoom.pay",
		type: "post",
		data: { "item_no":item_no, "curFisrt":curFisrt,"curLast":curLast},
		dataType: "json",
		success: function(data) {
			console.log("dddddd")
			$('#rightTb').append(`
										<colgroup style="width: 100%">
											<col style="width: 10%">
											<col style="width: 50%">
											<col style="width: 10%">
											<col style="width: 30%">
										</colgroup>
										<tr style="background-color:#1d1b1e0a; height: 40px;font-size:14px; ;">
											<td>No</td>
											<td>객실명</td>
											<td>예약</td>
											<td>판매금액</td>
										</tr>
			`)
			for (let i = 0; i < data.length; i++) {
				$('#rightTb').append(`
					<tr>
						<td>${i + 1}</td>
						<td>${data[i].room_name}</td>
						<td>${data[i].count}</td>
						<td>${data[i].sum}</td>
					</tr>
				`);

			}
		}, erroe: function(e) {
			console.log(e);
		}
	})
}

function setZero(val) {
			if (val >= 10) {
				return val;
			}
			return `0${val}`;
		}

