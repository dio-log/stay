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
    <link rel="stylesheet" href="../../css/main.css" />
    <link rel="stylesheet" href="../../css/default.css" />
    <link rel="stylesheet" href="../../css/itemList.css" />
  </head>

  <body>
    <nav class="nav">
      <!-- 중간배치박스 -->
      <div class="navCenterCont">
        <!-- 3등분 -->
        <ul>
          <li><img src="" alt="z" /></li>
          <li>
            <h2 class="navTit"></h2>
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
    <div class="centerCont">
      <div class="emptyBoxByNav"></div>
      <div class="searchBoxWrap">
        <div class="searchBoxDestination">
          <h4>여행지, 숙소</h4>
          <input
            type="text"
            placeholder="검색해보세요"
            style="
              width: 200px;
              height: 40px;
              font-size: 20px;
              font-weight: 600;
              border: none;
            "
          />

          <div id="recentSearchDesination">
            <span>최근검색</span><br />
            <a href=""
              ><span style="font-size: 12px"
                >강원도 <br />07.09-07.10 박 / 성인2-객실1</span
              ></a
            >
            <br />
            <span>인기 여행지</span>
            <br />
            <a href="">부산</a>
            <a href="">강원도</a>
          </div>
        </div>
        <div class="searchBoxDate" onclick="searchBoxBlock(this)">
          <h4>날짜</h4>
          <p>07.09(토) - 07.10(일) 1박</p>

          <div id="searchCalendar">
            <div id="thisMonth">sdfasf</div>
            <div id="nextMonth">sdfsadfdsf</div>
          </div>
        </div>
        <div class="searchBoxRoom" onclick="searchBoxBlock(this)">
          <h4>인원, 객실</h4>
          <p>성인 2, 소아 0, 객실 1</p>

          <div id="searchRoomOption">dddd</div>
        </div>
        <button id="searchBoxBtn" style="position: relative">
          <p
            class="fa-solid fa-magnifying-glass"
            style="
              font-size: 35px;
              position: absolute;
              left: 50%;
              top: 50%;
              transform: translateX(-50%) translateY(-50%);
            "
          ></p>
        </button>
      </div>
      <div class="midCont">
        <div>
          <div class="filterWrap">
            <div style="text-align: center; margin-bottom: 10px">
              <button type="button" id="searchMapBtn">
                <i
                  class="fa-solid fa-map-location-dot"
                  style="margin-right: 5px"
                ></i
                >지도 보기
              </button>
            </div>
            <ul>
              <li><h3>가격대</h3></li>

              <li><progress id="progress" min="0" max="100"></progress></li>
              <li>
                <input type="text" />
                <input type="text" />
              </li>
            </ul>
            <ul>
              <li><h3>숙소 유형</h3></li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">모텔</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">호텔</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">리조트</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">펜션</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">게스트하우스</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">캠핑</label>
              </li>
            </ul>
            <ul>
              <li><h3>테마</h3></li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">가족</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">단체</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">친구</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">커플</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">반려동물</label>
              </li>
            </ul>
            <ul>
              <li>
                <h3>부대시설</h3>
              </li>

              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">마트/편의점</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">바베큐장</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">주차장</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">테라스</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">조식</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">스파</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">세탁</label>
              </li>
              <li>
                <input id="" name="d1" type="checkbox" /><label
                  class="cb-square-16 filterCbLabel"
                  for="d1"
                  >✓</label
                ><label class="optLabel" for="d1">야외수영장</label>
              </li>
            </ul>
          </div>
        </div>
        <div class="itemListWrap">
          <div
            style="
              display: flex;
              justify-content: space-between;
              border-bottom: 1px solid #e6e6e6;
            "
          >
            <h2>서울</h2>
            <ul id="sortUl">
              <li>인기순</li>
              <li>높은 평점순</li>
              <li>낮은 요금순</li>
              <li>리뷰 많은순</li>
            </ul>
          </div>
          <ul style="margin-top: 5px">
            <li>
              <a href="#">
                <img src="../../img/main/테스트_호텔이미지.jpg" alt="" />
                <input
                  type="checkbox"
                  class="cbSt-heart"
                  style="display: none"
                  id="item_no1"
                /><label for="item_no1"
                  ><i class="fa-solid fa-heart heartStyle"></i
                ></label>
                <div class="itemTitle">
                  <h1 style="margin-bottom: 5px">힐슨 호텔</h1>
                  <div>
                    <p><span>호텔</span> <span>등급미정</span></p>
                    <p><span>9.2</span><span>(210)</span></p>
                  </div>
                  <div style="text-align: right">
                    <p>남은객실 1개</p>
                    <p>440,000원</p>
                  </div>
                </div>
              </a>
            </li>
            <li>
              <a href="#">
                <img src="../../img/main/테스트_호텔이미지.jpg" alt="" />
                <input
                  type="checkbox"
                  class="cbSt-heart"
                  style="display: none"
                  id="item_no2"
                /><label for="item_no2"
                  ><i class="fa-solid fa-heart heartStyle"></i
                ></label>
                <div class="itemTitle">
                  <h1 style="margin-bottom: 5px">힐슨 호텔</h1>
                  <div>
                    <p><span>호텔</span> <span>등급미정</span></p>
                    <p><span>9.2</span><span>(210)</span></p>
                  </div>
                  <div style="text-align: right">
                    <p>남은객실 1개</p>
                    <p>440,000원</p>
                  </div>
                </div>
              </a>
            </li>
            <li>
              <a href="#">
                <img src="../../img/main/테스트_호텔이미지.jpg" alt="" />
                <input
                  type="checkbox"
                  class="cbSt-heart"
                  style="display: none"
                  id="item_no3"
                /><label for="item_no3"
                  ><i class="fa-solid fa-heart heartStyle"></i
                ></label>
                <div class="itemTitle">
                  <h1 style="margin-bottom: 5px">힐슨 호텔</h1>
                  <div>
                    <p><span>호텔</span> <span>등급미정</span></p>
                    <p><span>9.2</span><span>(210)</span></p>
                  </div>
                  <div style="text-align: right">
                    <p>남은객실 1개</p>
                    <p>440,000원</p>
                  </div>
                </div>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <script src="../../js/hangOver.js"></script>
    <script src="../../js/default.js"></script>
    <script src="../../js/itemList.js"></script>
  </body>
</html>
