const provinces = document.querySelector("#province");
const districts = document.querySelector("#district");
const communes = document.querySelector("#commune");

let provinceArr = [];
async function getProvinces() {
  try {
    let res = await axios.get("https://provinces.open-api.vn/api/p/");
    displayProvince(res.data);
    console.log(provinceArr);
  } catch (error) {
    console.log(error);
  }
}

function displayProvince(arr) {
  let html = "";
  for (let i = 0; i < arr.length; i++) {
    html += `<option value="${arr[i].code}">${arr[i].name}</option>`;
    provinceArr.push(arr[i]);
  }
  provinces.innerHTML = html;
}

getProvinces();

async function getDistrict() {}

$("#province").change(function (e) {
  let prName = $(this).val();
  let disName = "";
  provinceArr.each(function () {
    if ($(this).val().name === prName) {
    }
  });
});
