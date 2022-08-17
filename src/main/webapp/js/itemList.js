$(".optLabel").on("click", function (e) {
  let tmp = e.target.previousElementSibling;
  if (tmp.checked) {
    tmp.click();
  } else {
    tmp.click();
  }
});

$(".cb-square-16").on("click", function (e) {
  let tmp = e.target.previousElementSibling;
  if (tmp.checked) {
    tmp.checked = false;
    // e.target.style.color = "white";

    e.target.style.backgroundColor = "white";
  } else {
    tmp.checked = true;

    e.target.style.backgroundColor = "rgb(255, 35, 35)";
  }
});
