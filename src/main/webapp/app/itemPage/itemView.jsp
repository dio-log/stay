<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Document</title>

    <style>
      @import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400&display=swap");
    </style>
    <script
      src="https://kit.fontawesome.com/fd6f859d9b.js"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://code.jquery.com/jquery-3.6.0.js"
      integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
      crossorigin="anonymous"
    ></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link rel="stylesheet" href="../../css/default.css" />
    <link rel="stylesheet" href="../../css/item.css" />
  </head>
  <body>
    <c:set var="title" value=""></c:set>
    <%@ include file="/app/mainPage/nav.jsp" %>

    <div class="centerCont" style="position: relative">
      <form
        id="itemUploadForm"
        action="insertItem.it"
        method="post"
        enctype="multipart/form-data"
      >
        <div class="emptyBoxByNav"></div>
        <h2 style="border-bottom: 1px solid #e6e6e6; margin-bottom: 5px">
          ${itemDTO.item_name} eddd
        </h2>
        <div id="itemFirstCont">
          <div style="display: flex">
            <div id="itemMainImgBox">
              <div
                style="
                  border: 1px solid #e6e6e6;
                  width: 100%;
                  height: 400px;
                  display: inline-block;
                  position: relative;
                "
              >
                <img
                  id="mainImg"
                  src=""
                  alt=""
                  style="width: 100%; height: 100%"
                  onerror="this.style.display='none'"
                />
              </div>
              <div id="itemSubImgBox">
                <!-- 이미지 반복문으로 등록 -->
                <c:forEach var="imgpath" items="${itemDTO.item_imgpath}">
                  <img
                    id="mainImg"
                    src="${imgpath}"
                    alt=""
                    style="width: 100%; height: 100%"
                    onerror="this.style.display='none'"
                  />
                </c:forEach>
              </div>
            </div>
            <div id="itemMainInfoBox">
              <p style="display: flex; justify-content: space-between"></p>
              <p style="display: flex; justify-content: space-between">
                <span>분류</span>
                <span>${itemDTO.item_div}</span>
              </p>

              <p>소재지</p>
              <p>
                ${itemDTO.item_addr} ${itemDTO.item_addr_detail}
                ${itemDTO.item_addr_extra}
              </p>

              <p>이벤트</p>
              <textarea
                id="itemMainEvent"
                name="item_event"
                cols=""
                rows=""
                disabled
                style="
                  background-color: #fafafa;
                  border-radius: 5px;
                  border: none;
                "
              >
${itemDTO.item_event}</textarea
              >
            </div>
          </div>
        </div>
        <div id="itemSecondCont">
          <div id="itemSecLeftBox">
            <ul id="itemTabUl">
              <li>
                <input
                  name="itemTab"
                  id="itemTab_room"
                  type="radio"
                  checked
                /><label for="itemTab_room">객실안내/예약</label>
              </li>
              <li>
                <input name="itemTab" id="itemTab_info" type="radio" /><label
                  for="itemTab_info"
                  >숙소정보</label
                >
              </li>
              <li>
                <input name="itemTab" id="itemTab_review" type="radio" "
                /><label for="itemTab_review">리뷰</label>
              </li>
            </ul>
            <!-- 디테일 -->

            <div id="itemTab_roomDetailBox">
              <!-- 정보추출해서 반복해서 넣어줄 박스 -->
            </div>

            <!-- 인포 -->

            <div id="itemTab_infoDetailBox" class="displayNone">
              <span>주변정보</span>
              <textarea
                class="itemInfoTextarea"
                name="item_arround"
                id=""
                cols=""
                rows=""
                disabled
              >
${itemDTO.item_arround}</textarea
              >

              <span>기본 정보</span>

              <textarea
                class="itemInfoTextarea"
                name="item_basic"
                id=""
                cols=""
                rows=""
                disabled
              >
${itemDTO.item_basic}</textarea
              >

              <span>공지사항</span>
              <textarea
                class="itemInfoTextarea"
                name="item_notice"
                id=""
                cols=""
                rows=""
                disabled
              >
${itemDTO.item_notice}</textarea
              >

              <span>주차정보</span>

              <textarea
                class="itemInfoTextarea"
                name="item_parking"
                id=""
                cols=""
                rows=""
                disabled
              >
${itemDTO.item_parking}</textarea
              >

              <span>인원추가시</span>
              <textarea
                class="itemInfoTextarea"
                name="item_addpeople"
                id=""
                cols=""
                rows=""
                disabled
              >
${itemDTO.item_addpeople}
              </textarea>

              <span>취소 및 환불 규정</span>

              <textarea
                name="item_cancel_refund"
                class="itemInfoTextarea"
                cols=""
                rows=""
                disabled
              >
${itemDTO.item_cancel_refund}</textarea
              >
            </div>
            <div id="itemTab_reviewDetailBox" class="displayNone">
              <div id="">
                <p>평점</p>
                <span>전체 리뷰</span>
                <span>제휴점 답변</span>
              </div>
              <!--리뷰dto array 불러서 반복붙임, 페이징-->
              <c:forEach var="reviewDto" test="reviewDtoArr">
                <div class="reviewNick">${reviewDto.u_nick}</div>
                <div class="reviewContentWrap">
                  <p class="reviewTitle">${reviewDto.re_title}</p>
                  <p class="reviewGrade">
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star-half-stroke"></i>
                    <span>${reviewDto.re_grade}</span>
                  </p>

                  <p class="reviewRoomName">${reviewDto.room_name}</p>
                  <textarea name="" id="" cols="" rows="" disabled>${reviewDto.re_content}</textarea>
                  <p class="reviewContentWtime">${reviewDto.re_wtime}</p>
                  <div class="reviewImgBox">
                    <img src="../../img/item/item2/메인7.jpg" alt="">
                  </div>
                </div>
              </c:forEach>
            </div>
          </div>
          <div id="itemSecRightBox">
            <div id="totalChargeBox">
              <p id="ro_price">110,000원</p>
              <input type="checkbox" id="underIcon" checked />
              <div id="dateAndMen">
                <ul>
                  <li>체크인</li>
                  <li>체크아웃</li>
                </ul>
                <!-- <div id="calendar"></div> -->
                <p>인원</p>
                <p class="fs-14" style="margin-bottom: 0">
                  성인 <span id="p_adult">2</span>명/ 아동
                  <span id="p_child">0</span>명

                  <label for="underIcon"
                    ><i class="fa-solid fa-angle-down fs-24"></i
                  ></label>
                </p>
                <span class="fs-14" style="margin-top: 0">
                  반려동물 <span id="p_pet">0</span>마리
                </span>
                <p class="chargePeople">
                  성인
                  <span style="position: relative">
                    <i class="fa-solid fa-minus btnStCircle"></i>
                    <input
                      data-ch="adult"
                      type="number"
                      min="0"
                      max="8"
                      value="2"
                    />
                    <i class="fa-solid fa-plus btnStCircle"></i>
                  </span>
                </p>
                <p class="chargePeople">
                  아동
                  <span>
                    <i class="fa-solid fa-minus btnStCircle"></i>
                    <input
                      data-ch="child"
                      type="number"
                      min="0"
                      max="8"
                      value="0"
                      readonly
                    />
                    <i class="fa-solid fa-plus btnStCircle"></i>
                  </span>
                </p>

                <p class="chargePeople">
                  반려동물
                  <span>
                    <i class="fa-solid fa-minus btnStCircle"></i>
                    <input
                      data-ch="pet"
                      type="number"
                      min="0"
                      max="4"
                      value="0"
                      readonly
                    />
                    <i class="fa-solid fa-plus btnStCircle"></i>
                  </span>
                </p>
              </div>
              <p>110,000원 * 4박</p>
              <p>추가요금</p>
              <p>바베큐 20,000원</p>
              <p style="text-align: right"><span>총 합계 460,000원</span></p>
            </div>
          </div>
        </div>
      </form>

      <!--작성 후 ajax 로 날리고 포맷 -->
      <div id="roomMoreBox">
        <form
          id="roomUploadForm"
          action=""
          method="post"
          enctype="multipart/form-data"
        >
          <p>객실 상세 정보</p>
          <div class="roomMoreImgBox">
            <div id="roomMoreImgCont"></div>

            <button type="button">
              <i class="fa-solid fa-angle-left"></i>
            </button>
            <button type="button">
              <i class="fa-solid fa-angle-right"></i>
            </button>

            <input
              id="roomMoreImgInput"
              name="room_imgs"
              type="file"
              multiple
              accept="image/*"
            />
            <label for="roomMoreImgInput"
              >객실 사진을 선택해주세요 <br />
              최대 8장까지 가능합니다<br />
              첫 번째 이미지가 상위에 노출됩니다
            </label>
          </div>
          <div id="fileListBox"></div>
          <div id="roomMoreInfoBox">
            <div id="roomMoreLeftBox">
              <ul>
                <li>
                  <p>객실명</p>
                  <input
                    name="room_name"
                    id="room_name"
                    type="text"
                    maxlength="16"
                    placeholder="최대 16자리"
                  />
                </li>
                <li>
                  <span>객실 크기</span
                  ><span
                    ><input
                      name="room_size"
                      id="room_size"
                      type="number"
                      placeholder=""
                    />㎡</span
                  >
                </li>
                <li>
                  <span>기준 인원</span>
                  <span>
                    <i class="fa-solid fa-minus btnStCircle"></i>
                    <input
                      name="basic_men"
                      type="number"
                      min="0"
                      max="8"
                      value="2" />
                    <i class="fa-solid fa-plus btnStCircle"></i
                  ></span>
                </li>
                <li>
                  <span>최대 인원</span>
                  <span
                    ><i class="fa-solid fa-minus btnStCircle"></i>
                    <input
                      name="max_men"
                      id="max_men"
                      type="number"
                      min="0"
                      max="8"
                      value="2" />
                    <i class="fa-solid fa-plus btnStCircle"></i
                  ></span>
                </li>
                <li>
                  <span>침대</span>
                  <span
                    ><select name="room_bed" id="bed">
                      <option value="싱글">싱글</option>
                      <option value="더블">더블</option>
                      <option value="퀸">퀸</option>
                      <option value="킹">킹</option>
                    </select>
                    <select name="room_bed_cnt" id="bedcnt">
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                    </select>
                  </span>
                </li>
                <li>
                  <span>전망</span>
                  <span>
                    <select name="room_view" id="view">
                      <option value="시티">시티</option>
                      <option value="산">산</option>
                      <option value="바다">바다</option>
                      <option value="프라이빗">프라이빗</option>
                    </select></span
                  >
                </li>
                <li>
                  <span>테마</span>
                  <span>
                    <select name="room_theme" id="room_theme">
                      <option value="가족">가족</option>
                      <option value="친구">친구</option>
                      <option value="커플">커플</option>
                      <option value="단체">단체</option>
                      <option value="반려동물">반려동물</option>
                    </select></span
                  >
                </li>
              </ul>
            </div>
            <div id="roomMoreRightBox">
              <p>
                숙박(1박) <input name="room_price" id="price" type="number" />원
              </p>
              <p>
                대실
                <select name="room_part_price" id="partTimePrice">
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                  <option value="6">6</option></select
                >시간 <input name="room_part_time" type="number" required />원
              </p>

              <p>부대 시설</p>
              <input
                id="room_extraopt"
                name="room_extraopt"
                type="text"
                hidden
              />
              <input
                name="room_extraopt"
                id="에어컨"
                type="checkbox"
                value="에어컨"
              />
              <label for="에어컨">에어컨</label>
              <input name="room_extraopt" id="TV" type="checkbox" value="TV" />
              <label for="TV">TV</label>
              <input
                name="room_extraopt"
                id="냉장고"
                type="checkbox"
                value="냉장고 "
              />
              <label for="냉장고">냉장고</label>
              <input
                name="room_extraopt"
                id="커피포트"
                type="checkbox"
                value="커피포트"
              />
              <label for="커피포트">커피포트</label>
              <input
                name="room_extraopt"
                id="욕실용품"
                type="checkbox"
                value="욕실용품"
              />
              <label for="욕실용품">욕실용품</label>
              <br />
              <input
                name="room_extraopt"
                id="드라이기"
                type="checkbox"
                value="드라이기"
              />
              <label for="드라이기">드라이기</label>
              <input
                name="room_extraopt"
                id="비데"
                type="checkbox"
                value="비데"
              />
              <label for="비데">비데</label>
              <input
                name="room_extraopt"
                id="개별테라스"
                type="checkbox"
                value="개별테라스"
              />
              <label for="개별테라스">개별테라스</label>
            </div>
          </div>
        </form>
        <div style="display: flex; justify-content: end">
          <button id="roomMoreCancelBtn" type="button">취소</button>
          <button id="roomSaveBtn" type="button">저장</button>
        </div>
      </div>
    </div>

    <script src="../../js/default.js"></script>
    <script src="../../js/item.js"></script>
  </body>
</html>
