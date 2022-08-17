function specialSymbolCheck(e) {
  let len = e.value.length;
  for (let i = 0; i < len; i++) {
    let ch = e.value.charCodeAt(i);
    if (
      (ch >= 65 && ch <= 90) ||
      (ch >= 97 && ch <= 122) ||
      (ch >= 48 && ch <= 57)
    ) {
    } else {
      return false;
    }
  }
  return true;
}

$("#modifyNick").on("keyup", function () {
  if ($(this).val().length >= 2) {
    if (!specialSymbolCheck(this)) {
      $("#nickWarning").html("영문,숫자 이외의 문자는 사용할 수 없습니다");
      u_nick = false;
    } else {
      $.ajax({
        url: "nickOverlap.my",
        type: "post",
        dataType: "text",
        data: $("#modifyNick").val(),
        success: function (data) {
          if (data == "false") {
            $("#nickWarning").html("사용가능한 닉네임입니다");
            u_nick = true;
          } else {
            $("#nickWarning").html("사용이 불가능한 닉네임입니다");
            u_nick = false;
          }
          isFormOk();
        },
        error: function (e) {
          console.log("닉네임 수정 에러 : " + e);
        },
      });
    }
  }
});

$("#modifyPhone").on("keyup", function () {
  console.log($(this).val().length);
  if ($(this).val().length == 3) {
    $(this).val($(this).val() + "-");
  } else if ($(this).val().length == 8) {
    $(this).val($(this).val() + "-");
  } else if ($(this).val().length > 13) {
    $("#phoneWarning").html("올바른 형식이 아닙니다");
  } else {
    $("#phoneWarning").html("");
  }
});
