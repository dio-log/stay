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
	geocoder.addressSearch($('#item_addr').html(), function(result, status) {

		// 정상적으로 검색이 완료됐으면 
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
	if(item_no==''){
		item_no = 0;
	}
	$.ajax({
		url: "checkPossibleReview.re",
		type: "post",
		dataType: "json",
		data: { item_no },
		success: function(data) {
			if (data!=null) {
				$('#usedRoom').html(data.re_room_name)
				$('#item_no').val(data.item_no);
				$('#room_no').val(data.room_no);
			} else {
				alert('해당 상품을 이용하신 고객님만 작성가능합니다')
			}

		}, error: function(e) {
			console.log(e);
		}
	})

})



$('.fa-star').on('click',function(e){
	$(e.target).addClass('fa-solid');
	$(e.target).prevAll('i').addClass('fa-solid');
	$(e.target).nextAll('i').removeClass('fa-solid');
	
	let gr1 = $('#serviceGrade span .fa-solid').length
	let gr2 = $('#cleanGrade span .fa-solid').length
	let gr3 = $('#convenienceGrade span .fa-solid').length
	
	let avg= (gr1+gr2+gr3)/3;
	let roundAvg = Math.round(avg);
	$('#re_grade').val(roundAvg);	
})

$('#doPayBtn').on('click',function(){
	
	console.log($('#chargeRoomPrice').val() +"?"+$('#calendarInput').val())
	if($(this).data("no")==null){
		alert('로그인이 필요합니다');
	}else if($('#chargeRoomPrice').val()=='' || $('#calendarInput').val()==''){
		
		alert('필수 사항이 선택되지 않았습니다')
	}
	else{
		$('#doPayForm').submit();
	}
})

