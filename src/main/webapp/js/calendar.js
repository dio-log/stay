let checkin = 0;
let checkout = 0;
let checkSet = new Set();

let dt1 = new Date();
let dt2 = new Date();
onload = function () {
  //   let dt = new Date();

  dt2.setMonth(dt1.getMonth() + 1);
  dt1.setDate(dt1.getDate() - dt1.getDate() + 1);
  dt2.setDate(dt2.getDate() - dt2.getDate() + 1);

  //일요일은0 ,~6까지
  $("#firstTitle").html(dt1.getFullYear() + "." + (dt1.getMonth() + 1));
  $("#secondTitle").html(dt2.getFullYear() + "." + (dt2.getMonth() + +1));
  setCalendar("firstCalendarTable", dt1);
  setCalendar("secondCalendarTable", dt2);
  initClick();
};
function initClick() {
  $(".calendarSt tr:not(.calendarSt tr:nth-child(1)) td").on(
    "click",
    function (e) {
      $(e.target).css("background-color", "rgb(255, 98, 98)");
      $(e.target).css("color", "white");
      checkSet.add($(e.target).html());
      if (checkSet.size == 2) {
        let min = Math.min(checkSet);
        let max = Math.max(checkSet);

        $("#setCalendarInput").val();
      } else if (checkSet.size > 2) {
        $(".calendarSt tr:not(.calendarSt tr:nth-child(1)) td").css(
          "background-color",
          "white"
        );
        $(".calendarSt tr:not(.calendarSt tr:nth-child(1)) td").css(
          "color",
          "black"
        );
        $(".calendarSt tr td:nth-child(1)").css("color", "rgb(255, 98, 98)");

        checkSet = new Set();
      }
    }
  );
}

function setDate(calendarDivId, lastNum, dt) {
  let tr = $("<tr>");
  for (let i = 0; i < dt.getDay(); i++) {
    tr.append("<td> </td>");
  }
  for (let i = 1; i <= lastNum; i++) {
    tr.append(`<td>${i}</td>`);

    dt.setDate(dt.getDate() + i - 1);
    if (dt.getDay() == 6) {
      $(`#${calendarDivId}`).append(tr);
      tr = $("<tr>");
    }
    if (i == lastNum) {
      $(`#${calendarDivId}`).append(tr);
    }
    dt.setDate(dt.getDate() - i + 1);
  }
}

function setCalendar(calendarDivId, dt) {
  //홀수 월 30, 2월은 윤년에 맞춰28 또는 29
  $(`#${calendarDivId}`).append(
    `<thead colspan="7" class="calendarTitle"></thead>
        <tr>
        <td>일</td>
        <td>월</td>
        <td>화</td>
        <td>수</td>
        <td>목</td>
        <td>금</td>
        <td>토</td>
      </tr>      `
  );
  //2월
  if (dt.getMonth() == 1) {
    if (dt.getFullYear() % 4 == 0) {
      //윤년 29일
      setDate(calendarDivId, 29, dt);
    } else {
      //평년 28일
      setDate(calendarDivId, 28, dt);
    }
  } else if (dt.getMonth() < 7) {
    if (dt.getMonth() % 2 == 0) {
      setDate(calendarDivId, 31, dt);
    } else {
      setDate(calendarDivId, 30, dt);
    }
  } else {
    if (dt.getMonth() % 2 == 0) {
      setDate(calendarDivId, 30, dt);
    } else {
      setDate(calendarDivId, 31, dt);
    }
  }
}

function nextMonth() {
  $("#firstCalendarTable").empty();
  $("#secondCalendarTable").empty();
  dt1.setMonth(dt1.getMonth() + 1);
  dt2.setMonth(dt1.getMonth() + 1);
  dt1.setDate(dt1.getDate() - dt1.getDate() + 1);
  dt2.setDate(dt2.getDate() - dt2.getDate() + 1);

  $("#firstTitle").html(dt1.getFullYear() + "." + (dt1.getMonth() + 1));
  $("#secondTitle").html(dt2.getFullYear() + "." + (dt2.getMonth() + 1));
  setCalendar("firstCalendarTable", dt1);
  setCalendar("secondCalendarTable", dt2);
  initClick();
}
function prevMonth() {
  $("#firstCalendarTable").empty();
  $("#secondCalendarTable").empty();
  dt1.setMonth(dt1.getMonth() - 1);
  dt2.setMonth(dt1.getMonth() - 1);
  dt1.setDate(dt1.getDate() - dt1.getDate() - 1);
  dt2.setDate(dt2.getDate() - dt2.getDate() - 1);

  $("#firstTitle").html(dt1.getFullYear() + "." + (dt1.getMonth() - 1));
  $("#secondTitle").html(dt2.getFullYear() + "." + (dt2.getMonth() - 1));
  setCalendar("firstCalendarTable", dt1);
  setCalendar("secondCalendarTable", dt2);
  initClick();
}
