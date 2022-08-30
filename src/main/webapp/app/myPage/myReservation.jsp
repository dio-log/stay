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
							<li><a href="myReservation.my"
								style="color: rgb(243, 21, 21); font-weight: 600">결제/예약내역</a></li>
							<li><a href="myPoint.my">나의 포인트</a></li>
							<li><a href="myReview.my">나의 후기</a></li>
							<li><a href="myPick.my">찜</a></li>
						</c:when>
						<c:when test="${u_div==2}">
							<li><a href="myInfo.my">내 정보 관리</a></li>
							<li><a href="itemUpload.it">객실등록</a></li>
							<li><a href="#">객실관리</a></li>
							<li><a href="#">답변관리</a></li>
						</c:when>
					</c:choose>
					<li><a href="notice.my">공지사항</a></li>
				</ul>

				<div id="myPageContentWrap">
					<div id="myResevationWrap">
							<h4>결제</h4>

							<table>
								<colgroup style="width: 100%">
									<col style="width: 5%;">
									<col style="width: 10%">
									<col style="width: 30%">
									<col style="width: 15%">
									<col style="width: 10%">
									<col style="width: 6%">
									<col style="width: 12%">
									<col style="width: 12%">

								</colgroup>
								<tr>
									<td>No</td>
									<td>결제상태</td>
									<td>숙소이름</td>
									<td>객실타입</td>
									<td>체크인/<br>체크아웃
									</td>
									<td>적립<br>포인트
									</td>
									<td>1박 금액(원)</td>
									<td>총 금액(원)</td>
								</tr>
								<c:forEach var="paymentDto" items="${paymentDtoList}">
									<tr>
										<td>${paymentDto.p_no }</td>
										<td><c:choose><c:when test="${paymentDto.p_status=='n'}">결제대기</c:when><c:when test="${paymentDto.p_status=='y'}">결제완료</c:when><c:when test="${paymentDto.p_status=='c'}">결제취소</c:when></c:choose></td>
										<td>${paymentDto.p_item_name}</td>
										<td>${paymentDto.p_room_name }</td>
										<td>${paymentDto.p_checkin }/<br>${paymentDto.p_checkout}</td>
										<td>${paymentDto.p_point }</td>
										<td>${paymentDto.p_fee }</td>
										<td>${paymentDto.p_totalFee }</td>
									</tr>
								</c:forEach>
							</table>

						</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<%@ include file="/app/mainPage/footer.jsp"%>
	<script src="../../js/default.js"></script>
	<script src="../../js/myPage.js"></script>
</body>
</html>
