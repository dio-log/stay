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
	<%@ include file="/app/mainPage/nav.jsp"%>
	<div class="outerCenterCont">
		<div class="centerCont" style="width: 768px">
			<div class="emptyBoxByNav"></div>
			<div id="signCompCont">
				<i class="fa-solid fa-circle-check"
					style="font-size: 50px; color: rgb(253, 84, 84)"></i>
				<p style="font-size: 30px">회원가입이 완료되었습니다</p>
				<p style="margin-bottom: 0">회원님을 위한 웰컴쿠폰을 지급해드렸어요 !</p>
				<p style="margin-top: 0"></p>
				<a href="login.m"><button type="button">로그인페이지</button></a>
			</div>
		</div>
	</div>
	<%@ include file="/app/mainPage/footer.jsp"%>
</body>
</html>
