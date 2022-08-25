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
					<li><a href="myInfo.my"
						style="color: rgb(243, 21, 21); font-weight: 600">내
							정보 관리</a></li>
					<c:choose>
						<c:when test="${u_div==1}">
							<li><a href="myReservation.my">결제/예약내역</a></li>
							<li><a href="myPoint.my">나의 포인트</a></li>
							<li><a href="myReview.my">나의 후기</a></li>
							<li><a href="myPick.my">찜</a></li>
						</c:when>
						<c:when test="${u_div==2}">
							<li><a href="itemUpload.it">객실등록</a></li>
							<li><a href="#">객실관리</a></li>
							<li><a href="#">답변관리</a></li>
						</c:when>
					</c:choose>


				</ul>

				<div id="myPageContentWrap">
					<div id="myPagePwCheckWrap">
						<p style="margin-bottom: 30px">
							회원님의 소중한 개인정보를 안전하게 보호하기위해 <br /> 로그인된 계정을 한번 더 인증해주세요
						</p>
						<span>비밀번호</span> <input id="myPagePwCheckInput" type="password" />
						<button type="button" id="myPagePwCheckBtn"
							class="whiteBtnSt fs-14">확인</button>
					</div>
					<div id="myPageModifyWrap" style="display: none">
						<ul>
							<li><i class="fa-solid fa-face-grin-wide fs-32"></i></li>
							<li><span>아이디</span> <span id="u_id"></span></li>
							<li><span>이메일</span> <span id="u_email"></span></li>
							<li><span>닉네임</span> <span id="u_nick">${u_nick}</span> <br />
								<input id="modifyNick" type="text" placeholder="변경할 닉네임 입력" />
								<p id="nickWarning"></p></li>
							<li><span>휴대폰번호</span> <span id="u_phone"></span> <br /> <input
								id="modifyPhoneInput" type="number" placeholder="변경할 휴대폰 번호 입력" />

								<button id="getAccessNum" class="whiteBtnSt"
									style="font-size: 16px" disabled>
									인증번호 받기</button> <br /> <input id="modifyPhoneAccessNum" type="number"
								placeholder="인증번호 입력" />

								<button class="whiteBtnSt" style="font-size: 16px" disabled>
									인증하기</button>
								<p id="phoneWarning"></p></li>
							<li>
								<p>광고성 정보 수신동의</p> <input id="foreignMarketing" type="checkbox"
								value="foreignMarketing" /> <label
								for="foreignMarketing">제 3자 마케팅</label> <input id="smsMarketing"
								type="checkbox" value="smsMarketing" /> <label
								for="smsMarketing">SMS 및 이메일</label>
							</li>
							<li>
								<button id="modifyCancelBtn" class="whiteBtnSt">수정취소</button>
								<button class="redBtnSt" id="modifyCompBtn">수정완료</button>
							</li>
							<li>
								<hr /> <a href="#" style="font-size: 14px"> 회원탈퇴 </a>
							</li>
						</ul>
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
