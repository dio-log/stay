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
<link rel="stylesheet" href="../../css/memberSign.css" />
</head>
<body>
	<c:set var='title' value='회원가입'></c:set>
	<%@ include file="/app/mainPage/nav.jsp"%>
	<div class="outerCenterCont">
		<!-- body아래서 가로정렬 박스 -->
		<div class="centerCont" style="width: 768px">
			<div class="emptyBoxByNav"></div>
			<form id="signForm" action="signComp.m" method="post">
				<ul>
					<li>아이디</li>
					<li><input id="u_id" name="u_id" type="text" required
						placeholder="6~14자 영문, 숫자" /></li>
					<li>
						<p id="idWarning" class="warningPharses"></p>
					</li>
					<li>비밀번호</li>
					<li><input id="pw1" name="u_pw" type="password" required
						placeholder="10~20자 영문, 숫자" /></li>
					<li>비밀번호 확인</li>
					<li><input id="pw2" name="pw2" type="password" required
						placeholder="10~20자 영문, 숫자" /></li>
					<li><p id="pwWarning" class="warningPharses"></p></li>
					<li>이름</li>
					<li><input id="u_name" name="u_name" type="text" required /></li>
					<li>닉네임</li>
					<li><input name="u_nick" type="text"></li>
					<li>이메일</li>
					<li><input id="u_email" name="u_email" type="email" required
						placeholder="abcd@gmail.com" /></li>
					<li>
						<p class="warningPharses">비밀번호 찾기 시 사용되니, 정확한 이메일 주소를 작성해주세요</p>
					</li>
					<li>휴대폰</li>
					<li><input id="u_phone" name="u_phone" type="tel" required
						style="width: 500px" />
						<button id="phoneCertBtn" type="button">인증번호 받기</button></li>
				</ul>
				<div id="signFormAgreeBox">
					<ul>
						<li><input id="agreePhoneMarketing"
							name="agreePhoneMarketing" type="checkbox" /> <label
							for="agreePhoneMarketing" class="checkBoxStyle-16">✓</label> <label
							for="agreePhoneMarketing"> SMS, 이메일로 상품 및 이벤트 정보를
								받겠습니다(선택) </label></li>
						<li><input id="agreeAge" name="agreeAge" type="checkbox"
							required /> <label for="agreeAge" class="checkBoxStyle-16">✓</label>
							<label for="agreeAge">14세 이상입니다</label> <span
							style="font-size: 14px; color: gray;">(14세미만은 가입이 불가합니다)</span></li>
					</ul>

				</div>
				<button type="submit" id="signCompleteBtn" disabled>가입완료</button>
		</div>
		</form>
	</div>
	<%@ include file="/app/mainPage/footer.jsp"%>
	<script src="../../js/memberSign.js"></script>
</body>
</html>
