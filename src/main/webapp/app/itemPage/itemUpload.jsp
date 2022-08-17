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
    <nav class="nav">
      <!-- 중간배치박스 -->
      <div class="navCenterCont">
        <!-- 3등분 -->
        <ul>
          <li><img src="" alt="z" /></li>
          <li>
            <p class="navTit">객실 등록</p>
          </li>
          <li>
            <i class="fa-solid fa-user"></i>
            <div class="navCartBox">
              <img src="../../img/main/speachBubble.png" alt="" />
              <div>
                <p>My</p>
                <p>Cart</p>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </nav>
    <!--  -->

    <div class="centerCont">
      <form
        id="itemUploadForm"
        action=""
        method="post"
        enctype="multipart/form-data"
      >
        <div class="emptyBoxByNav"></div>
        <div id="itemFirstCont">
          <div style="display: flex">
            <div id="itemMainImgBox">
              <div
                style="
                  border: 1px solid black;
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
                <label id="itemMainImgLabel" for="itemMainImgInput">
                  사진을 선택해주세요
                </label>

                <input
                  id="itemMainImgInput"
                  name="itemMainImgInput"
                  type="file"
                  multiple
                  accept="image/*"
                />
              </div>
              <div id="itemSubImgBox">
                <!-- 이미지 반복문으로 등록 -->
                <div></div>
              </div>
            </div>
            <div id="itemMainInfoBox">
              <p style="display: flex; justify-content: space-between">
                <span>숙소명</span>

                <input id="item_name" name="item_name" type="text" />
              </p>
              <p style="display: flex; justify-content: space-between">
                <span>분류</span>

                <select name="itemDiv" id="itemDiv">
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
                <input
                  type="text"
                  id="sample6_address"
                  placeholder="주소"
                  style="width: 100%"
                />
                <br />
                <input
                  type="text"
                  id="sample6_detailAddress"
                  placeholder="상세주소"
                />

                <input
                  type="text"
                  id="sample6_extraAddress"
                  placeholder="참고항목"
                />
                <input
                  class="whiteBtnSt"
                  type="button"
                  onclick="sample6_execDaumPostcode()"
                  value="주소 찾기"
                  style="font-size: 16px; padding: 0 5px"
                />
              </div>
              <p>이벤트</p>
              <textarea
                id="itemMainEvent"
                name="itemMainEvent"
                cols=""
                rows=""
              ></textarea>
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
            </ul>
            <!-- 디테일 -->

            <div id="itemTab_roomDetailBox">
              <button id="addRoomBtn" type="button">객실 추가</button>
              <!-- 정보추출해서 반복해서 넣어줄 박스 -->
        <div class="itemTab_roomDetailSt">
                <div style="position: relative">
                  <img src="../../img/room/admin/ho7.jpg" alt="" />
                  <p class="itemTab_roomDetailMore">더 보기</p>
                </div>
                <div class="itemTab_roomDetailRight">
                  <p>객실이름</p>
                  <p>객실상세</p>
                  <p>가격 <span>110,000</span></p>
                  <p>
                    쿠폰적용가
                    <span>99,000</span>
                  </p>
                  <button type="button" class="whiteBtnSt">수정</button>
                </div> 
              </div> 
            </div>

            <!-- 인포 -->

            <div id="itemTab_infoDetailBox" class="displayNone">
              <p>주변정보</p>
              <textarea name="item_arround" id="" cols="" rows=""></textarea>
              <p>공지사항</p>
              <textarea name="item_notice" id="" cols="" rows=""></textarea>
              <p>기본정보</p>
              <textarea name="item_basic" id="" cols="" rows=""></textarea>
              <p>주차정보</p>
              <textarea name="item_parking" id="" cols="" rows=""></textarea>
              <p>인원추가시</p>
              <textarea name="item_addpeople" id="" cols="" rows=""></textarea>
              <p>취소 및 환불 규정</p>
              <textarea
                name="item_cancel_refund"
                id=""
                cols=""
                rows=""
              ></textarea>
              <p>확인사항 및 기타</p>
              <textarea name="item_extra" id="" cols="" rows=""></textarea>
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
                      name="min_men"
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
                      <option value="b001">싱글</option>
                      <option value="b002">더블</option>
                      <option value="b003">퀸</option>
                      <option value="b004">킹</option>
                    </select>
                    <select name="bedcnt" id="bedcnt">
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
                      <option value="v001">산</option>
                      <option value="v002">바다</option>
                      <option value="v003">프라이빗</option>
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
