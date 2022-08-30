$(".optLabel").on("click", function(e) {
	let tmp = e.target.previousElementSibling;
	if (tmp.checked) {
		tmp.click();
	} else {
		tmp.click();
	}
});

$(".cb-square-16").on("click", function(e) {
	let tmp = e.target.previousElementSibling;
	if (tmp.checked) {
		tmp.checked = false;
		// e.target.style.color = "white";

		e.target.style.backgroundColor = "white";
	} else {
		tmp.checked = true;

		e.target.style.backgroundColor = "rgb(255, 35, 35)";
	}
});


$('#searchMapBtn').on('click', function() {
	var container = document.getElementById('map');


	var options = {
		center: new kakao.maps.LatLng(33.450701, 126.570667),
		level: 3
	};

	var map = new kakao.maps.Map(container, options);

	//var markerPosition = new kakao.maps.LatLng(33.450701, 126.570667);

	// 마커를 생성합니다
	//var marker = new kakao.maps.Marker({
	//	position: markerPosition
	//});

	//marker.setMap(map);

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch('서울 강남구 테헤란로25길 10', function(result, status) {

		// 정상적으로 검색이 완료됐으면 
		if (status === kakao.maps.services.Status.OK) {

			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords
			});

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			marker.setMap(map);
			map.setCenter(coords);
		}
	});
})

// 마커가 지도 위에 표시되도록 설정합니다


$('body').on('click', function(e) {
	if (!$('#searchMapBtn').is(e.target) && !$('#searchMapBtn').has(e.target).length) {
		$('#map').fadeOut();
	}
	if ($('#searchMapBtn').is(e.target)) {
		$('#map').toggle();
	}
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

let preIdx = 0;
onload = function() {
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
			<a href="#"  >${data[i].searchWord}</a> <i class="fa-solid fa-xmark" data-idx="${i}"></i>
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
	$(".sortBy").eq(0).trigger('click');
}

$('#filterBtn').on('click', function() {
	$('#itemListWrap').empty();
	preIdx = 0;
	let arr = [];
	let sortBy = $('input[class=sortBy]:checked').val();
	let searchWord = $('#beSearchedWord').data("search");

	$('input[name=room_div]:checked').each(function() {
		arr.push(this.value);
	})
	let item_div = arr.toString().replaceAll(',', "|");

	arr = []
	$('input[name=room_theme]:checked').each(function() {
		arr.push(this.value);
	})
	let room_theme = arr.toString().replaceAll(',', "|");

	arr = [];
	$('input[name=room_extraopt]:checked').each(function() {
		arr.push(this.value);
	})
	let room_extraopt = arr.toString().replaceAll(',', "%");
	let minPrice = $('#minPrice').val();
	let maxPrice = $('#maxPrice').val();
	if (preIdx == 0) {
		$.ajax({
			url: "filteredMaxItem.it",
			data: {
				"sortBy": sortBy,
				"searchWord": searchWord,
				"item_div": item_div,
				"room_theme": room_theme,
				"room_extraopt": room_extraopt,
				"preIdx": preIdx,
				"minPrice": minPrice,
				"maxPrice": maxPrice
			},
			dataType: "json",
			type: "post",
			success: function(data) {
				$('#filteredMaxItem').html(`(${data.totalItem})`);
			}, error: function(e) {
				console.log(e);
			}
		})
	};
	sortItem()
})
let priceFlag = true;
$(".sortBy").on('click', function() {
	$('#itemListWrap').empty();
	preIdx = 0;
	let arr = [];
	let sortBy = $('input[class=sortBy]:checked').val();
	let searchWord = $('#beSearchedWord').data("search");

	$('input[name=room_div]:checked').each(function() {
		arr.push(this.value);
	})
	let item_div = arr.toString().replaceAll(',', "|");

	arr = []
	$('input[name=room_theme]:checked').each(function() {
		arr.push(this.value);
	})
	let room_theme = arr.toString().replaceAll(',', "|");

	arr = [];
	$('input[name=room_extraopt]:checked').each(function() {
		arr.push(this.value);
	})
	let room_extraopt = arr.toString().replaceAll(',', "%");
	let minPrice = $('#minPrice').val();
	let maxPrice = $('#maxPrice').val();
	if (preIdx == 0) {
		$.ajax({
			url: "filteredMaxItem.it",
			data: {
				"sortBy": sortBy,
				"searchWord": searchWord,
				"item_div": item_div,
				"room_theme": room_theme,
				"room_extraopt": room_extraopt,
				"preIdx": preIdx,
				"minPrice": minPrice,
				"maxPrice": maxPrice
			},
			dataType: "json",
			type: "post",
			success: function(data) {

				$('#filteredMaxItem').html(`(${data.totalItem})`);
				if (priceFlag) {

					$('#minPrice').val(`${data.minPrice}`);
					$('#maxPrice').val(`${data.maxPrice}`);
					priceFlag = false;
				}
			}, error: function(e) {
				console.log(e);
			}
		})
	}
	sortItem();
})


function sortItem() {
	let arr = [];

	let sortBy = $('input[class=sortBy]:checked').val();
	let searchWord = $('#beSearchedWord').data("search");

	$('input[name=room_div]:checked').each(function() {
		arr.push(this.value);
	})
	let item_div = arr.toString().replaceAll(',', "|");

	arr = []
	$('input[name=room_theme]:checked').each(function() {
		arr.push(this.value);
	})
	let room_theme = arr.toString().replaceAll(',', "|");

	arr = [];
	$('input[name=room_extraopt]:checked').each(function() {
		arr.push(this.value);
	})
	let room_extraopt = arr.toString().replaceAll(',', "%");
	let minPrice = $('#minPrice').val();
	let maxPrice = $('#maxPrice').val();
	$.ajax({
		url: "sortItem.it",
		data: {
			"sortBy": sortBy,
			"searchWord": searchWord,
			"item_div": item_div,
			"room_theme": room_theme,
			"room_extraopt": room_extraopt,
			"preIdx": preIdx,
			"minPrice": minPrice,
			"maxPrice": maxPrice
		},
		dataType: "json",
		type: "post",
		success: function(data) {
			for (let i = 0; i < data.length; i++) {
				let imgPath = data[i].item_imgpath.split(',')[1];
				$("#itemListWrap").append(`
      		<li>
                <input
                  type="checkbox"
                  class="cbSt-heart"
                  style="display: none;"
                  id="cb${data[i].item_no}"
                /><label  for="cb${data[i].item_no}"
                  ><i class="fa-solid fa-heart heartStyle" onclick="togglePick(this)" style="z-index:50000"  data-no="${data[i].item_no}" ></i
                ></label>
              <a href="itemView.it?item_no=${data[i].item_no}">
                <img src="${imgPath}" alt="" />
                <div class="itemTitle">
                  <h1 style="margin-bottom: 5px">${data[i].item_name}</h1>
                  <div>
                    <p><span>${data[i].item_div}</span> <span></span></p>
                    <p style="color:rgb(250, 187, 93);"><span>${data[i].item_grade}</span><span>(${data[i].maxReview})</span></p>
                  </div>
                  <div style="text-align: right">
                    <p></p>
                    <p style="font-size:24px">${data[i].minPrice}원</p>
                  </div>
                </div>
              </a>
            </li>
      `);
			}


			preIdx += 5;
		}, error: function(f, sts, e) {
			console.log(e, sts);
		}
	})
}
window.addEventListener("scroll", (e) => {
	let per = window.scrollY / (document.body.clientHeight - window.innerHeight);
	if (per > 0.90) {
		sortItem()
	}
});

