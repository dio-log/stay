window.addEventListener("scroll", (e) => {
	// console.log(window.scrollY);
	if (window.scrollY > 20) {
		$(".nav").css("height", "50px");
		$(".nav").css("background-color", "white");
		$(".navTit").css({ "font-size": "25px", color: "black" });
		$(".fa-user").css("color", "black");
		$(".logo").css("color", "#f7323f");
	} else {
		$(".nav").css("height", "80px");
		$(".nav").css("background-color", "#f7323f");
		$(".navTit").css({ "font-size": "35px", color: "white" });
		$(".fa-user").css("color", "white");
		$(".logo").css("color", "white");
	}

	if (window.scrollY > 1000) {
		$('.topBtn').show();
	} else {
		$('.topBtn').hide();
	}

	let per = window.scrollY / (document.body.clientHeight - window.innerHeight);
	if (per > 0.97) {
		$('.topBtn').css("bottom", "250px");
	} else {
		$('.topBtn').css("bottom", "30px");
	}

});

$('.topBtn').on('click', function() {
	$(window).scrollTop(0)
})

$(".fa-user").on("click", () => {
	//$(".navCartBox").fadeToggle();
	$(".navCartBox").slideToggle();

});

$(".plusBtn").on("click", function() {
	this.previousElementSibling.stepUp();
});
$(".minusBtn").on("click", function() {
	this.nextElementSibling.stepDown();
});

$("#setPeopleBtn").on("click", function() {
	//성인 4, 아동 0, 반려동물 2
	let adult = $(".setPeopleAdult").val();
	let kids = $(".setPeopleKids").val();
	let pet = $(".setPeoplePet").val();
	$("#peopleInput").val(`성인 ${adult}, 아동 ${kids}, 반려동물 ${pet}`);
});


function removeCookie() {
	$('.fa-xmark').on('click', function(e) {
		e.stopPropagation();
		let idx = $(e.target).data("idx");
		$.ajax({
			url: "removeCookie.m",
			type: "post",
			dataTpye: "text",
			data: { idx },
			success: function(data) {
				$(e.target).parent().parent().remove();
			}, error: function(e) {
				console.log(e);
			}
		})

	}
	)
}

function beLoginCheck(input) {
	let flag = false;
	$.ajax({
		url: "beLoginCheck.m",
		type: "post",
		data: {},
		dataType: "text",
		success: function(data) {
			if (data != 'null') {
				flag = true;
			} else {
				alert('로그인이 필요한 서비스입니다')
				input.prop("checked");
			}
		}, error: function() {

		}
	})
	return flag;
}


function togglePick(e) {
	let item_no = $(e).data("no");
	let input = $(e).parent().prev('input');
	let flag = beLoginCheck(input);
	

	if (flag) {
		if (input.is(':checked')) {
			$.ajax({
				url: "updatePick.m",
				type: "post",
				data: { "item_no": item_no, "signal": "del" },
				dataType: "text",
				success: function(data) {
					if (data > 0) {
						$('#alertAddPick').html('찜 목록에서 <br> 삭제되었습니다')
						$('#alertAddPick').fadeIn(200);
						setTimeout(() => {
							$('#alertAddPick').fadeOut(200);
						}, 1000)
					}
				}, error: function() {
				}
			})
		} else {
			$.ajax({
				url: "updatePick.m",
				type: "post",
				data: { "item_no": item_no, "signal": "add" },
				dataType: "text",
				success: function(data) {
					if (data > 0) {
						$('#alertAddPick').html('찜 목록에 <br> 추가되었습니다')
						$('#alertAddPick').fadeIn(200);
						setTimeout(() => {
							$('#alertAddPick').fadeOut(200);
						}, 1000)
					}
				}, error: function() {

				}
			})

		}
	}

}
function recentSearch(e) {
	let ds = $(e).data("recent");
	let arr = ds.split("&");

	$('#setLocationInput').val(arr[0]);
	$('#calendarInput').val(arr[1]);
	$('#peopleInput').val(arr[2]);
}

function setComma(num){
	let numlen = num.length;
	
}

