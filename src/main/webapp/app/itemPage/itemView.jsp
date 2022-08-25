<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Document</title>

<style>
@import
	url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400&display=swap")
	;
</style>
<script src="https://kit.fontawesome.com/fd6f859d9b.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9b8cfe888abf4ea908007fe3bb9d7094&libraries=services,clusterer,drawing"></script>
<link rel="stylesheet" href="../../css/default.css" />
<link rel="stylesheet" href="../../css/calendar.css" />
<link rel="stylesheet" href="../../css/item.css" />
</head>
<body>
	<c:set var="title" value=""></c:set>
	<%@ include file="/app/mainPage/nav.jsp"%>
	<div class="outerCenterCont">
		<div class="centerCont" style="position: relative">

			<div class="emptyBoxByNav"></div>
			<h2 style="border-bottom: 1px solid #e6e6e6; margin-bottom: 5px">
				${itemDto.item_name}</h2>
			<div id="itemFirstCont">
				<div style="display: flex">
					<div id="itemMainImgBox">
						<div
							style="border: 1px solid #e6e6e6; width: 100%; height: 400px; display: inline-block; position: relative;">
							<img id="mainImg" src="" alt="" style="width: 100%; height: 100%"
								onerror="this.style.display='none'" />
						</div>
						<div id="itemSubImgBox">
							<!-- 이미지 반복문으로 등록 -->
							<c:set var="imgCnt" value="0"></c:set>
							<c:forEach var="imgpath" items="${itemDto.item_imgpath}">
								<c:if test="${imgCnt==0}">
									<script type="text/javascript">
										$("#mainImg").attr("src", `${imgpath}`);
									</script>
								</c:if>
								<img id="mainImg" src="${imgpath}" alt=""
									style="left:${120*imgCnt}px"
									onerror="this.style.display='none'" />
								<c:set var="imgCnt" value="${imgCnt=imgCnt+1}"></c:set>
							</c:forEach>
						</div>
					</div>
					<div id="itemMainInfoBox">
						<p style="display: flex; justify-content: space-between"></p>
						<p>
							<span style="display: inline-block; width: 80px">분류</span> <span
								style="color: gray; font-size: 14px">${itemDto.item_div}</span>
						</p>
						<div id="map"></div>
						<p>
							<span style="display: inline-block; width: 80px">소재지</span> <span><i
								class="fa-solid fa-location-dot location"
								style="color: rgb(65, 211, 65)"></i><span class="location"
								style="color: gray; font-size: 14px">지도로보기</span> </span>
						</p>
						<p id="item_addr" style="color: gray; font-size: 14px">${itemDto.item_addr}</p>
						<p style="color: gray; font-size: 14px">${itemDto.item_addr_detail}${itemDto.item_addr_extra}</p>

						<p>이벤트</p>
						<textarea id="itemMainEvent" name="item_event" cols="" rows=""
							disabled
							style="background-color: #fafafa; border-radius: 5px; border: none;">
${itemDto.item_event}</textarea>
					</div>
				</div>
			</div>
			<div id="itemSecondCont">
				<div id="itemSecLeftBox">
					<ul id="itemTabUl">
						<li><input name="itemTab" id="itemTab_room" type="radio"
							checked /><label for="itemTab_room">객실안내/예약</label></li>
						<li><input name="itemTab" id="itemTab_info" type="radio" /><label
							for="itemTab_info">숙소정보</label></li>
						<li><input name="itemTab" id="itemTab_review" type="radio" "
                /><label
							for="itemTab_review">리뷰</label></li>
					</ul>
					<!-- 디테일 -->

					<div id="itemTab_roomDetailBox">
						<!-- 정보추출해서 반복해서 넣어줄 박스 -->
						<c:forEach var="roomDto" items="${roomDtoList}">

							<div class="itemTab_roomDetailSt">
								<div style="position: relative">
									<img src='${roomDto.room_img_path.split(",")[1]}' alt="깨짐" />
									<p class="itemTab_roomDetailMore"
										data-no="${item_no}&${
          roomDto.room_no
        }">더
										보기</p>
								</div>
								<div class="itemTab_roomDetailRight">
									<p>${roomDto.room_name}</p>

									<p>
										1박 가격 <span>${roomDto.room_price}</span>
									</p>
									<c:if test="${roomDto.room_part_price!=0}">
										<p>
											대실 가격 <span>${roomDto.room_part_price}</span>
										</p>

									</c:if>

									<%-- 	<p>
									쿠폰적용가 <span>${roomDto.room_price * 0.9}</span>
								</p> --%>
									<button type="button" class="whiteBtnSt tempPayBtn"
										data-temp="${roomDto.room_name}&${roomDto.room_price}&${roomDto.max_men}&${roomDto.room_no}">
										견적보기<i class="fa-solid fa-angle-right"
											style="margin-left: 10px"></i>
									</button>
								</div>
							</div>
						</c:forEach>
					</div>

					<!-- 인포 -->
					<%
					pageContext.setAttribute("dele", "\r\n");
					%>
					<div id="itemTab_infoDetailBox" class="displayNone">
						<span>주변정보</span>

						<div class="itemInfoBox">
							${fn:replace(itemDto.item_arround,dele,"<br>")}</div>


						<span>기본 정보</span>


						<div class="itemInfoBox">
							${fn:replace(itemDto.item_basic,dele,"<br>")}</div>

						<span>공지사항</span>


						<div class="itemInfoBox">
							${fn:replace(itemDto.item_notice,dele,"<br>")}</div>
						<span>주차정보</span>


						<div class="itemInfoBox">

							${fn:replace(itemDto.item_parking,dele,"<br>")}</div>
						<span>인원추가시</span>


						<div class="itemInfoBox">

							${fn:replace(itemDto.item_addpeople,dele,"<br>")}</div>
						<span>취소 및 환불 규정</span>


						<div class="itemInfoBox">
							${fn:replace(itemDto.item_cancel_refund,dele,"<br>")}</div>
					</div>
					<div id="itemTab_reviewDetailBox" class="displayNone">
						<div id="">
							<p>
								<i class="fa-solid fa-star"></i> <i class="fa-solid fa-star"></i>
								<i class="fa-solid fa-star"></i> <i class="fa-solid fa-star"></i>
								<i class="fa-solid fa-star-half-stroke"></i>
							</p>
							<span>전체 리뷰</span> <span>${maxReview}</span> · <span>제휴점
								답변</span> <span> ${maxAnswer}</span>
						</div>
						<p>
							<button id="writeReviewBtn" class="whiteBtnSt"
								data-no="${itemDto.item_no}" type="button">후기 남기기</button>
						</p>
						<form id="reviewForm" action="insertReview.re" method="post"
							enctype="multipart/form-data">
							
							<div class="reviewContentWrap">
								<div style="width: 150px;">
									<img alt="" src="../../img/main/스마일.png" style="width: 80px">
									<p class="reviewNick">${u_nick}</p>
								</div>
								<div style="width: 100%">
									<input style="display: none" id="re_grade" name="re_grade">
									<p>
										<span>이용한 객실</span> <span id="usedRoom"> </span>
									</p>
									<p class="reviewGrade" id="serviceGrade">
										<span>서비스 & 친절도</span> <span><i
											class="fa-regular fa-star"></i><i class="fa-regular fa-star"></i><i
											class="fa-regular fa-star"></i><i class="fa-regular fa-star"></i><i
											class="fa-regular fa-star"></i> </i></span>
									</p>
									<p class="reviewGrade" id="cleanGrade">
										<span>숙소 & 객실 청결도</span> <span><i
											class="fa-regular fa-star"></i><i class="fa-regular fa-star"></i><i
											class="fa-regular fa-star"></i><i class="fa-regular fa-star"></i><i
											class="fa-regular fa-star"></i> </i> </span>
									</p>
									<p class="reviewGrade" id="convenienceGrade">
										<span>시설 & 편의성</span> <span> <i
											class="fa-regular fa-star"></i><i class="fa-regular fa-star"></i><i
											class="fa-regular fa-star"></i><i class="fa-regular fa-star"></i><i
											class="fa-regular fa-star"></i> </i>
										</span>
									</p>
									<input id="re_title" name="re_title" placeholder="제목을 입력해주세요">
									<textarea name="re_content" id="re_content" cols="" rows=""
										placeholder="방문한 곳은 어떠셨나요?"></textarea>
									<div class="reviewImgBox">
										<input type="file" id="re_imgfile" name="re_imgfile">
									</div>
									<button id="compReviewBtn" class="redBtnSt">작성 완료</button>
								</div>

							</div>
						</form>

						<!--리뷰dto array 불러서 반복붙임, 페이징-->
						<c:forEach var="reviewDto" items="${reviewDtoList}">

							<div class="reviewContentWrap">

								<p class="reviewNick">${reviewDto.re_u_nick}</p>
								<p class="reviewTitle">${reviewDto.re_title}</p>
								<p class="reviewGrade">
									<i class="fa-solid fa-star"></i> <i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star"></i> <i class="fa-solid fa-star"></i>
									<i class="fa-solid fa-star-half-stroke"></i> <span>${reviewDto.re_grade}</span>
								</p>

								<p class="reviewRoomName">${reviewDto.re_room_name}</p>
								<div>${reviewDto.re_content}</div>
								<p class="reviewContentWtime">${reviewDto.re_wtime}</p>
								<div class="reviewImgBox">
									<img src='${reviewDto.re_imgpath.split(",")[1]}' alt="">
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

				<div id="itemSecRightBox">
					<div id="totalChargeBox">
						<form id="doPayForm" action="movePayPage.pay" method="post">
						<input style="display: none" id="item_no" name="item_no" value="${itemDto.item_no}">
							<input style="display: none" id="room_no" name="room_no">
							<input style="display: none" id="p_room_name"  name="p_room_name">
							<input style="display: none" name="p_item_name" value="${itemDto.item_name}">
							<div id="calendar">
								<div id="firstCalendar" class="calendarSt">
									<div class="titleBox">
										<p class="title">
											<button onclick="prevMonth()">
												<i class="fa-solid fa-angle-left"></i>
											</button>
											<span id="firstTitle" style="margin-right: 50px"></span>
										</p>
									</div>
									<table id="firstCalendarTable"></table>
								</div>
								<div id="secondCalendar" class="calendarSt">
									<div class="titleBox">
										<p class="title">
											<span id="secondTitle" style="margin-left: 50px"></span>
											<button onclick="nextMonth()">
												<i class="fa-solid fa-angle-right"></i>
											</button>
										</p>
									</div>

									<table id="secondCalendarTable"></table>
								</div>
							</div>
							<p>
								<input id="chargeRoomPrice" class="temp_price" name="p_fee"
								 required	style="font-size: 16px; text-align: right; border: none; background-color: white; width: 80px;"
									 readonly="readonly">원
							</p>
							<input type="checkbox" id="underIcon" checked />

							<div id="dateAndMen">

								<p style="margin: 0; padding: 5px 0">
									<input id="calendarInput" name="calendarInput"
										readonly="readonly" placeholder="체크인 / 체크아웃" required>
								</p>


								<span class="fs-14" style="margin-bottom: 0;"> 성인 <input name="p_adult"
									id="p_adult" type="text" readonly="readonly"
									style="width: 10px; font-size: 16px; border: none; background-color: white;"
									value='2'>명 / 아동 <input name="p_child" id="p_child" type="text"
									readonly="readonly"
									style="width: 10px; font-size: 16px; border: none; background-color: white;"
									value='0'>명
								</span> <label for="underIcon" style="float: right"><i
									class="fa-solid fa-angle-down fs-24"></i></label> </br> <span class="fs-14"
									style="margin-top: 0"> 반려동물 <input id="p_pet" name="p_pet" 
									type="text" readonly="readonly"
									style="width: 10px; font-size: 16px; border: none; background-color: white"
									value='0'> 마리
								</span>
								<p class="chargePeople">
									성인 <span style="position: relative"> <i
										class="fa-solid fa-minus btnStCircle"></i> <input
										data-ch="adult" type="number" min="0" max="8" value="2" /> <i
										class="fa-solid fa-plus btnStCircle"></i>
									</span>
								</p>
								<p class="chargePeople">
									아동 <span> <i class="fa-solid fa-minus btnStCircle"></i>
										<input data-ch="child" type="number" min="0" max="8" value="0"
										readonly /> <i class="fa-solid fa-plus btnStCircle"></i>
									</span>
								</p>

								<p class="chargePeople">
									반려동물 <span> <i class="fa-solid fa-minus btnStCircle"></i>
										<input data-ch="pet" type="number" min="0" max="4" value="0"
										readonly /> <i class="fa-solid fa-plus btnStCircle"></i>
									</span>
								</p>
							</div>
							<p>
								<input class="temp_price"
									style="font-size: 16px; text-align: right; border: none; background-color: white; width: 80px;"
									value="0" readonly="readonly" required="required"><i
									class="fa-solid fa-xmark" style="margin-left: 10px"></i><input
									id="night"
									style="font-size: 16px; text-align: right; border: none; background-color: white; width: 20px"
									value="0">박
							</p>
							<p style="text-align: right">
								총 합계<span id="totalFee" style="margin-left: 10px"></span>원
							</p>
							<button id="doPayBtn" class="redBtnSt" style="width: 100%" type="button" data-no="${u_no}">결제하기</button>
						</form>
					</div>
				</div>
			</div>


			<div id="roomMoreBox">
				<form id="roomUploadForm" action="" method="post"
					enctype="multipart/form-data">
					<p>객실 상세 정보</p>
					<div class="roomMoreImgBox">
						<div id="roomMoreImgCont"></div>

						<button type="button">
							<i class="fa-solid fa-angle-left"></i>
						</button>
						<button type="button">
							<i class="fa-solid fa-angle-right"></i>
						</button>

						<input id="roomMoreImgInput" name="room_imgs" type="file" multiple
							accept="image/*" /> <label for="roomMoreImgInput">객실 사진을
							선택해주세요 <br /> 최대 8장까지 가능합니다<br /> 첫 번째 이미지가 상위에 노출됩니다
						</label>
					</div>
					<div id="fileListBox"></div>
					<div id="roomMoreInfoBox">
						<div id="roomMoreLeftBox">
							<ul>
								<li>
									<p>객실명</p> <input name="room_name" id="room_name" type="text"
									maxlength="16" placeholder="최대 16자리" />
								</li>
								<li><span>객실 크기</span><span><input name="room_size"
										id="room_size" type="number" placeholder="" />㎡</span></li>
								<li><span>기준 인원</span> <span> <i
										class="fa-solid fa-minus btnStCircle"></i> <input
										name="basic_men" type="number" min="0" max="8" value="2" /> <i
										class="fa-solid fa-plus btnStCircle"></i></span></li>
								<li><span>최대 인원</span> <span><i
										class="fa-solid fa-minus btnStCircle"></i> <input
										name="max_men" id="max_men" type="number" min="0" max="8"
										value="2" /> <i class="fa-solid fa-plus btnStCircle"></i></span></li>
								<li><span>침대</span> <span><select name="room_bed"
										id="bed">
											<option value="싱글">싱글</option>
											<option value="더블">더블</option>
											<option value="퀸">퀸</option>
											<option value="킹">킹</option>
									</select> <select name="room_bed_cnt" id="bedcnt">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
									</select> </span></li>
								<li><span>전망</span> <span> <select name="room_view"
										id="view">
											<option value="시티">시티</option>
											<option value="산">산</option>
											<option value="바다">바다</option>
											<option value="프라이빗">프라이빗</option>
									</select></span></li>
								<li><span>테마</span> <span> <select name="room_theme"
										id="room_theme">
											<option value="가족">가족</option>
											<option value="친구">친구</option>
											<option value="커플">커플</option>
											<option value="단체">단체</option>
											<option value="반려동물">반려동물</option>
									</select></span></li>
							</ul>
						</div>
						<div id="roomMoreRightBox">
							<p>
								숙박(1박) <input name="room_price" id="price" type="number" />원
							</p>
							<p>
								대실 <select name="room_part_price" id="partTimePrice">
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
								</select>시간 <input name="room_part_time" type="number" required />원
							</p>

							<p>부대 시설</p>
							<input id="room_extraopt" name="room_extraopt" type="text" hidden />
							<input name="room_extraopt" id="에어컨" type="checkbox" value="에어컨" />
							<label for="에어컨">에어컨</label> <input name="room_extraopt" id="TV"
								type="checkbox" value="TV" /> <label for="TV">TV</label> <input
								name="room_extraopt" id="냉장고" type="checkbox" value="냉장고 " /> <label
								for="냉장고">냉장고</label> <input name="room_extraopt" id="커피포트"
								type="checkbox" value="커피포트" /> <label for="커피포트">커피포트</label>
							<input name="room_extraopt" id="욕실용품" type="checkbox"
								value="욕실용품" /> <label for="욕실용품">욕실용품</label> <br /> <input
								name="room_extraopt" id="드라이기" type="checkbox" value="드라이기" />
							<label for="드라이기">드라이기</label> <input name="room_extraopt"
								id="비데" type="checkbox" value="비데" /> <label for="비데">비데</label>
							<input name="room_extraopt" id="개별테라스" type="checkbox"
								value="개별테라스" /> <label for="개별테라스">개별테라스</label>
						</div>
					</div>
				</form>
				<div style="display: flex; justify-content: end">
					<button id="roomMoreCancelBtn" type="button">취소</button>
					<button id="roomSaveBtn" type="button">저장</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/app/mainPage/footer.jsp"%>
	<script src="../../js/default.js"></script>
	<script src="../../js/calendar.js"></script>
	<script src="../../js/item.js"></script>
	<script src="../../js/itemView.js"></script>

</body>
</html>
