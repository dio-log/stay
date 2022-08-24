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
			for (let i = data.length-1; i > data.length-4; i--) {
				$('#searchRecentBox').append(`
			<div class="searchRecnetContent">
			<p>
			<a href="">${data[i].searchWord}</a> <i class="fa-solid fa-xmark"></i>
				</p>
			<p>${data[i].calendarInput} <br> ${data[i].peopleInput}</p>
			</div>
			`)
			}
		

		}, error: function(e) {
			console.log(e);
		}
	})
};
let cnt = 0;
setInterval(mainImgChange, 4000);
function mainImgChange() {
	$(".mainImg").eq(cnt).hide(400);
	if (cnt > 2) {
		cnt = -1;
	}
	$(".mainImg")
		.eq(++cnt)
		.fadeIn(800);
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


