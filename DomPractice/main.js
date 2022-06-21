// Truy cập vào thẻ h1 có id=“heading” thay đổi màu chữ thành ‘red’, và in hoa nội dung của thẻ đó
// Thay đổi màu chữ của tất cả thẻ có class “para” thành màu “blue” và có font-size = “20px”
// Thêm thẻ a link đến trang ‘facebook’ ở đằng sau thẻ có class “para-3”
// Thay đổi nội dung của thẻ có id=“title” thành “Đây là thẻ tiêu đề”
// Thêm class “text-bold” vào thẻ có class=“description” (định nghĩa class “text-bold” có tác dụng in đậm chữ)
// Thay thế thẻ có class=“para-3” thành thẻ button có nội dung là “Click me”
// Copy thẻ có class=“para-2” và hiển thị ngay đằng sau thẻ đó
// Xóa thẻ có class=“para-1”

document.querySelector("#heading").style.color = "red";
document.querySelector("#heading").style.textTransform = "uppercase";

$(".para").each(function () {
  $(this).css("color", "blue");
  $(this).css("font-size", "20px");
});

const link = document.createElement("a");
link.innerText = "link FB";
link.href = "https://facebook.com";
document.querySelector(".para-3").insertAdjacentElement("beforebegin", link);

$("#title").text("Đây là thẻ tiêu đề");

$(".description").addClass("text-bold");

const button = document.createElement("button");
button.innerText = "button";
document.body.replaceChild(button, document.querySelector(".para-3"));

const para2Copy = document.querySelector(".para-2").cloneNode(true);
document
  .querySelector(".para-2")
  .insertAdjacentElement("beforebegin", para2Copy);

document.body.removeChild(document.querySelector(".para-1"));
