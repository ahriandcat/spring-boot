// const heading = document.getElementById("heading");
// console.log(heading);

const para2 = document.querySelector(".para");
console.log(para2);

// const para3 = document.querySelector(".para:nth-child(4)")
// console.log(para3);

// const para3 = document.querySelector(".para + .para");
// console.log(para3);

const para3 = para2.nextElementSibling;
console.log(para3);

const ul1 = document.querySelector(".box > ul");
console.log(ul1);

const nodeList = document.querySelectorAll("p");
console.log(nodeList);

// su dung for

for (let i = 0; i < nodeList.length; i++) {
  nodeList[i].style.color = "red";
  nodeList[i].style.backgroundColor = "black";
}
// => ko phai la array

//su dung phuong thuc map

// Array.from(nodeList).map((e) => {
//   e.style.backgroundColor = "black";
//   e.style.color = "red";
// });

//lay ra noi dung phan tu

console.log(heading.innerHTML);
console.log(heading.innerText);
console.log(heading.textContent);

console.log(ul1.innerHTML);
console.log(ul1.innerText);
console.log(ul1.textContent);

// innerHTML chen duoc duoi dang the
heading.innerHTML = "hello";
heading.innerHTML = "<span>HELLO</span>";

//them phan tu vao DOM
const newPara = document.createElement("p");
newPara.innerText = "New Para";
newPara.style.color = "red";
console.log(newPara);

//them vao vi tri chi dinh
// document.body.insertBefore(newPara, para2);

//cach 2
para3.insertAdjacentElement("afterEnd", newPara);

//................................................................

document
  .querySelector(".box")
  .insertAdjacentHTML("beforebegin", "<p>The new tag</p>");

//xoa phan tu
// document.querySelector(".box").removeChild(document.querySelector(".li"));

// thay the phan tu
const link = document.createElement("a");
link.innerText = "link Google";
link.href = "https://google.com";
document.body.replaceChild(link, document.querySelector(".para"));

//sao chep
const boxCopy1 = document.querySelector(".box").cloneNode(true);
const boxCopy2 = document.querySelector(".box").cloneNode(false);

console.log(boxCopy1);
console.log(boxCopy2);

document.body.appendChild(boxCopy1);

//class list
document.querySelector(".box").classList.add("text-big", "text-bold");
document.querySelector(".box").classList.remove("text-big");
console.log(document.querySelector(".box").classList.contains("text-big"));

setInterval(function () {
  document.querySelector(".box").classList.toggle("text-big");
}, 1000);
