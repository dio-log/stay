// $('input[type="number"').on("keyup", function (e) {
//     let len = $(this).val().length();
//     if (len == 3) {
//         $(this).val($(this).val() + ",");
//     }else if()
// });

$('.tempPayBtn').on('click',function(e){
	let temp = $(e.target).data('temp').split('&');
	let room_name = temp[0];
	let room_price = temp[1];
	let totalFee = $('#night').val();
	$('.temp_price').val(room_price);
	console.log(typeof(totalFee))
	$('#totalFee').html(room_price*totalFee)
	
})


let flag = true;
$('.location').on('click',function(){
	
	
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

    $('#map').css('display','block')
      
      if(flag){
	 $('.location').trigger("click");
	 flag=false;
}
  
  
});    


})
$(document).on('click',function(e){
	console.log(e);
	if(!$('#map').has(e.target).length && !$('.location').is(e.target)){
		$('#map').css('display','none');
	}
})

