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
		<div class="centerCont"
			style="width: 768px; text-align: center; position: relative">
			<div class="emptyBoxByNav"></div>
			<div id="signSelectCont">
				<h1 style="margin: 0">Hostel</h1>
				<h4>지금 가입하시면 최대 30% 쿠폰을 쏩니다 !</h4>
				<a href="signAgree.m"><button type="button">개인 회원가입</button></a>
				<div>
					<ul id="signUlSNS">
						<li><a href=""><img src="../../img/main/naverlogo.png"
								\ alt="" /></a></li>
						<li><a href=""><img src="../../img/main/kakaologo2.png"
								alt="" /></a></li>
						<li><a href=""><img src="../../img/main/googlelogo.jpg"
								alt="" /></a></li>
					</ul>
					<p>SNS계정 회원가입(만 14세 이상 가능)</p>
				</div>
				<a href=""><button type="button">사업자 회원가입</button></a>
				<p>판매 목적의 개인/법인 사업자 가입 가능</p>
			</div>
		</div>
	</div>
	<%@ include file="/app/mainPage/footer.jsp"%>
	<script src="../../js/memberSign.js"></script>
</body>
</html>
