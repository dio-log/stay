// $('input[type="number"').on("keyup", function (e) {
//     let len = $(this).val().length();
//     if (len == 3) {
//         $(this).val($(this).val() + ",");
//     }else if()
// });
console.log("dd");
onload = function () {
  $(".chargePeople").on("mouseup", function () {
    // console.log(this);
    // console.log($(this).find("input"));
    // console.log($(this).find("input").data());

    let ch = $(this).find("input").data("ch");
    let value = $(this).find("input").val();
    $(`#p_${ch}`).html(parseInt(value) + 1);
  });
};
