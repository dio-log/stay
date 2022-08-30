onload = function() {
	$("#mainMenu").slideUp();
	for (let i = 1; i < 10; i++) {
		$(`.lo${i}`).hover(
			(e) => {
				$(e.target).css("fill", "rgb(255, 161, 161)");
				$(e.target).css("cursor", "pointer");
				$(`.g${i}`).addClass("mapAni");
			},
			(e) => {
				$(e.target).css("fill", "#F5EDD4");
				$(`.g${i}`).removeClass("mapAni");
			}
		);
	}

	for (let i = 1; i < 10; i++) {
		$(`.lo${i}`).on("click", (e) => {
			console.log($(e).data());

			$("#mainMenu").slideUp(300);
			$("#mainMenu").slideDown(300);
		});
	}
	$(".st102 , .st103").hover(
		(e) => {
			console.log("aaaa");
			$(".lo9").css("fill", "rgb(255, 161, 161)");
			$(`.g9`).addClass("mapAni");
		},
		(e) => {
			$(".lo9").css("fill", "#F5EDD4");
			$(`.g9`).removeClass("mapAni");
		}
	);

	$.ajax({
		url: "getCookies.m",
		dataType: "json",
		data: "",
		type: "post",
		success: function(data) {
			for (let i = data.length - 1; i >= 0; i--) {
				$('#searchRecentBox').append(`
			<div class="searchRecnetContent" onclick="recentSearch(this)" data-recent="${data[i].searchWord}&${data[i].calendarInput}&${data[i].peopleInput}">
			<p>
			<a href="#"  >${data[i].searchWord}</a> <i class="fa-solid fa-xmark" data-idx="${ i }"></i>
				</p>
			<p>${data[i].calendarInput} <br> ${data[i].peopleInput}</p>
			</div>
			`)
			}
			removeCookie();
		}, error: function(e) {
			console.log(e);
		}
	})
}





let cnt = 0;
setInterval(mainImgChange, 3000);
function mainImgChange() {
	$(".mainImg")
		.eq(++cnt)
		.fadeIn(400)
	$(".mainImg").eq(cnt - 1).hide();

	if (cnt > 2) {
		cnt = -1;
	}


}


$("body").on("click", (e) => {
	if (
		!$("#setLocationBox").is(e.target) &&
		!$("#setLocationBox").has(e.target).length
	) {
		$("#searchDetination").hide();
	} else {
		$("#searchDetination").show();
	}
	if (
		!$("#setCalendarBox").is(e.target) &&
		!$("#setCalendarBox").has(e.target).length
	) {
		$("#calendar").hide();
	} else {
		$("#calendar").show();
	}

	if (
		!$("#setPeopleBox").is(e.target) &&
		!$("#setPeopleBox").has(e.target).length
	) {
		$("#setPeople").hide();
	} else {
		$("#setPeople").show();
	}
	if ($("#setPeopleBtn").is(e.target)) {
		$("#setPeople").hide();
	}
});



let bannerCnt = 0;
setInterval(() => {
	if (bannerCnt < 4) {
		$('#bannerWrap').css('left', `${bannerCnt * -1004}px`)
		bannerCnt++;
		if (bannerCnt >= 4) {
			bannerCnt = 0;
		}
	}
}, 2000)
