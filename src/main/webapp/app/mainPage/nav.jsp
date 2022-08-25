<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>

 <nav class="nav">
      <!-- 중간배치박스 -->
      <div class="navCenterCont" >
        <!-- 3등분 -->
        <ul>
          <li><a href="main.m"><h1 class="logo" style=" color:white">Hostel</h1></a></li>
          <li class="navTit"> ${title}</li>
          <li>
          <c:choose>
          <c:when test="${u_no!=null}">
            <i class="fa-solid fa-user"></i>
            <div class="navCartBox">
              <p>${u_nick} <span style="font-size: 12px">님</span> </p>
               <p><a href="myInfo.my" style="margin: 0">내정보</a></p>  
                <p><a href="myReservation.my" style="margin: 0">결제/예약내역</a></p>
                <p><a href='logout.m' style="margin: 0">로그아웃</a></p>
            </div></c:when>
            <c:when test="${title=='로그인' || title=='회원가입' || title=='내정보'}">
            </c:when>
            <c:otherwise>
              <a href='login.m' style="color:white">로그인</a>
            </c:otherwise>
          
          </c:choose>
        
         
          </li>
        </ul>
      </div>
    </nav>