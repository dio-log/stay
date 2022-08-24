$(".optLabel").on("click", function (e) {
  let tmp = e.target.previousElementSibling;
  if (tmp.checked) {
    tmp.click();
  } else {
    tmp.click();
  }
});

$(".cb-square-16").on("click", function (e) {
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

function moreItemList() {
  $.ajax({
    url: "selectItemList.it",
    type: "post",
    dataType: "JSON",
    data: { searchWord: $("#beSearchedWord").html() },
    success: function (data) {
	$('#beSearchedWord').html(data[1].searchWord);
	console.log(data[1])
	console.log(data[1].searchWord)
      let imgPath = data[0].item_imgpath.split(',')[1];
      $("#itemListWrap").append(`
      <li>
              <a href="itemView.it?item_no=${data[0].item_no}">
                <img src="${imgPath}" alt="" />
                <input
                  type="checkbox"
                  class="cbSt-heart"
                  style="display: none"
                /><label for="item_no1"
                  ><i class="fa-solid fa-heart heartStyle"></i
                ></label>
                <div class="itemTitle">
                  <h1 style="margin-bottom: 5px">${data[0].item_name}</h1>
                  <div>
                    <p><span>${data[0].item_div}</span> <span>등급미정</span></p>
                    <p><span>${data[0].item_grade}</span><span>(${data[0].item_reviewCnt})</span></p>
                  </div>
                  <div style="text-align: right">
                    <p></p>
                    <p>${data[0].item_price}원</p>
                  </div>
                </div>
              </a>
            </li>
      `);
    },
    error: function (r, sts, s) {
      console.log(e + "/" + sts);
    },
  });
};


var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
		
		var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

marker.setMap(map);
		
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

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;"></div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
// 마커가 지도 위에 표시되도록 설정합니다


$('body').on('click',function(e){
	if(!$('#searchMapBtn').is(e.target) &&!$('#searchMapBtn').has(e.target).length){
		$('#map').fadeOut();
	}
	if($('#searchMapBtn').is(e.target)){
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
onload=function(){
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

}
