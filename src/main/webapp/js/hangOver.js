window.addEventListener('scroll', (e) => {
    console.log(window.scrollY);
    if (window.scrollY > 20) {
        $('.nav').css('height', '50px');
        $('.logo').css('width', '50px');
        //            $('#nav-logoBox').css('margin-top','3px');
        $('#nav-ul').css('opacity', '0');
        $('.navTit').css('font-size', '25px');
        $('.navTit').css('margin-top', '5px')
    } else {
        $('.nav').css('height', '80px');
        $('.logo').css('width', '80px');
        //         $('#nav-logoBox').css('margin-top','30px');
        $('#nav-ul').css('opacity', '1');
        $('.navTit').css('font-size', '40px');
        $('.navTit').css('margin-top', '8px');
    }
})



function weatherContBtn(e) {
    let dataN = $(e).data('w');
    $('.main-weather__contentStyle').css('display', 'none')
    $(`#main-weather__content-${dataN}`).css('display', 'block');
}



$('.checkContent').on('click', function (e) {
    let tmp = e.target.previousElementSibling;
    if (tmp.checked) {
        tmp.click();

    } else {
        tmp.click();
    }
});


$('.checkBox').on('click', function (e) {
    let tmp = e.target.previousElementSibling;
    if (tmp.checked) {
        tmp.checked = false;
        e.target.style.color = 'white';
    } else {
        tmp.checked = true;
        e.target.style.color = 'black';
    }
});


let carouselCnt = 0;

function recentLeftBtn() {
    
    if (carouselCnt > 0) {
        carouselCnt--;
        let tmp = -162*carouselCnt;
        console.log(tmp);
        $('#recentUl').css('transform', `translate(${tmp}px)`);
        
    }

}

function recentRightBtn() {
   
    
    if(carouselCnt==5) {
        $('#recentUl').css('transform', `translate(-860px)`);
    }
    else if (carouselCnt < 6) {
        carouselCnt++;
         let tmp = -162*carouselCnt;
        $('#recentUl').css('transform', `translate(${tmp}px)`);s
    }
    
}

setTimeout(()=>{
//    $('#mainLogoAniWrap').css('border-radius','50%');
//    $('#mainLogoAniWrap').css('width','0px');
    $('#mainLogoAniWrap').css('height','0px');
    $('#mainLogoAniWrap').css('opacity','0');
               }
          ,1000);



//리스트를 서버에서 받아왔다고 가정
function createItemList(){
    let createItemList = `<li class='itemList'>
<img src="http://via.placeholder.com/220x160" alt="">
<div>
  <div>
                                        <h3>세인트 존스 호텔</h3>
                                        <p>호텔 | 등급미정</p>
                                        <p>편의시설</p>
                                        <p>바베큐장 | 주차장 | 야외수영장</p>
                                        <i class="fa-solid fa-location-dot"></i>
                                        <span>지도로 위치 보기</span>
                                    </div>
                                    <div>
                                        <div>
                                           <h3>303,050원</h3>
                                            <p>5%할인</p>
                                        </div>
                                        <div>
                                            <br><br>
                                            <p>****</p>
                                            <p>리뷰<span>(209)</span></p>
                                        </div>

                                    </div>
                                </div>
                           </li>`;
    for(let i=0; i<5;i++){
        $('#itemListUl').append(createItemList);
    } 
}
//document.documentElement.clientHeight
window.addEventListener('scroll',(e)=>{
   let per= window.scrollY/(document.body.clientHeight-window.innerHeight);
    
   if(per>0.98){
        $('#viewMoreBtn').click();
       console.log(document.documentElement.clientHeight);
   }
});

//.searchBoxDate input:focus + #searchCalendar{
//    display: block;
//}
//
//function searchBoxBlock(e){
////    e.currentTarget.children('div').css('display','block');
//    e.childNodes[5].style.display='block';
//   
//}
//$('html').on('click',function(e){
//    
//    console.log(e.target);
//    if(e.currentTarget!=document.querySelector('searchBoxRoom') ||e.currentTarget!=document.querySelector('searchBoxDate') ){
//        $('#searchCalendar').css('display','none');
//        $('#searchRoomOption').css('display','none');
//    }
//    
//    
//    
//});

//$('#searchBoxRoom').on('click',()=>{
//   $('#searchRoomOption').css('display','block'); 
//});

//$('.fa-magnifying-glass').on('click',()=>{
//    $('.mybasketWrap').toggle('setOpacity');
////   $(e).css('opacity',1);
//    console.log(123);
//});

function setOpacity(){
    $('.mybasketWrap').toggle('setOpacity');

}