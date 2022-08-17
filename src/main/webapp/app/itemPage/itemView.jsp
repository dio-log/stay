<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <p class="navTit"></p>
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
      <div class="emptyBoxByNav"></div>
      <div id="itemFirstCont">
        <h2 id="itemTitle">[제주/호텔] 그랜드 하얏트 제주</h2>
        <div style="display: flex;">
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
          <div id="itemMainInfoBox">
            <p>호텔</p>
            <p id="grade">평점 및 상품평개수</p>
            <input id="itemMainMap" type="text" value="주소, 지도로보기" />
            <p>할인 혜택</p>
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
            <li>
              <input name="itemTab" id="itemTab_review" type="radio" " /><label
                for="itemTab_review"
                >리뷰</label
              >
            </li>
          </ul>
          <!-- 디테일 -->

          <div id="itemTab_roomDetailBox">
            <!-- 정보추출해서 반복해서 넣어줄 박스 -->
            <div class="itemTab_roomDetailSt">
              <div style="position: relative">
                <img src="http://via.placeholder.com/300x200" alt="" />
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
                <button  type="button" class="whiteBtnSt">견적보기 ></button>
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
            <textarea name="item_cancelrefund" id="" cols="" rows=""></textarea>
            <p>확인사항 및 기타</p>
            <textarea name="item_checklist" id="" cols="" rows=""></textarea>
          </div>

          <!-- 리뷰 -->

          <div id="itemTab_reviewDetailBox" class="displayNone">
            <div id="">
              <p>평점</p>
              <span>전체 리뷰</span>
              <span>제휴점 답변</span>
            </div>
            <!--리뷰정보 불러서 반복붙임, 페이징-->
          </div>
        </div>
        <div id="itemSecRightBox">
          <div id="totalChargeBox">
            <p id="ro_price">110,000원</p>
            <input type="checkbox" id="underIcon" />
            <div id="dateAndMen">
              <ul>
                <li>체크인</li>
                <li>체크아웃</li>
              </ul>
              <div id="calendar"></div>
              <p>인원</p>
              <p class="fs-14" style="margin-bottom: 0;">
                성인 <span id="p_adult">2</span>명/
                어린이 <span id="p_child">0</span>명
             
             
                <label for="underIcon"
                  ><i class="fa-solid fa-angle-down fs-24"></i
                ></label>
              </p>
              <p class="fs-14" style="margin-top: 0;">
                유아 <span id="p_baby">0</span>명/
                반려동물 <span id="p_pet">0</span>마리
              </p>
              <p class="chargePeople">
                성인
                <span style="position: relative">
                  <i class="fa-solid fa-minus btnStCircle"></i>
                  <input data-ch="adult" type="number" min="0" max="8" value="2" />
                  <i class="fa-solid fa-plus btnStCircle"></i>
                </span>
              </p>
              <p class="chargePeople">
                어린이
                <span>
                  <i class="fa-solid fa-minus btnStCircle"></i>
                  <input   data-ch="child" type="number" min="0" max="8" value="0" readonly />
                  <i class="fa-solid fa-plus btnStCircle"></i>
                </span>
              </p>
              <p class="chargePeople">
                유아
                <span>
                  <i class="fa-solid fa-minus btnStCircle"></i>
                  <input  data-ch="baby" type="number" min="0" max="8" value="0" readonly />
                  <i class="fa-solid fa-plus btnStCircle"></i>
                </span>
              </p>
              <p class="chargePeople">
                반려동물
                <span>
                  <i class="fa-solid fa-minus btnStCircle"></i>
                  <input   data-ch="pet" type="number" min="0" max="4" value="0" readonly />
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
      <div id="roomMoreBox">
        <form action="" method="post" enctype="multipart/form-data">
          <p>객실 상세 정보</p>
          <div class="roomMoreImgBox">
            <div id="roomMoreSlide">
<img src="img/초고화질_컴퓨터_배경화면_(7).jpg" alt="">
<img src="img/초고화질_컴퓨터_배경화면_(7).jpg" alt="">
<img src="img/초고화질_컴퓨터_배경화면_(7).jpg" alt="">


            </div>
            <div id="roomMoreBtnBox">
              <button id="leftBtn" type="button" onclick="leftSideBtn()" ><i class="fa-solid fa-angle-left"></i></button>
              <button id="rightBtn" type="button" onclick="rightSideBtn()" ><i class="fa-solid fa-angle-right"></i></button>
            </div>
       
          </div>
          <div id="roomMoreInfoBox">
            <div id="roomMoreLeftBox">
              <ul>
                <li>
                  <p>객실명</p>
                  <input
                    id="room_name"
                    type="text"
                    maxlength="16"
                    placeholder="최대 16자리"
                  />
                </li>
                <li>
                  <span>객실 크기</span
                  ><span
                    ><input id="size" type="number" placeholder="" />㎡</span
                  >
                </li>
                <li>
                  <span>기준 인원</span>
                  <span>
                    <i id="min_men" class="fa-solid fa-minus btnStCircle"></i>
                    <input type="number" min="0" max="8" value="2" />
                    <i class="fa-solid fa-plus btnStCircle"></i
                  ></span>
                </li>
                <li>
                  <span>최대 인원</span>
                  <span
                    ><i class="fa-solid fa-minus btnStCircle"></i>
                    <input
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
                    ><select id="bed">
                      <option value="b001">싱글</option>
                      <option value="b002">더블</option>
                      <option value="b003">퀸</option>
                      <option value="b004">킹</option>
                    </select>
                    <select id="bedCnt">
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                      
                    </select>
                   
                      
                  </input>
                  </span>
                </li>
                <li>
                  <span>전망</span>
                  <span>
                    <select id="view">
                      <option value="v001">산</option>
                      <option value="v002">바다</option>
                      <option value="v003">프라이빗</option>
                    </select></span
                  >
                </li>
              </ul>
            </div>
            <div id="roomMoreRightBox">
              <p>숙박(1박) <input id="price" type="number">원</p>
              <p>대실  <select id="partTime">
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
              </select>시간 <input type="number">원</p>

              <p>객실 정보</p>
              <input
                name="roomExtraInfo"
                id="에어컨"
                type="checkbox"
                value="에어컨"
              />
              <label for="에어컨">에어컨</label>
              <input name="roomExtraInfo" id="TV" type="checkbox" value="TV" />
              <label for="TV">TV</label>
              <input
                name="roomExtraInfo"
                id="냉장고"
                type="checkbox"
                value="냉장고 "
              />
              <label for="냉장고">냉장고</label>
              <input
                name="roomExtraInfo"
                id="커피포트"
                type="checkbox"
                value="커피포트"
              />
              <label for="커피포트">커피포트</label>
              <input
                name="roomExtraInfo"
                id="욕실용품"
                type="checkbox"
                value="욕실용품"
              />
              <label for="욕실용품">욕실용품</label>
              <br />
              <input
                name="roomExtraInfo"
                id="드라이기"
                type="checkbox"
                value="드라이기"
              />
              <label for="드라이기">드라이기</label>
              <input
                name="roomExtraInfo"
                id="비데"
                type="checkbox"
                value="비데"
              />
              <label for="비데">비데</label>
              <input
                name="roomExtraInfo"
                id="개별테라스"
                type="checkbox"
                value="개별테라스"
              />
              <label for="개별테라스">개별테라스</label>
            </div>
          </div>
      
        </form>
        <button type="button" id="viewMoreCloseBtn" class="whiteBtnSt" onclick="closeBtn(this)">닫기</button>
      </div>
    </div>

    <script src="../../js/default.js"></script>
    <script src="../../js/item.js"></script>
    <script src="../../js/itemView.js"></script>
  </body>
</html>
