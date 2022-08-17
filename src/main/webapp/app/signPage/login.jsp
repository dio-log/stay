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
    <link rel="stylesheet" href="../../css/memberSign.css" />
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
    <div
      class="centerCont"
      style="width: 768px; text-align: center;"
    >
      <div class="emptyBoxByNav" style="margin-bottom: 60px;"></div>
      <div style="display:inline-block ; width: 400px;text-align: center;">
      <form id="loginForm" action="">
        <input name="u_id" type="text" placeholder="아이디">
        <br>
        <input name="u_pw" type="password" placeholder="비밀번호">
        <br>
        <div style="text-align: left; padding-left: 2px;">
        <input id="rememberID" type="checkbox"> <label for="rememberID" class="cb-square-16" style="text-align: center;">✓</label>
         <label for="rememberID" style="font-family: fantasy;">아이디 기억하기</label>
         </div>
        <br>
        <button class="redBtnSt" style="width: 400px ;padding: 10px 0;">로그인</button>
    </form>
        <ul id="searchUl">
            <li>아이디찾기</li>
            <li>비밀번호찾기</li>
            <li>회원가입</li>
        </ul>
       <ul id="loginBtnUl">
         <li> <button type="button" class="whiteBtnSt" style="background-color: rgb(255, 220, 64);"><img src="../../img/main/kakaologo2.png" alt=""> <span>카카오톡으로 로그인</span></button></li>
         <li> <button type="button" class="whiteBtnSt" style="border: 1px solid #e6e6e6;"><img src="../../img/main/googlelogo.jpg" alt=""><span>구글로 로그인</span></button></li>
<li> <button type="button" class="whiteBtnSt" style="background-color: rgb(45, 196, 45);color: white;"><img src="../../img/main/naverlogo.png" alt=""><span>네이버로 로그인</span></button></li>

       </ul>
      
      </div>
    </div>
    </div>
    <script src="../../js/memberSign.js"></script>
  </body>
</html>
