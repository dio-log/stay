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
		<div class="centerCont" style="width: 768px">
			<div class="emptyBoxByNav"></div>
			<div class="inner-cont">
				<div class="emptyBoxByNav" style="margin-bottom: 60px"></div>
				<form style="display: block" action="">
					<input id="agreeTotal" type="checkbox" /> <label for="agreeTotal"
						class="checkBoxStyle-24">✓</label>
					<!-- <span class="checkBoxStyle-24">✓</span> -->
					<label for="agreeTotal" class="fs-24">약관 전체 동의</label>

					<h3>필수 동의 항목</h3>
					<div id="essentialBox">
						<div>
							<input id="agreeUse" name="agreeUse" type="checkbox" required />
							<label for="agreeUse" class="checkBoxStyle-16">✓</label> <label
								for="agreeUse" class="agreeLabelSt"> [필수] 행아웃 이용약관 ></label>
						</div>
						<div>
							<input id="agreeFinance" name="agreeFinance" type="checkbox"
								required /> <label for="agreeFinance" class="checkBoxStyle-16">✓</label>
							<label for="agreeFinance" class="agreeLabelSt">[필수]
								전자금융거래 이용약관 ></label>
						</div>
						<div>
							<input id="agreePersonal" name="agreePersonal" type="checkbox"
								required /> <label for="agreePersonal" class="checkBoxStyle-16">✓</label>
							<label for="agreePersonal" class="agreeLabelSt">[필수] 개인정보
								수집동의서 ></label>
						</div>
					</div>
					<h3>선택 동의 항목</h3>
					<div id="choiceBox">
						<div>
							<input id="agreeLocation" name="agreeLocation" type="checkbox" />
							<label for="agreeLocation" class="checkBoxStyle-16">✓</label> <label
								for="agreeLocation" class="agreeLabelSt">[선택] 위치기반서비스
								이용약관 ></label>
						</div>
						<div>
							<input id="agreeMarketing" name="agreeMarketing" type="checkbox" />
							<label for="agreeMarketing" class="checkBoxStyle-16">✓</label> <label
								for="agreeMarketing" class="agreeLabelSt">[선택] 제 3자 마케팅
								활용동의서 전체동의 ></label>
						</div>

						<p style="font-size: 12px; margin: 20px 0">
							맞춤 서비스 제공을 위해 마케팅활용에 동의하여 주신 고객님께 <br /> 5천원 할인쿠폰을 드립니다
						</p>
					</div>
				</form>

				<a href="signForm.m">
					<button type="button" id="agreeNextBtn" disabled>다음 단계</button>
				</a>
			</div>
		</div>
	</div>
	<script src="../../js/memberSign.js"></script>
</body>
</html>
