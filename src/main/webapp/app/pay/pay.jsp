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
<link rel="stylesheet" href="../../css/pay.css" />
</head>
<body>

	<c:set var="title" value=""></c:set>
	<%@ include file="/app/mainPage/nav.jsp"%>
	<div class="outerCenterCont">
		<div class="centerCont">
			<div class="emptyBoxByNav"></div>
			<div style="display: flex;">
				<form action="insertCart.pay" id="payForm" method="post">
					<div id="payLeftWrap">
						<div>

							<h3>예약자 정보</h3>
							<p>예약자 이름</p>

							<input type="text" id="p_name" name="p_name"
								placeholder="체크인시 필요한 정보입니다">
							<p>휴대폰 번호</p>
							<input type="text" id="p_phone" name="p_phone"
								placeholder="연락가능한 번호를 입력해주세요">
						</div>
						<div>
							<h3>할인 수단 선택</h3>
							<p>
								<span>구매총액</span> <span
									style="color: black; font-weight: 600; font-size: 20px">${paymentDto.p_totalFee}원</span>
							</p>
							<p>
								<button type='button' class="whiteBtnSt" style="font-size: 16px">내
									쿠폰</button>
								<span></span>
							</p>
							<p>
								<span>사용가능 포인트 : ${u_point} P </span> <span
									style="color: black;"> <input id="p_point"
									max="${u_point}" name="p_point" type="number" value="0">P
								</span>
							</p>
						</div>
						<div>
							<h3>결제수단 선택</h3>
							<select name="payMethod"
								style="border-radius: 5px; border: 1px solid #e6e6e6;">
								<option value="신용/체크카드">신용/체크카드</option>
								<option value="계좌이체">계좌이체</option>
							</select>
							<ul>
								<li><input type='checkbox' id="allAgree" name="allAgree">
									<label for="allAgree">전체 동의</label></li>
								<li><input type='checkbox' id="rullAgree" name="rullAgree">
									<label for="rullAgree">숙소이용규칙 및 취소/환불규정 동의</label> <span>(필수)</span>
								</li>
								<li><input type='checkbox' id="personalAgree"
									name="personalAgree"> <label for="personalAgree">개인정보
										수집 및 이용 동의</label> <span>(필수)</span></li>
								<li><input type='checkbox' id="thirdAgree"
									name="thirdAgree"> <label for="thirdAgree">개인정보
										제3자 제공 동의</label> <span>(필수)</span></li>
							</ul>

						</div>
					</div>
				<input name="item_no" value="${paymentDto.item_no}" class="displayNone">
					<input name="room_no" value="${paymentDto.room_no}" class="displayNone">
					<input name="p_checkin" value="${paymentDto.p_checkin}" class="displayNone">
					<input name="p_checkout" value="${paymentDto.p_checkout}" class="displayNone">
					<input name="p_night" value="${paymentDto.p_night}" class="displayNone">
					<input name="p_totalFee" value="${paymentDto.p_totalFee}" class="displayNone">
					<input name="p_fee" value="${paymentDto.p_fee}" class="displayNone">
				</form>
				<div id="payRightWrap">
					<p>숙소이름</p>
					<p>${paymentDto.p_item_name}</p>
					<p>객실타입</p>
					<p>${paymentDto.p_room_name}/ ${paymentDto.p_night}</p>
					<p>체크인</p>
					<p>${paymentDto.p_checkin}</p>
					<p>체크아웃</p>
					<p>${paymentDto.p_checkout }</p>
					<div>
						<h4 style='display: inline-block;'>총 결제 금액</h4>
						<span style="font-size: 14px">(VAT포함)</span>
						<h2>${paymentDto.p_totalFee}원</h2>
						<p>· 해당 객실가는 세금, 봉사료가 포함된 금액입니다</p>
						<p>· 결제완료 후 예약자 이름으로 바로 체크인 하시면 됩니다</p>
						<p>· 미성년자는 보호자 동반 시 투숙이 가능합니다.다</p>
						<p>· 취소 및 환불 규정에 따라 취소수수료 부과 및 취소불가 될 수 있습니다.</p>
					</div>
					<button id="doPayBtn" type="button" class="redBtnSt">결제하기</button>
				</div>

			</div>

			<div id="outerPayBox">

				<div id="payBox">

					<img alt="" src="../../img/main/우리은행.jpg">
					<p>1002-050-307547</p>
					<p>
						확인을 누르시면 결제대기상태가 됩니다 <br>마이페이지 결제내역에서 확인가능합니다
					</p>
					<div>

						<button class="whiteBtnSt" id="cancelBtn" type="button">취소</button>
						<button class="redBtnSt" id="compBtn" type="button">확인</button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<%@ include file="/app/mainPage/footer.jsp"%>
	<script src="../../js/default.js"></script>
	<script src="../../js/pay.js"></script>
</body>
</html>
