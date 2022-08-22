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

onload = function () {
  console.log("서치워드" + $("#beSearchedWord").html());
  $.ajax({
    url: "selectItemList.it",
    type: "post",
    dataType: "JSON",
    data: { searchWord: $("#beSearchedWord").html() },
    success: function (data) {
      let imgPath = data[0].item_imgpath.split(',')[1];
      console.log(imgPath)
      $("#itemListWrap").append(`
      <li>
              <a href="itemView.it?item_no=${data.item_no}">
                <img src="${imgPath}" alt="" />
                <input
                  type="checkbox"
                  class="cbSt-heart"
                  style="display: none"
                /><label for="item_no1"
                  ><i class="fa-solid fa-heart heartStyle"></i
                ></label>
                <div class="itemTitle">
                  <h1 style="margin-bottom: 5px">${data.item_name}</h1>
                  <div>
                    <p><span>호텔</span> <span>등급미정</span></p>
                    <p><span>${data.item_grade}</span><span>(${data.item_reviewCnt})</span></p>
                  </div>
                  <div style="text-align: right">
                    <p></p>
                    <p>${data.item_price}원</p>
                  </div>
                </div>
              </a>
            </li>
      `);
    },
    error: function (r, sts, s) {
      console.log(e + "/" + sts);
    },
  });
};
