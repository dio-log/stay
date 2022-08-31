// $('input[type="number"').on("keyup", function (e) {
//     let len = $(this).val().length();
//     if (len == 3) {
//         $(this).val($(this).val() + ",");
//     }else if()
// });







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
			console.log("roomMoreMax" + roomMoreMax)


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
		roomMoreCnt = 0;
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


onload=function(){
	
	$.ajax({
		url:"",
		type:"post",
		data:{"item_no":item_no},
		dataType:"json",
		success:function(data){
			
		},error:function(e){
			
		}
	})
}