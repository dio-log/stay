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
    <link rel="stylesheet" href="../../css/default.css" />
    <link rel="stylesheet" href="../../css/myPage.css" />
  </head>
  <body>
    <c:set var="title" value="내 정보"></c:set>
    <%@ include file="/app/mainPage/nav.jsp" %>
    <div class="centerCont">
      <div class="emptyBoxByNav"></div>
      <!-- 내 정보 관리 , 예약내역,나의 후기,포인트, 찜,쿠폰함,  공지 사항-->
      <div style="display: flex; white-space: nowrap">
        <ul id="myTab">
          <li>
            <a
              href="myInfo.my"
              >내 정보 관리</a
            >
          </li>
           <c:choose>
 	<c:when test="${u_div==1}">
          <li>
            <a href="myReservation.my">예약내역</a>
          </li>

          <li><a href="myReview.my">나의 후기</a></li>
           	<li> <a href="myPoint.my">포인트</a></li>
          <li><a href="myCoupon.my">쿠폰</a></li>
          <li><a href="myPick.my">찜</a></li>
 	</c:when>
 	<c:when test="${u_div==2}">
 	 <li><a href="itemUpload.it">객실등록</a></li>
 	 <li><a href="myItemManage.my"  style="color: rgb(243, 21, 21); font-weight: 600">객실관리</a></li>
 	 <li><a href="myAnswerManage.my">답변관리</a></li>
 	</c:when>
 </c:choose>
        </ul>
        <div id="myPageContentWrap">
         
      
        </div>
      </div>
    </div>
    <script src="../../js/default.js"></script>
    <script src="../../js/myPage.js"></script>
  </body>
</html>
