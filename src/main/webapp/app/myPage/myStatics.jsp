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
<link rel="stylesheet" href="../../css/default.css" />
<link rel="stylesheet" href="../../css/myPage.css" />
</head>
<body>
	<c:set var="title" value="내 정보"></c:set>
	<%@ include file="/app/mainPage/nav.jsp"%>
	<div class="outerCenterCont">
		<div class="centerCont">
			<div class="emptyBoxByNav"></div>
			<!-- 내 정보 관리 , 예약내역,나의 후기,포인트, 찜,쿠폰함,  공지 사항-->
			<div style="display: flex; white-space: nowrap">
				<ul id="myTab">
					<c:choose>
						<c:when test="${u_div==1}">
							<li><a href="myInfo.my">내 정보 관리</a></li>
							<li><a href="myReservation.my">결제/예약내역</a></li>
							<li><a href="myPoint.my">나의 포인트</a></li>
							<li><a href="myReview.my">나의 후기</a></li>
							<li><a href="myPick.my">찜</a></li>
						</c:when>
						<c:when test="${u_div==2}">
							<li><a href="myInfo.my">내 정보 관리</a></li>
							<li><a href="itemUpload.it">객실등록</a></li>
							<li><a href="myItemManage.my">객실관리</a></li>
							<li><a href="myStatics.my"
								style="color: rgb(243, 21, 21); font-weight: 600">통계</a></li>
						</c:when>
					</c:choose>
					<li><a href="notice.my">공지사항</a></li>
				</ul>
				<div id="myPageItemManageWrap">
					<div id="monthly">

						<h4>월별 통계</h4>
						<div>
							<h3 style="position: relative;">
								<select id="itemSelect">
									<c:forEach var="itemDto" items="${itemDtoList }">
										<option value="${itemDto.item_no}">${itemDto.item_name}</option>
									</c:forEach>
								</select>
								<button id="monthlyLeftBtn">
									<i class="fa-solid fa-angle-left"></i>
								</button>
								<span id="monthlyMonth"
									style="display: inline-block; width: 100px;"></span>
								<button id="monthlyRightBtn">
									<i class="fa-solid fa-angle-right"></i>
								</button>
							</h3>
							<ul style="display: flex; padding: 20px 0">
								<li id="monthlyLeftWrap">
									<p class="fs-14" style="padding: 10px 0">이 달의 누적 판매금액</p>
									<p>
										<span id="monthSales"></span> <span>원</span>
									</p>
									<p style="color: gray; font-size: 12px">
										전월대비 <span id="comparePrevMonth"> <img alt=""
											src="../../img/main/up.png">
										</span>
									</p>
								</li>
								<li id="monthlyRightWrap">
									<table id="rightTb">
										<colgroup style="width: 100%">
											<col style="width: 10%">
											<col style="width: 50%">
											<col style="width: 10%">
											<col style="width: 30%">
										</colgroup>
										<tr>
											<td>No</td>
											<td>객실명</td>
											<td>예약</td>
											<td>판매금액</td>
										</tr>
									</table>

								</li>
							</ul>

						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<%@ include file="/app/mainPage/footer.jsp"%>
	<script src="../../js/default.js"></script>
	<script src="../../js/myStatics.js"></script>
</body>
</html>
