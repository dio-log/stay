$("#itemTabUl").on("click", () => {
  if ($("#itemTab_room").is(":checked")) {
    $("#itemTab_roomDetailBox").css("display", "block");
    $("#itemTab_infoDetailBox").css("display", "none");
    $("#itemTab_reviewDetailBox").css("display", "none");
  } else if ($("#itemTab_info").is(":checked")) {
    $("#itemTab_roomDetailBox").css("display", "none");
    $("#itemTab_infoDetailBox").css("display", "block");
    $("#itemTab_reviewDetailBox").css("display", "none");
  } else if ($("#itemTab_review").is(":checked")) {
    $("#itemTab_roomDetailBox").css("display", "none");
    $("#itemTab_infoDetailBox").css("display", "none");
    $("#itemTab_reviewDetailBox").css("display", "block");
  }
});

function itemTabToggle(e) {
  if ($(e.target).is(":checked")) {
    $("#itemTab_roomDetaillBox").toggle("displayNone");
    $("#itemTab_infoDetailBox").toggle("displayNone");
    $("#itemTab_reviewDetailBox").toggle("displayNone");
  }
}

$(".fa-plus").on("click", function () {
  this.previousElementSibling.stepUp();
  let ch= $(this).prev().data('ch');
   let chVal  = $(this).prev().val()
  //   $(this).prev().spinner("stepUp");
  if(ch==="adult"){
	$('#p_adult').val(chVal)
}else if(ch==="child"){
	$('#p_child').val(chVal)
}else if(ch==="pet"){
	$('#p_pet').val(chVal)
}
});


$(".fa-minus").on("click", function () {
  this.nextElementSibling.stepDown();
    let ch= $(this).next().data('ch');
   let chVal  = $(this).next().val()
  if(ch==="adult"){
	$('#p_adult').val(chVal)
}else if(ch==="child"){
	$('#p_child').val(chVal)
}else if(ch==="pet"){
	$('#p_pet').val(chVal)
}
});




  $("#addRoomBtn").on("click", () => {
    $("#roomMoreBox").fadeIn();
  });

  $("#roomMoreCancelBtn").on("click", function () {
    $("#fileListBox").empty();
    $("#roomMoreImgCont").empty();
    $("#roomMoreBox").css("display", "none");
    $("#roomUploadForm")[0].reset();
  });





let cnt = 0;
function leftSideBtn() {
  if (cnt <= 0) return;
  --cnt;
  $("#roomMoreSlide").css("left", `${-400 * cnt}px`);
}
function rightSideBtn() {
  ++cnt;
  $("#roomMoreSlide").css("left", `${-400 * cnt}px`);
}

function closeBtn(e) {
  console.log($(e).parent());
  $(e).parent().css("display", "none");
}

$("#roomSaveBtn").on("click", function () {
  // for (let inputValue of $("#roomUploadForm input")) {
  //   if (inputValue.value == "") {
  //     alert("필수항목이 체크되지 않았습니다");

  //     return;
  //   }
  // }
  let firstImg = $("#roomMoreImgInput")[0].files[0];
  let arr = [];
  $("input:checkbox[name=room_extraopt]:checked").each(function () {
    arr.push(this.value);
  });
  $("#room_extraopt").val(arr.toString());

  let formData = new FormData($("#roomUploadForm")[0]);

  //아아템넘버, 룸넘버 그냥 할당하고 반환

  $.ajax({
    url: "insertRoom.it",
    type: "POST",
    processData: false,
    contentType: false,
    data: formData,
    dataType: "json",
    success: function (re) {
      let reader = new FileReader();
      reader.readAsDataURL(firstImg);
      let result;
      reader.onload = function () {
        result = reader.result;
        $("#itemTab_roomDetailBox").append(`
        <div class="itemTab_roomDetailSt">
        <div style="position: relative">
          <img src="${result}" alt="깨짐" />
         <p class="itemTab_roomDetailMore" data-no="${re.item_no}&${
          re.room_no
        }">더 보기</p>
        </div>
        <div class="itemTab_roomDetailRight">
          <p>${re.room_name}</p>
       
          <p>1박 가격 <span>${re.room_price}</span></p>
          <p>대실 가격 <span>${re.room_part_price}</span></p>
          <p>
            쿠폰적용가
            <span>${re.room_price * 0.9}</span>
          </p>
         <a href=""> <button  type="button" class="whiteBtnSt">수정하기</button>
       </a> 
        </div>
      </div>
        `);
        $("#fileListBox").empty();
        $("#roomMoreImgCont").empty();
        $("#roomUploadForm")[0].reset();
         let dataTransfer = new DataTransfer();
       document.querySelector("#roomMoreImgInput").files = dataTransfer.files;
      fileList = new Set(dataTransfer.files);
        $("#roomMoreBox").css("display", "none");
      };
    },
    error: function (e) {
      alert(e);
      console.log(e);
    },
  });
});

function sample6_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ""; // 주소 변수
      var extraAddr = ""; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if (data.userSelectedType === "R") {
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== "" && data.apartment === "Y") {
          extraAddr +=
            extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (extraAddr !== "") {
          extraAddr = " (" + extraAddr + ")";
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        document.getElementById("item_addr_extra").value = extraAddr;
      } else {
        document.getElementById("item_addr_extra").value = "";
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      // document.getElementById("sample6_postcode").value = data.zonecode;
      document.getElementById("item_addr").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("item_addr_detail").focus();
    },
  }).open();
}
let itemImgCnt = 0;
$("#itemMainImgInput").on("change", (e) => {
  // let files = Array.from(e.target.files);
  let files = e.target.files;
  let firstFlag = true;
  [...files].forEach((file) => {
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      if (firstFlag) {
        console.log("ddd");
        $("#mainImg").attr("src", reader.result);
        $("#mainImg").fadeIn(300);
        let img = $("<img>");
        img.attr("src", reader.result);
        img.css("left", `${120 * itemImgCnt}px`);
        img.css("opacity", "1");
        itemImgCnt++;
        $("#itemSubImgBox").append(img);
        firstFlag = false;
      } else {
        let img = $("<img>");
        img.attr("src", reader.result);
        img.css("left", `${120 * itemImgCnt}px`);
        img.css("opacity", "0.5");
        itemImgCnt++;
        $("#itemSubImgBox").append(img);
      }
    };
  });
});

$("#itemSubImgBox").on("click", function (e) {
  //클릭한 이미지 left 가져와서 이미지 모두 해당 값만큼 빼줌,
  //빼준다음 left가 마이너스인건 최대 left값으로 이미지 이어붙인다
  let clickImgLeft = e.target.style.left.split("px", 1);
  moveImg(this, clickImgLeft);
  $("#mainImg").css("display", "none");
  $(e.target).css("left", "0px");
  $(e.target).css("opacity", "1");
  $("#mainImg").attr("src", e.target.src);
  $("#mainImg").fadeIn(300);
  setOpacity(this);
});

//모든 이미지가 들어있는 박스인 this가 와야함
function setOpacity(e) {
  for (let i of $(e).children("img")) {
    if (i.src == $("#mainImg").attr("src")) {
      i.style.opacity = "1";
    } else {
      i.style.opacity = "0.4";
    }
  }
}
//모든 이미지가 들어있는 박스인 this가 와야함
function getMaxLeft(e) {
  $(e).children("img");
  let tmp = 0;
  for (let obj of $(e).children("img")) {
    if (obj.style.left.split("px", 1) >= tmp) {
      tmp = obj.style.left.split("px", 1);
    }
  }
  return tmp;
}
function moveImg(e, clickImgLeft) {
  for (let obj of $(e).children("img")) {
    let currentLeft = obj.style.left.split("px", 1);
    //다옮겨주고
    obj.style.left = `${currentLeft - clickImgLeft}px`;
  }
  //최대 left 가져와서

  for (let obj of $(e).children("img")) {
    let maxLeft = getMaxLeft(e);
    if (obj.style.left.split("px", 1) < 0) {
      obj.style.left = `${parseInt(maxLeft) + 120}px`;
      console.log("dd" + obj.style.left);
    }
  }
}

let fileList = new Set();
$("#roomMoreImgInput").on("change", function (e) {
  // let arr = Array.from(e.target.files);
  //기존에 업로드된 파일이면 삭제
  let arr = [...e.target.files];
  let len = e.target.files.length;
  for (let i = 0; i < len; i++) {
    for (let file of [...fileList]) {
      //여기서 비교할때 [...e.target.files]말고 arr 쓰면 반복문 돌다가 길이변경되서 에러
      if (file.name === [...e.target.files][i].name) {
        arr.splice(i, 1);
      }
    }
  }

  //기존에 없는 파일만 출력하고 저장
  arr.forEach((data) => {
    fileList.add(data);
    let reader = new FileReader();
    reader.readAsDataURL(data);
    reader.onload = function () {
      let img = $("<img>");
      let p = $("<p></p>");
      let span = $("<span></span>");
      let btn = $("<button></button>");
      img.attr("src", reader.result);
      img.data("name", data.name);

      $("#roomMoreImgCont").append(img);

      span.html(data.name);
      btn.html('<i class="fa-solid fa-xmark"></i>');
      btn.attr("onclick", "imgDelete(this)");
      p.append(span);
      p.append(btn);
      $("#fileListBox").append(p);

      let dataTransfer = new DataTransfer();
      fileList.forEach((file) => {
        dataTransfer.items.add(file);
        // console.log(file);
        // console.log(dataTransfer.files);
      });
      document.querySelector("#roomMoreImgInput").files = dataTransfer.files;
    };
  });
});

function imgDelete(e) {
  let dataTransfer = new DataTransfer();
  //p 태그 삭제
  $(e).parent().remove();

  fileList.forEach((file) => {
    //삭제된 p태그와 일치하는 이름의 파일을 찾아 제외하고 fileList로 재구성
    if ($(e).prev().html() != file.name) {
      dataTransfer.items.add(file);
    } else {
      let arr = Array.from($("#roomMoreImgCont img"));
      arr.forEach((img) => {
        //미리보기중 이름이 일치하는 이미지 삭제
        if ($(img).data("name") == file.name) {
          $(img).remove();
        }
      });
    }
  });
  //재구성된  fileList를 실제 input 값으로 업데이트
  document.querySelector("#roomMoreImgInput").files = dataTransfer.files;
  // fileList = Set.from(dataTransfer.files);
  fileList = new Set(dataTransfer.files);
}

$(".fa-circle-exclamation").on("click", function (e) {
  $(e.target).nextAll("div").eq(0).css("display", "block");
  $(e.target).nextAll("div").eq(0).css("z-index", "50000");
});

$("body").on("click", function (e) {
  if ($(e.target).data("ex") == "ex") {
    $(".writeEx").toggle();
  }
});


