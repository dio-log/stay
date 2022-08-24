let checkin = 0;
let checkout = 0;
let checkSet = new Set();

let dt1 = new Date();
let dt2 = new Date();

  //   let dt = new Date();

  dt2.setMonth(dt1.getMonth() + 1);
  dt1.setDate(dt1.getDate() - dt1.getDate() + 1);
  dt2.setDate(dt2.getDate() - dt2.getDate() + 1);

  //일요일은0 ,~6까지
  $("#firstTitle").html(dt1.getFullYear() + "." + (dt1.getMonth() + 1));
  $("#secondTitle").html(dt2.getFullYear() + "." + (dt2.getMonth() +1));
  setCalendar("firstCalendarTable", dt1);
  setCalendar("secondCalendarTable", dt2);

let arr =[];
function initClick() {
  $(".calendarSt tr:not(.calendarSt tr:nth-child(1)) td").on(
    "click",
    function (e) {
     	 $(e.target).css("background-color", "rgb(255, 98, 98)");
      $(e.target).css("color", "white");
      let pick = parseInt($(e.target).html());
      checkSet.add(pick);
			if($('#firstCalendar').has($(e.target))){
				
				let y = dt1.getFullYear();
				let m = dt1.getMonth()+1;
				arr.push(new Date(`${y}-${m}-${pick}`));
				
			}else if($('#secondCalendar').has($(e.target))){
				let y = dt2.getFullYear();
				let m = dt2.getMonth()+1;
				arr.push(new Date(`${y}-${m}-${pick}`));
				console.log("ddd")
			}
       if (checkSet.size == 2) {
		let night =	Math.abs(arr[1].getTime()-arr[0].getTime())/(1000*60*60*24);
		if(night>7){
			
			alert("최대 7박까지 가능합니다");
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
        arr=[];
			
		}
		else if(arr[0].getTime()<arr[1].getTime()){
			$(`#${checkin}`).html(arr[0].getFullYear()+"."+(arr[0].getMonth()+1)+"."+arr[0].getDate())
			$(`#${checkout}`).html(arr[1].getFullYear()+"."+(arr[1].getMonth()+1)+"."+arr[1].getDate()+"("+night+"박)")
			$('#night').val(night)
			fullDate = arr[0].getFullYear()+"."+(arr[0].getMonth()+1)+"."+arr[0].getDate()+"/"+arr[1].getFullYear()+"."+(arr[1].getMonth()+1)+"."+arr[1].getDate()+"("+night+"박)";
		}else{
			$(`#${checkout}`).html(arr[0].getFullYear()+"."+(arr[0].getMonth()+1)+"."+arr[0].getDate()+"("+night+"박)")
			$(`#${checkin}`).html(arr[1].getFullYear()+"."+(arr[1].getMonth()+1)+"."+arr[1].getDate())
			$('#night').val(night)
			fullDate = arr[1].getFullYear()+"."+(arr[1].getMonth()+1)+"."+arr[1].getDate()+"/"+arr[0].getFullYear()+"."+(arr[0].getMonth()+1)+"."+arr[0].getDate()+"("+night+"박)";
		
		}
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
        arr=[];
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
  dt2.setMonth(dt1.getMonth() + 1);
  dt1.setDate(dt1.getDate() - dt1.getDate() + 1);
  dt2.setDate(dt2.getDate() - dt2.getDate() + 1);

  $("#firstTitle").html(dt1.getFullYear() + "." + (dt1.getMonth() + 1));
  $("#secondTitle").html(dt2.getFullYear() + "." + (dt2.getMonth() + 1));
  setCalendar("firstCalendarTable", dt1);
  setCalendar("secondCalendarTable", dt2);
  initClick();
}




