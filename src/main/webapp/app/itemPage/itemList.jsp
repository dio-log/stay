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
      <link rel="stylesheet" href="../../css/calendar.css" />
    <link rel="stylesheet" href="../../css/itemList.css" />
  </head>

  <body>
      <c:set var="title" value=""></c:set>
    <%@ include file="/app/mainPage/nav.jsp" %>
    <div class="centerCont">
      <div class="emptyBoxByNav"></div>
   <div id="searchCont">
        <div id="setLocationBox">
          <label for="setLocationInput">
            <i
              class="fa-solid fa-map-location-dot"
              style="margin-right: 5px"
            ></i>
          </label>

          <input
            name="setLocationInput"
            id="setLocationInput"
            type="text"
            placeholder="여행지, 숙소"
          />
          <div id="searchDetination">
            <!-- 포커스했을땐 최근검색, 인기키워드 -->
            <!-- 검색할땐 검색키워드 자동검색 -->
            <div style="padding: 0 5px;overflow-y: auto; width: 100%;max-height: 300px; ">
            <div id="searchRecent">
              <div><strong>최근 검색</strong></div>
              <div>
                <div class="searchRecnetContent">
                  <p> <a href="">최근검색어 쿠키에서불러오기</a> <i class="fa-solid fa-xmark"></i></p>
                  <p>08.19(금) ~ 08.20(토) / 성인2 </p>
                </div>
                <div class="searchRecnetContent">
                  <p>최근검색어 쿠키에서불러오기 <i class="fa-solid fa-xmark"></i></p>
                  <p>08.19(금) ~ 08.20(토) / 성인2 </p>
                </div> <div class="searchRecnetContent">
                  <p>최근검색어 쿠키에서불러오기 <i class="fa-solid fa-xmark"></i></p>
                  <p>08.19(금) ~ 08.20(토) / 성인2 </p>
                </div> <div class="searchRecnetContent">
                  <p>최근검색어 쿠키에서불러오기 <i class="fa-solid fa-xmark"></i></p>
                  <p>08.19(금) ~ 08.20(토) / 성인2 </p>
                </div> <div class="searchRecnetContent">
                  <p>최근검색어 쿠키에서불러오기 <i class="fa-solid fa-xmark"></i></p>
                  <p>08.19(금) ~ 08.20(토) / 성인2 </p>
                </div>
               
              </div>
            </div>
            
            <div id="searchBestKeyword">
              <div><strong>인기키워드</strong></div>
              <div>
                
              </div>
            </div>
          </div>
          </div>
        </div>
        <div id="setCalendarBox">
          <label for="setCalendarInput"
            ><i class="fa-solid fa-calendar-week"></i
          ></label>
          <input
            id="setCalendarInput"
            name="setCalendarInput"
            type="text"
            placeholder="날짜"
          />
            <div id="calendar">
      <div id="firstCalendar" class="calendarSt">
        <div class="titleBox">
          <p class="title">
            <button type="button" onclick="prevMonth()">
              <i class="fa-solid fa-angle-left"></i>
            </button>
            <span id="firstTitle" style="margin-right: 50px"></span>
          </p>
        </div>
        <table id="firstCalendarTable"></table>
      </div>
      <div id="secondCalendar" class="calendarSt">
        <div class="titleBox">
          <p class="title">
            <span id="secondTitle" style="margin-left: 50px"></span
            ><button type="button" onclick="nextMonth()">
              <i class="fa-solid fa-angle-right"></i>
            </button>
          </p>
        </div>

        <table id="secondCalendarTable"></table>
      </div>
    </div>
        </div>

        <div id="setPeopleBox">
          <label for="setPeopleInput"><i class="fa-solid fa-users"></i></label>
          <input id="setPeopleInput" type="text" placeholder="인원" />
          <div id="setPeople">
            <div class="setPeopleContent">
              <p><span>성인</span><span><i class="fa-solid fa-minus minusBtn"></i><input class="setPeopleAdult" type="number" min="0" max="8" value="2" disabled><i class="fa-solid fa-plus plusBtn"></i></span></p>
              <p><span>아동 <span style="font-size: 12px; color: gray;">만 18세미만</span></span><span><i class="fa-solid fa-minus minusBtn"></i><input class="setPeopleKids" type="number" min="0" max="8" value="0" disabled><i class="fa-solid fa-plus plusBtn"></i></span></p>
              <p><span>반려동물</span><span><i class="fa-solid fa-minus minusBtn"></i><input  class="setPeoplePet"  type="number" min="0" max="8" value="0" disabled><i class="fa-solid fa-plus plusBtn"></i></span></p>
             
            </div>
            <p style="text-align: center;"><button id="setPeopleBtn" c type="button" class="whiteBtnSt">선택 완료</button></p>
          </div>
        </div>
      <div style="display: flex; align-items: center; padding-left: 0;">
      <button id="searchBtn" class="redBtnSt" type="button">검색</button>
      </div>
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
            <h2 id="beSearchedWord">${searchWord}</h2>
            <ul id="sortUl">
              <li>인기순</li>
              <li>높은 평점순</li>
              <li>낮은 요금순</li>
              <li>리뷰 많은순</li>
            </ul>
          </div>
          <ul id="itemListWrap" style="margin-top: 5px">
            <li>
              <a href="#">
                <img src="../../img/item/item1/main1.jpg" alt="" />
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
    
            
          </ul>
        </div>
      </div>
    </div>

    <script src="../../js/default.js"></script>
    <script src="../../js/calendar.js"></script>
    <script src="../../js/itemList.js"></script>
  </body>
</html>
