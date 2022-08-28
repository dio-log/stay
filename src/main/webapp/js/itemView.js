// $('input[type="number"').on("keyup", function (e) {
//     let len = $(this).val().length();
//     if (len == 3) {
//         $(this).val($(this).val() + ",");
//     }else if()
// });

$('.tempPayBtn').on('click', function(e) {
	let temp = $(e.target).data('temp').split('&');
	let room_name = temp[0];
	let room_price = temp[1];
	let room_max_men = temp[2];
	let room_no = temp[3];
	let totalFee = $('#night').val();
	$('.temp_price').val(room_price);
	$('#totalFee').html(room_price * totalFee)
	$('.chargePeople input').attr('max', room_max_men);
	$('#p_room_name').val(room_name);
	$('#room_no').val(room_no);
})


let flag = true;
$('.location').on('click', function() {
	var container = document.getElementById('map');
	var options = {
		center: new kakao.maps.LatLng(33.450701, 126.570667),
		level: 3
	};

	var map = new kakao.maps.Map(container, options);
	var geocoder = new kakao.maps.services.Geocoder();
	geocoder.addressSearch($('#item_addr').html().trim(), function(result, status) {
		// 정상적으로 검색이 완료됐으면 3
		if (status === kakao.maps.services.Status.OK) {

			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords
			});

			// 인포윈도우로 장소에 대한 설명을 표시합니다
			// var infowindow = new kakao.maps.InfoWindow({
			//     content: '<div style="width:150px;text-align:center;padding:6px 0;"></div>'
			// infowindow.open(map, marker);

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		}

		$('#map').css('display', 'block')

		if (flag) {
			$('.location').trigger("click");
			flag = false;
		}
	});
})
$(document).on('click', function(e) {
	if (!$('#map').has(e.target).length && !$('.location').is(e.target)) {
		$('#map').css('display', 'none');
	}
})

$('#calendar').hide();
$('#calendarInput').on('click', function() {
	$('#calendar').slideToggle();
})

$('#writeReviewBtn').on('click', function() {
	let item_no = $(this).data('no');
	if (item_no == '') {
		item_no = 0;
	}
	$.ajax({
		url: "checkPossibleReview.re",
		type: "post",
		dataType: "json",
		data: { item_no },
		success: function(data) {
			if (data != null) {
				$('#usedRoom').html(data.re_room_name)
				$('#item_no').val(data.item_no);
				$('#room_no').val(data.room_no);
				$('#reviewForm').show();
			} else {
				alert('해당 상품을 이용하신 고객님만 작성가능합니다')
			}

		}, error: function(e) {
			console.log(e);
		}
	})

})



$('.tempClass span i').on('click', function(e) {
	$(e.target).addClass('fa-solid');
	$(e.target).prevAll('i').addClass('fa-solid');
	$(e.target).nextAll('i').removeClass('fa-solid');

	let gr1 = $('#serviceGrade span .fa-solid').length
	let gr2 = $('#cleanGrade span .fa-solid').length
	let gr3 = $('#convenienceGrade span .fa-solid').length

	let avg = (gr1 + gr2 + gr3) / 3;
	let roundAvg = Math.round(avg);
	$('#re_grade').val(roundAvg);
})

$('#doPayBtn').on('click', function() {

	console.log($('#chargeRoomPrice').val() + "?" + $('#calendarInput').val())
	if ($(this).data("no") == null) {
		alert('로그인이 필요합니다');
	} else if ($('#chargeRoomPrice').val() == '' || $('#calendarInput').val() == '') {

		alert('필수 사항이 선택되지 않았습니다')
	}
	else {
		$('#doPayForm').submit();
	}
})


let roomMoreMax = 0;
let roomMoreCnt = 0;

$('.itemTab_roomDetailMore').on('click', function(e) {
	$('#roomMoreBox').children('div').remove();
	$('#roomMoreBox').children('h3').remove();
	let dataSet = $(e.target).data("no");

	$.ajax({
		url: "selectRoom.it",
		data: { "dataSet": dataSet },
		type: "post",
		dataType: "json",
		success: function(data) {


			$('#roomMoreBox').append(`
	<div class="roomMoreImgBox">
					<div id="roomMoreImgCont">
					<div></div>
					</div>
					<button type="button">
						<i class="fa-solid fa-angle-left" onclick="roomMoreLeftBtn()"></i>
					</button>
					<button type="button">
						<i class="fa-solid fa-angle-right" onclick="roomMoreRightBtn()"></i>
					</button>
				</div>
				<h3>${data.room_name} / ${data.room_view}전망</h3>
				<div id="roomMoreInfoBox">
					<div id="roomMoreLeftBox">
						<ul>
							<li>기본정보</li>
							<li><img alt="" src="../../img/main/double-bed.png"> <span> ${data.room_bed} ${data.room_bed_cnt} </span></li>
							<li>${data.basic_men}인 기준, 최대 ${data.max_men}인</li>
							<li>${data.room_size} m<sup>2</sup></li>
							<li>${data.room_theme}</li>
						</ul>
					</div>
					<div id="roomMoreRightBox">
						<ul>
							<li>편의시설</li>
						</ul>
					</div>
				</div>`);

			let optArr = data.room_extraopt.split(',');
			for (let opt of optArr) {
				if (opt != '') {
					$('#roomMoreRightBox ul').append(`
							<li>· ${opt}</li>
						`)
				}
			}
			
			
			let imgArr = data.room_img_path.split(',');
			roomMoreMax = imgArr.length - 1;
			console.log("roomMoreMax"+roomMoreMax)
			
			
			for (let imgPath of imgArr) {
				if (imgPath != '') {
					$('#roomMoreImgCont div').append(`
					<img alt="" src="${imgPath}">
					`)
				}
			}
			$('#roomMoreBox').show();

		}, error: function(req, sts, e) {
			console.log(req, sts, e);
			alert(req + "/" + sts + "/" + e);
		}
	})
})




$('#roomMoreBox>p').on('click', function(e) {
	if ($('#roomMoreCloseImg').is($(e.target))) {
		$('#roomMoreBox').children('div').remove();
		$('#roomMoreBox').children('h3').remove();
		$('#roomMoreBox').hide();
		roomMoreCnt= 0;
	}
})

function roomMoreLeftBtn() {
	if (roomMoreCnt < roomMoreMax - 1) {
		roomMoreCnt++;
	}
	console.log(roomMoreCnt)
	$('#roomMoreImgCont>div').css('left', `${roomMoreCnt * -400}px`);
}

function roomMoreRightBtn() {
	if (roomMoreCnt > 0) {
		roomMoreCnt--;
	}
	console.log(roomMoreCnt)
	$('#roomMoreImgCont>div').css('left', `${roomMoreCnt * -400}px`);
}

