onload = function () {
  console.log("dddd");
  $("#mainMenu").slideUp();
  for (let i = 1; i < 10; i++) {
    $(`.lo${i}`).hover(
      (e) => {
        $(e.target).css("fill", "rgb(255, 161, 161)");
        $(e.target).css("cursor", "pointer");
        $(`.g${i}`).addClass("mapAni");
      },
      (e) => {
        $(e.target).css("fill", "#F5EDD4");
        $(`.g${i}`).removeClass("mapAni");
      }
    );
  }

  for (let i = 1; i < 10; i++) {
    $(`.lo${i}`).on("click", (e) => {
      console.log($(e).data());

      $("#mainMenu").slideUp(300);
      $("#mainMenu").slideDown(300);
    });
  }
  $(".st102 , .st103").hover(
    (e) => {
      console.log("aaaa");
      $(".lo9").css("fill", "rgb(255, 161, 161)");
      $(`.g9`).addClass("mapAni");
    },
    (e) => {
      $(".lo9").css("fill", "#F5EDD4");
      $(`.g9`).removeClass("mapAni");
    }
  );
};
