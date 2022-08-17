window.addEventListener("scroll", (e) => {
  console.log(window.scrollY);
  if (window.scrollY > 20) {
    $(".nav").css("height", "50px");
    $(".navTit").css("font-size", "20px");
    // $(".logo").css("width", "50px");
    //            $('#nav-logoBox').css('margin-top','3px');
    // $("#nav-ul").css("opacity", "0");
    // $(".navTit").css("font-size", "25px");
    // $(".navTit").css("margin-top", "5px");
  } else {
    $(".nav").css("height", "80px");
    $(".navTit").css("font-size", "35px");
    //         $('#nav-logoBox').css('margin-top','30px');
    // $("#nav-ul").css("opacity", "1");
  }
});

$(".fa-user").on("click", () => {
  $(".navCartBox").fadeToggle();
});
