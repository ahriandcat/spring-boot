const btn = document.querySelector("#btn");
const imageEl = document.querySelector("#image");
const selectEl = document.querySelector("#breed-list");

async function getList() {
  try {
    let res = await axios.get("https://dog.ceo/api/breeds/list/all");
    displayBreedList(res.data.message);
  } catch (error) {
    console.log(error);
  }
}

function displayBreedList(obj) {
  let keys = Object.keys(obj);
  let html = "";
  for (let i = 0; i < keys.length; i++) {
    html += `<option value="${keys[i]}">${keys[i]}</option>`;
  }
  selectEl.innerHTML = html;
}

window.onload = getList();

btn.addEventListener("click", function (e) {
  getRadom();
});

async function getRadom() {
  let link1 = "https://dog.ceo/api/breed/";
  let link2 = $("#breed-list").val();
  let link3 = "/images/random";
  let link = link1 + link2 + link3;
  try {
    //b1 : goi api
    let res = await axios.get(link);
    //b2 : lay ket qua tra ve tu api va hien thi
    imageEl.src = res.data.message;
  } catch (error) {
    console.log(error);
  }
}
