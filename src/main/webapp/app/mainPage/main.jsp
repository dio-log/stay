<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Hostel</title>
<style>
@import
	url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400&display=swap")
	;

@import
	url('https://fonts.googleapis.com/css2?family=Anton&display=swap');
</style>
<script src="https://kit.fontawesome.com/fd6f859d9b.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="../../css/default.css" />
<link rel="stylesheet" href="../../css/calendar.css" />
<link rel="stylesheet" href="../../css/main.css" />
</head>
<body>
	<c:set var='title' value=''></c:set>
	<%@ include file="nav.jsp"%>
	<div class="outerCenterCont">
		<div class="centerCont">
			<div class="emptyBoxByNav"></div>
			<form action="searchItemList.it" method="POST">
				<div id="searchCont">
					<div id="setLocationBox">
						<label for="setLocationInput"> <i
							class="fa-solid fa-map-location-dot" style="margin-right: 5px"></i>
						</label> <input id="setLocationInput" type="text" name="searchWord"
							placeholder="여행지, 숙소" required="required" />
						<div id="searchDetination">
							<div
								style="padding: 0 5px; overflow-y: auto; width: 100%; max-height: 300px;">
								<div id="searchRecent">
									<div>
										<strong>최근 검색</strong>
									</div>
									<div id="searchRecentBox"></div>
								</div>

								<!-- 	<div id="searchBestKeyword">
								<div>
									<strong>인기키워드</strong>
								</div>
								<div></div>
							</div> -->
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
			<div style="border-top: 1px solid #e6e6e6; margin-top: 20px;"></div>

			<div id="mainImgCont">
				<!-- <img class="mainImg" style="display: block;" src="../../img/main/mainImg0.jpg" alt="" /> -->
				<img class="mainImg" style="display: block;"
					src="../../img/main/mainImg1.jpg" alt="" /> <img class="mainImg"
					src="../../img/main/mainImg4.jpg" alt="" /> <img class="mainImg"
					src="../../img/main/mainImg5.jpg" alt="" /> <img class="mainImg"
					src="../../img/main/mainImg3.jpg" alt="" />
				<div id="mainImgTag">
					<h1>사진 속 주인공이 되어보세요</h1>
					<p>
						마음에 딱 맞는 옵션을 찾으실 수 있도록 <br> 호스텔에서 도와드릴게요
					<div class="br"></div>
					완벽한 곳에서 최고의 휴가를 즐겨보세요
					</p>
				</div>
			</div>
			<div style="border-top: 1px solid #e6e6e6; margin: 20px 0;"></div>
			<div id="mainTravelInfo">
				<h2>지금 필요한 여행 정보</h2>
				<div>
					<div class="mainTravelInfoImgWrap">
						<a href="https://www.yanolja.com/magazine/pension-inside/778">
							<img src="../../img/main/recomend1.jpg" alt="">
						</a>
						<p>우리들끼리 프라이빗하게
						<br>인생 풀빌라 7
						</p>
					</div>
					<div class="mainTravelInfoImgWrap">
						<a href="https://www.yanolja.com/magazine/traffic/782">
							<img src="../../img/main/recomend2.jpg" alt="">

						</a>
						<p>지나치면 섭섭한데요,
						<br>우영우가 다녀간 그 곳 !
						</p>
					</div>
					<div class="mainTravelInfoImgWrap">
						<a href="https://www.yanolja.com/magazine/travel-counselor/575">
							<img src="../../img/main/recomend3.png" alt="">
						</a>
						<p>한국에서 찾은 발리,
						<br>여수 여행 가이드
						</p>
					</div>

				</div>

			</div>
			<div style="border-top: 1px solid #e6e6e6; margin: 20px 0;"></div>
			<div id="mainEventCont">
				<h2>EVENT</h2>
				<div>
					<div id="bannerWrap">
						<img alt="" src="../../img/main/banner1.jpg"> <img alt=""
							src="../../img/main/banner2.jpg"> <img alt=""
							src="../../img/main/banner3.png"> <img alt=""
							src="../../img/main/banner4.jpg">
					</div>
				</div>
			</div>
			<div>
				<img src="../../img/main/앱다운이미지.PNG" alt="" style="width: 100%;">
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
	<script src="../../js/default.js"></script>
	<script src="../../js/calendar.js"></script>
	<script src="../../js/main.js"></script>
</body>
</html>
