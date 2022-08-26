<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9b8cfe888abf4ea908007fe3bb9d7094&libraries=services,clusterer,drawing"></script>
<link rel="stylesheet" href="../../css/main.css" />
<link rel="stylesheet" href="../../css/default.css" />
<link rel="stylesheet" href="../../css/calendar.css" />
<link rel="stylesheet" href="../../css/itemList.css" />
</head>

<body>
	<c:set var="title" value=""></c:set>
	<%@ include file="/app/mainPage/nav.jsp"%>
	<div class="outerCenterCont">
		<div class="centerCont">
			<div class="emptyBoxByNav"></div>
			<form action="searchItemList.it" method="POST">
				<div id="searchCont">
					<div id="setLocationBox">
						<label for="setLocationInput"> <i
							class="fa-solid fa-map-location-dot" style="margin-right: 5px"></i>
						</label> <input id="setLocationInput" type="text" name="searchWord"
							placeholder="여행지, 숙소" />
						<div id="searchDetination">
							<!-- 포커스했을땐 최근검색-->
							<!-- 검색할땐 검색키워드 자동검색 -->
							<div
								style="padding: 0 5px; overflow-y: auto; width: 100%; max-height: 300px;">
								<div id="searchRecent">
									<div>
										<strong>최근 검색</strong>
									</div>
									<div id="searchRecentBox"></div>
								</div>

							</div>
						</div>
					</div>
					<div id="setCalendarBox">
						<label for="setCalendarInput"><i
							class="fa-solid fa-calendar-week"></i></label> <input id="calendarInput"
							name="calendarInput" type="text" placeholder="날짜"
							readonly="readonly" />
						<div id="calendar" style="display: none">
							<div id="firstCalendar" class="calendarSt">
								<div class="titleBox">
									<p class="title">
										<button type="button" onclick="prevMonth()">
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
										<button type="button" onclick="nextMonth()">
											<i class="fa-solid fa-angle-right"></i>
										</button>
									</p>
								</div>

								<table id="secondCalendarTable"></table>
							</div>
						</div>
					</div>

					<div id="setPeopleBox">
						<label for="setPeopleInput"><i class="fa-solid fa-users"></i></label>
						<input id="peopleInput" name="peopleInput" type="text"
							placeholder="인원" />
						<div id="setPeople">
							<div class="setPeopleContent">
								<p>
									<span>성인</span><span><i
										class="fa-solid fa-minus minusBtn"></i><input
										class="setPeopleAdult" type="number" min="0" max="8" value="2"
										disabled><i class="fa-solid fa-plus plusBtn"></i></span>
								</p>
								<p>
									<span>아동 <span style="font-size: 12px; color: gray;">만
											18세미만</span></span><span><i class="fa-solid fa-minus minusBtn"></i><input
										class="setPeopleKids" type="number" min="0" max="8" value="0"
										disabled><i class="fa-solid fa-plus plusBtn"></i></span>
								</p>
								<p>
									<span>반려동물</span><span><i
										class="fa-solid fa-minus minusBtn"></i><input
										class="setPeoplePet" type="number" min="0" max="8" value="0"
										disabled><i class="fa-solid fa-plus plusBtn"></i></span>
								</p>

							</div>
							<p style="text-align: center;">
								<button id="setPeopleBtn" c type="button" class="whiteBtnSt">선택
									완료</button>
							</p>
						</div>
					</div>
					<div style="display: flex; align-items: center; padding-left: 0;">
						<button id="searchBtn" class="redBtnSt" type="submit">검색</button>
					</div>
				</div>
			</form>
			<div class="midCont">
				<div>
					<div class="filterWrap">
						<div style="text-align: center; margin-bottom: 10px">

							<button type="button" id="searchMapBtn">
								<i class="fa-solid fa-map-location-dot"
									style="margin-right: 5px"></i>지도 보기
								<div id="map"></div>
							</button>
						</div>
						<form id="filterForm" action="">
							<button id="filterBtn" class="whiteBtnSt" style="width: 100%;">
								필터 적용</button>
							<ul>
								<li><h3>가격대</h3></li>
								<li><progress id="progress" min="0" max="100"></progress></li>
								<li><input type="text" /> <input type="text" /></li>
							</ul>
							<ul>
								<li><h3>숙소 유형</h3></li>
								<li><input id="" name="room_div" type="checkbox" value="모텔" /><label
									class="cb-square-16 filterCbLabel" for="room_div">✓</label><label
									class="optLabel" for="room_div">모텔</label></li>
								<li><input id="" name="room_div" type="checkbox" value="호텔" /><label
									class="cb-square-16 filterCbLabel" for="room_div">✓</label><label
									class="optLabel" for="room_div">호텔</label></li>
								<li><input id="" name="room_div" type="checkbox"
									value="리조트" /><label class="cb-square-16 filterCbLabel"
									for="room_div">✓</label><label class="optLabel" for="room_div">리조트</label></li>
								<li><input id="" name="room_div" type="checkbox" value="펜션" /><label
									class="cb-square-16 filterCbLabel" for="room_div">✓</label><label
									class="optLabel" for="room_div">펜션</label></li>
								<li><input id="" name="room_div" type="checkbox"
									value="게스트하우스" /><label class="cb-square-16 filterCbLabel"
									for="room_div">✓</label><label class="optLabel" for="room_div">게스트하우스</label></li>
								<li><input id="" name="room_div" type="checkbox" value="캠핑" /><label
									class="cb-square-16 filterCbLabel" for="room_div">✓</label><label
									class="optLabel" for="room_div">캠핑</label></li>
							</ul>
							<ul>
								<li><h3>테마</h3></li>
								<li><input id="" name="room_theme" type="checkbox"
									value="가족" /><label class="cb-square-16 filterCbLabel"
									for="room_theme">✓</label><label class="optLabel"
									for="room_theme">가족</label></li>
								<li><input id="" name="room_theme" type="checkbox"
									value="단체" /><label class="cb-square-16 filterCbLabel"
									for="room_theme">✓</label><label class="optLabel"
									for="room_theme">단체</label></li>
								<li><input id="" name="room_theme" type="checkbox"
									value="친구" /><label class="cb-square-16 filterCbLabel"
									for="room_theme">✓</label><label class="optLabel"
									for="room_theme">친구</label></li>
								<li><input id="" name="room_theme" type="checkbox"
									value="커플" /><label class="cb-square-16 filterCbLabel"
									for="room_theme">✓</label><label class="optLabel"
									for="room_theme">커플</label></li>
								<li><input id="" name="room_theme" type="checkbox"
									value="반려동물" /><label class="cb-square-16 filterCbLabel"
									for="room_theme">✓</label><label class="optLabel"
									for="room_theme">반려동물</label></li>
							</ul>
							<ul>
								<li>
									<h3>부대시설</h3>
								</li>

								<li><input id="" name="room_extraopt" type="checkbox"
									value="마트/편의점" /><label class="cb-square-16 filterCbLabel"
									for="room_extraopt">✓</label><label class="optLabel"
									for="room_extraopt">마트/편의점</label></li>
								<li><input id="" name="room_extraopt" type="checkbox"
									value="바베큐장" /><label class="cb-square-16 filterCbLabel"
									for="room_extraopt">✓</label><label class="optLabel"
									for="room_extraopt">바베큐장</label></li>
								<li><input id="" name="room_extraopt" type="checkbox"
									value="주차장" /><label class="cb-square-16 filterCbLabel"
									for="room_extraopt">✓</label><label class="optLabel"
									for="room_extraopt">주차장</label></li>
								<li><input id="" name="room_extraopt" type="checkbox"
									value="테라스" /><label class="cb-square-16 filterCbLabel"
									for="room_extraopt">✓</label><label class="optLabel"
									for="room_extraopt">테라스</label></li>
								<li><input id="" name="room_extraopt" type="checkbox"
									value="조식" /><label class="cb-square-16 filterCbLabel"
									for="room_extraopt">✓</label><label class="optLabel"
									for="room_extraopt">조식</label></li>
								<li><input id="" name="room_extraopt" type="checkbox"
									value="스파" /><label class="cb-square-16 filterCbLabel"
									for="room_extraopt">✓</label><label class="optLabel"
									for="room_extraopt">스파</label></li>
								<li><input id="" name="room_extraopt" type="checkbox"
									value="세탁" /><label class="cb-square-16 filterCbLabel"
									for="room_extraopt">✓</label><label class="optLabel"
									for="room_extraopt">세탁</label></li>
								<li><input id="" name="room_extraopt" type="checkbox"
									value="야외수영장" /><label class="cb-square-16 filterCbLabel"
									for="room_extraopt">✓</label><label class="optLabel"
									for="room_extraopt">야외수영장</label></li>
							</ul>
						</form>
					</div>
				</div>
				<div class="itemListWrap">
					<div
						style="display: flex; justify-content: space-between; border-bottom: 1px solid #e6e6e6;">
						<h2 id="beSearchedWord" data-search="${searchWord }">
							'${searchWord }' <span style="font-size: 20px">검색결과</span>
						</h2>
						<ul id="sortUl">
							<li class="sortBy" data-sortby="byGrade">높은 평점순</li>
							<li class="sortBy" data-sortby="byPrice">낮은 요금순</li>
							<li class="sortBy" data-sortby="byReview">리뷰 많은순</li>
						</ul>
					</div>
					<ul id="itemListWrap" style="margin-top: 5px">
						<c:if test="${itemDtoList==null}">
							<li style="text-align: center; margin-top: 50px">
								<h3>검색하신 상품은 존재하지 않습니다</h3>
							</li>
						</c:if>
						<c:forEach var="itemDto" items="${itemDtoList}">
							<li><a href="itemView.it?item_no=${itemDto.item_no}"> <img
									src="${itemDto.item_imgpath.split(',')[1]}" alt="" /> <input
									type="checkbox" class="cbSt-heart" style="display: none" /><label
									for="item_no1"><i class="fa-solid fa-heart heartStyle"></i></label>
									<div class="itemTitle">
										<h1 style="margin-bottom: 5px">${itemDto.item_name}</h1>
										<div>
											<p>
												<span>${itemDto.item_div}</span> <span></span>
											</p>
											<p style="color: rgb(250, 187, 93)">
												<span>${itemDto.item_grade}</span><span>(${itemDto.item_reviewCnt})</span>
											</p>
										</div>
										<div style="text-align: right">
											<p></p>
											<p>${itemDto.item_room_price}원</p>
										</div>
									</div>
							</a></li>
						</c:forEach>


					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/app/mainPage/footer.jsp"%>
	<script src="../../js/default.js"></script>
	<script src="../../js/calendar.js"></script>
	<script src="../../js/itemList.js"></script>

</body>
</html>
