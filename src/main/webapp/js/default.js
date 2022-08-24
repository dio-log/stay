window.addEventListener("scroll", (e) => {
 // console.log(window.scrollY);
  if (window.scrollY > 20) {
    $(".nav").css("height", "50px");
    $(".nav").css("background-color", "white");
    $(".nav a").css("color", "#f7323f");
    $(".navTit").css({ "font-size": "25px", color: "black" });
    $(".fa-user").css("color", "black");
    $(".logo").css("color", "#f7323f");
  } else {
    $(".nav").css("height", "80px");
    $(".nav").css("background-color", "#f7323f");
    $(".nav a").css("color", "white");
    $(".navTit").css({ "font-size": "35px", color: "white" });
    $(".fa-user").css("color", "white");
    $(".logo").css("color", "white");
  }
});

$(".fa-user").on("click", () => {
  $(".navCartBox").fadeToggle();
});

$(".plusBtn").on("click", function () {
  this.previousElementSibling.stepUp();
});
$(".minusBtn").on("click", function () {
  this.nextElementSibling.stepDown();
});

$("#setPeopleBtn").on("click", function () {
  //성인 4, 아동 0, 반려동물 2
  let adult = $(".setPeopleAdult").val();
  let kids = $(".setPeopleKids").val();
  let pet = $(".setPeoplePet").val();
  $("#peopleInput").val(`성인 ${adult}, 아동 ${kids}, 반려동물 ${pet}`);
});


