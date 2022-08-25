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
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="../../css/default.css" />
<link rel="stylesheet" href="../../css/item.css" />
</head>
<body>
	<c:set var="title" value="상품 등록"></c:set>
	<%@ include file="/app/mainPage/nav.jsp"%>
	<div class="outerCenterCont">
		<div class="centerCont" style="position: relative">
			<form id="itemUploadForm" action="insertItem.it" method="post"
				enctype="multipart/form-data">
				<div class="emptyBoxByNav"></div>
				<div id="itemFirstCont">
					<div style="display: flex">
						<div id="itemMainImgBox">
							<div
								style="border: 1px solid black; width: 100%; height: 400px; display: inline-block; position: relative;">
								<img id="mainImg" src="" alt=""
									style="width: 100%; height: 100%"
									onerror="this.style.display='none'" /> <label
									id="itemMainImgLabel" for="itemMainImgInput"> 사진을
									선택해주세요 </label> <input id="itemMainImgInput" name="item_imgpath"
									type="file" multiple accept="image/*" />
							</div>
							<div id="itemSubImgBox">
								<!-- 이미지 반복문으로 등록 -->
								<div></div>
							</div>
						</div>
						<div id="itemMainInfoBox">
							<p style="display: flex; justify-content: space-between">
								<span>숙소명</span> <input id="item_name" name="item_name"
									type="text" />
							</p>
							<p style="display: flex; justify-content: space-between">
								<span>분류</span> <select name="item_div " id="item_div ">
									<option value="모텔">모텔</option>
									<option value="호텔">호텔</option>
									<option value="리조트">리조트</option>
									<option value="풀빌라">풀빌라</option>
									<option value="펜션">펜션</option>
									<option value="게스트하우스">게스트하우스</option>
									<option value="캠핑">캠핑</option>
								</select>
							</p>

							<p>소재지</p>
							<div id="addrBox">
								<input type="text" id="item_addr" name="item_addr"
									placeholder="주소" style="width: 100%" /> <br />
								<input type="text" id="item_addr_detail" name="item_addr_detail"
									placeholder="상세주소" /> <input type="text"
									id="item_addr_extra" name="item_addr_extra" placeholder="참고항목" />
								<input class="whiteBtnSt" type="button"
									onclick="sample6_execDaumPostcode()" value="주소 찾기"
									style="font-size: 16px; padding: 0 5px" />
							</div>
							<p>이벤트</p>
							<textarea id="itemMainEvent" name="item_event" cols="" rows=""></textarea>
						</div>
					</div>
				</div>
				<div id="itemSecondCont">
					<div id="itemSecLeftBox">
						<ul id="itemTabUl">
							<li><input name="itemTab" id="itemTab_room" type="radio"
								checked /><label for="itemTab_room">객실안내/예약</label>
							</li>
							<li><input name="itemTab" id="itemTab_info" type="radio" /><label
								for="itemTab_info">숙소정보</label></li>
						</ul>
						<!-- 디테일 -->

						<div id="itemTab_roomDetailBox">
							<button id="addRoomBtn" type="button" class="whiteBtnSt">
								객실 추가</button>
							<!-- 정보추출해서 반복해서 넣어줄 박스 -->
						</div>

						<!-- 인포 -->

						<div id="itemTab_infoDetailBox" class="displayNone">
							<p style="font-size: 14px; margin: 10px 0; color: gray">
								<i class="fa-solid fa-circle-exclamation" data-ex="ex"></i>아이콘을
								누르면 각 항목별 예시를 보실 수 있습니다
							</p>

							<span>주변정보</span> <i class="fa-solid fa-circle-exclamation"
								data-ex="ex"></i>
							<textarea name="item_arround" id="" class="itemInfoTextarea"
								cols="" rows="">· 신논현역 도보 1분
· 강남역 도보 5분</textarea>
							<div class="writeEx" data-ex="ex">
								· 홍대입구역 도보 3분<br /> · 연트럴파크 도보 1분<br /> · 홍대거리 도보 5분
							</div>

							<span>기본 정보</span> <i class="fa-solid fa-circle-exclamation"
								data-ex="ex"></i>

							<textarea name="item_basic" id="" class="itemInfoTextarea"
								cols="" rows="">· 체크인 : 15시~ | 체크아웃 : ~11시
· 22시 이후 체크인 시 호텔 프론트 문의
· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다
· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)
· 애완동물 출입 금지입니다
· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다
· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)</textarea>
							<div class="writeEx" data-ex="ex">
								· 체크인 : 15시~ | 체크아웃 : ~11시<br /> · 22시 이후 체크인 시 호텔 프론트 문의<br />
								· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다 <br /> · 전 객실 금연 (흡연 적발 시
								300,000원 위약금이 발생됩니다)<br /> · 애완동물 출입 금지입니다 <br /> · 시설물을 파손하거나
								침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다 <br /> · 보호자 동반 없는 미성년자 입실
								불가 (업체 문의 필수)
							</div>

							<span>공지사항</span> <i class="fa-solid fa-circle-exclamation"
								data-ex="ex"></i>
							<textarea name="item_notice" id="" class="itemInfoTextarea"
								cols="" rows="">· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다
· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)
· 애완동물 출입 금지입니다
· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다
· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)</textarea>
							<div class="writeEx" data-ex="ex">
								· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다 <br /> · 전 객실 금연 (흡연 적발 시
								300,000원 위약금이 발생됩니다)<br /> · 애완동물 출입 금지입니다 <br /> · 시설물을 파손하거나
								침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다 <br /> · 보호자 동반 없는 미성년자 입실
								불가 (업체 문의 필수)
							</div>

							<span>주차정보</span> <i class="fa-solid fa-circle-exclamation"
								data-ex="ex"></i>
							<textarea name="item_parking" id="" class="itemInfoTextarea"
								cols="" rows="">· 주차 가능 (발렛파킹만 가능 / 박 당 10,000원)</textarea>
							<div class="writeEx" data-ex="ex">
								· 숙소 앞 무료주차 가능 (업체 문의 필수) 또는<br /> · 주차 가능 (발렛파킹만 가능 / 박 당
								10,000원)
							</div>
							<span>인원추가시</span> <i class="fa-solid fa-circle-exclamation"
								data-ex="ex"></i>
							<textarea name="item_addpeople" id="" class="itemInfoTextarea"
								cols="" rows="">· 기준인원 외 추가 시, 1인 33,000원 (만 12세 이상~성인), 만 12세 미만 무료
· 영유아 인원수 포함, 최대인원 초과 불가
· 현장 결제</textarea>
							<div class="writeEx" data-ex="ex">
								· 기준인원 외 추가 시, 1인 33,000원 (만 12세 이상~성인), 만 12세 미만 무료 <br /> ·
								영유아 인원수 포함, 최대인원 초과 불가<br /> · 현장 결제
							</div>
							<span>취소 및 환불 규정</span> <i class="fa-solid fa-circle-exclamation"
								data-ex="ex"></i>
							<div class="writeEx" data-ex="ex">
								· 체크인일 기준 7일 전 : 100% 환불<br /> · 체크인일 기준 6 ~ 4일 전 : 50% 환불<br />
								· 체크인일 기준 3일 전 ~ 당일 및 No-Show : 환불 불가<br /> · 취소, 환불 시 수수료가 발생할
								수 있습니다
							</div>
							<textarea name="item_cancel_refund" id="" cols="" rows=""
								class="itemInfoTextarea">· 체크인일 기준 7일 전 : 100% 환불
· 체크인일 기준 6 ~ 4일 전 : 50% 환불
· 체크인일 기준 3일 전 ~ 당일 및 No-Show : 환불 불가
· 취소, 환불 시 수수료가 발생할 수 있습니다</textarea>
						</div>
					</div>
					<div>
						<button class="redBtnSt"
							style="position: absolute; bottom: 20px; right: 0px">
							상품 등록</button>
					</div>
				</div>
			</form>

			<!--작성 후 ajax 로 날리고 포맷 -->
			<div id="roomMoreBox">
				<form id="roomUploadForm" action="" method="post"
					enctype="multipart/form-data">
					<p>객실 상세 정보</p>
					<div class="roomMoreImgBox">
						<div id="roomMoreImgCont"></div>

						<button type="button">
							<i class="fa-solid fa-angle-left"></i>
						</button>
						<button type="button">
							<i class="fa-solid fa-angle-right"></i>
						</button>

						<input id="roomMoreImgInput" name="room_imgs" type="file" multiple
							accept="image/*" /> <label for="roomMoreImgInput">객실
							사진을 선택해주세요 <br /> 최대 8장까지 가능합니다<br /> 첫 번째 이미지가 상위에 노출됩니다
						</label>
					</div>
					<div id="fileListBox"></div>
					<div id="roomMoreInfoBox">
						<div id="roomMoreLeftBox">
							<ul>
								<li>
									<p>객실명</p> <input name="room_name" id="room_name" type="text"
									maxlength="40" placeholder="최대 40자리" />
								</li>
								<li><span>객실 크기</span><span><input
										name="room_size" id="room_size" type="number" placeholder="" />㎡</span>
								</li>
								<li><span>기준 인원</span> <span> <i
										class="fa-solid fa-minus btnStCircle"></i> <input
										name="basic_men" type="number" min="0" max="8" value="2" /> <i
										class="fa-solid fa-plus btnStCircle"></i></span></li>
								<li><span>최대 인원</span> <span><i
										class="fa-solid fa-minus btnStCircle"></i> <input
										name="max_men" id="max_men" type="number" min="0" max="8"
										value="2" /> <i class="fa-solid fa-plus btnStCircle"></i></span></li>
								<li><span>침대</span> <span><select
										name="room_bed" id="bed">
											<option value="싱글">싱글</option>
											<option value="더블">더블</option>
											<option value="퀸">퀸</option>
											<option value="킹">킹</option>
									</select> <select name="room_bed_cnt" id="bedcnt">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
									</select> </span></li>
								<li><span>전망</span> <span> <select name="room_view"
										id="view">
											<option value="시티">시티</option>
											<option value="산">산</option>
											<option value="바다">바다</option>
											<option value="프라이빗">프라이빗</option>
									</select></span></li>
								<li><span>테마</span> <span> <select name="room_theme"
										id="room_theme">
											<option value="가족">가족</option>
											<option value="친구">친구</option>
											<option value="커플">커플</option>
											<option value="단체">단체</option>
											<option value="반려동물">반려동물</option>
									</select></span></li>
							</ul>
						</div>
						<div id="roomMoreRightBox">
							<p>
								숙박(1박) <input name="room_price" id="price" type="number" />원
							</p>
							<p>
								대실 <select name="room_part_time" id="partTimePrice">
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
								</select>시간 <input name="room_part_price" type="number" required />원
							</p>

							<p>부대 시설</p>
							<input id="room_extraopt" name="room_extraopt" type="text" hidden />
							<input name="room_extraopt" id="에어컨" type="checkbox" value="에어컨" />
							<label for="에어컨">에어컨</label> <input name="room_extraopt" id="TV"
								type="checkbox" value="TV" /> <label for="TV">TV</label> <input
								name="room_extraopt" id="냉장고" type="checkbox" value="냉장고 " />
							<label for="냉장고">냉장고</label> <input name="room_extraopt"
								id="커피포트" type="checkbox" value="커피포트" /> <label
								for="커피포트">커피포트</label> <input name="room_extraopt" id="욕실용품"
								type="checkbox" value="욕실용품" /> <label
								for="욕실용품">욕실용품</label> <br /> <input name="room_extraopt"
								id="드라이기" type="checkbox" value="드라이기" /> <label
								for="드라이기">드라이기</label> <input name="room_extraopt" id="비데"
								type="checkbox" value="비데" /> <label for="비데">비데</label>
							<input name="room_extraopt" id="개별테라스" type="checkbox"
								value="개별테라스" /> <label for="개별테라스">개별테라스</label>
						</div>
					</div>
				</form>
				<div style="display: flex; justify-content: end">
					<button id="roomMoreCancelBtn" type="button">취소</button>
					<button id="roomSaveBtn" type="button">저장</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/app/mainPage/footer.jsp"%>
	<script src="../../js/default.js"></script>
	<script src="../../js/item.js"></script>
</body>
</html>
