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
	<div class="centerCont">
		<div class="emptyBoxByNav"></div>
		<!-- 내 정보 관리 , 예약내역,나의 후기,포인트, 찜,쿠폰함,  공지 사항-->
		<div style="display: flex; white-space: nowrap">
			<ul id="myTab">
				<li><a href="myInfo.my">내 정보 관리</a></li>
				<li><a href="myReservation.my">결제/예약내역</a></li>
				<li><a href="myPoint.my">포인트</a></li>
				<li><a href="myReview.my"
					style="color: rgb(243, 21, 21); font-weight: 600">나의 후기</a></li>

				<li><a href="myPick.my">찜</a></li>
			</ul>

			<div id="myPageContentWrap">
				<!-- 	//아이템이름, 타이틀, 평점 작성시간 -->
				<div id="myReviewWrap">
					<h3>후기</h3>
					<table>
						<colgroup style="width: 100%">
							<col style="width: 20%;">
							<col style="width: 50%">
							<col style="width: 15%">
							<col style="width: 15%">
						</colgroup>
						<tr>
							<td>상품</td>
							<td>제목</td>
							<td>평점</td>
							<td>작성시간</td>
						</tr>
					</table>

					<c:forEach var="reviewDto" items="${reviewDtoList}">

						<tr>
							<a href="itemView.it?item_no=${reviewDto.item_no }">
								<td>${reviewDto.re_item_name }</td>
								<td>${reviewDto.re_title }</td>
								<td>${reviewDto.re_grade }</td>
								<td>${reviewDto.re_wtime }</td>
							</a>
						</tr>
					</c:forEach>
				</div>
			</div>
			<div id="pointListBox"></div>
		</div>
	</div>
	<script src="../../js/default.js"></script>
	<script src="../../js/myPage.js"></script>
</body>
</html>
