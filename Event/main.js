let qoutes =
  "Lorem ipsum dolor sit amet consectetur adipisicing elit. Hic nisi debitis enim tempora rerum provident magnam velit corporis! Ipsam voluptatum commodi consectetur molestias sit quod alias ex eius veritatis.Dicta";

let qouteArr = qoutes.split(".");

$("#btn-1").on("click", function () {
  let random = Math.floor(Math.random() * qouteArr.length);
  $("#text").text(qouteArr[random]);
});

$("#btn-2").on("click", function () {
  let randomColor = Math.floor(Math.random() * 16777215).toString(16);
  let randomHex = "#" + randomColor;
  $("#text").css("color", randomHex);
});

$("#btn-3").on("click", function () {
  $("#text").css("background-color", random_rgba);
});

function random_rgba() {
  var o = Math.round,
    r = Math.random,
    s = 255;
  return (
    "rgba(" +
    o(r() * s) +
    "," +
    o(r() * s) +
    "," +
    o(r() * s) +
    "," +
    r().toFixed(1) +
    ")"
  );
}

$("#input").on("keydown", function (e) {
  if (e.keyCode == 13) {
    alert($(this).val());
  }
});

// // Lắng nghe sự kiện keyup
// document.addEventListener("keyup", function () {
//   console.log("keyup");
// });

// // Lắng nghe sự kiện keypress
// document.addEventListener("keypress", function () {
//   console.log("keypress");
// });


// function funcC() {
//   setTimeout(function () {
//     console.log("three");
//   }, 3000);
// }

// // Công việc 4 : Giả sử sau 4s thì thực hiện xong
// function funcD() {
//   setTimeout(function () {
//     console.log("four");
//   }, 4000);
// }

// // Tổng thời gian thực hiện hết 2 cv3 và cv4 là 4s
// // Thực hiện công việc
// funcC();
// funcD();