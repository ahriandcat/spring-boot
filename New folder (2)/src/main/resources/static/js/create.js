const addressEl = document.getElementById("address");
const btnSave = document.getElementById("btn-save");

btnSave.addEventListener("click", async function () {
  try {
    //lay thong tin o input
    let name = $("#fullname").val();
    let email = $("#email").val();
    let phone = $("#phone").val();
    let address = $("#address").val();
    let password = $("#password").val();
    //goi api
    let res = await axios.post("http://localhost:8080/api/v1/users", {
      name: name,
      email: email,
      phone: phone,
      address: address,
      password: password,
    });
    //thanh cong thi chuyen huong ve index.html
    if (res.data) {
      window.location.href = "/index.html";
    }
  } catch (e) {
    console.log(e);
  }
});
const getProvinces = async () => {
  try {
    let res = await axios.get("https://provinces.open-api.vn/api/p/");
    console.log(res);
    renderProvinces(res.data);
  } catch (e) {
    console.error(e);
  }
};

const renderProvinces = (arr) => {
  let html = "";
  arr.forEach((province) => {
    html += `<option value="${province.name}">${province.name}</option>`;
  });
  addressEl.innerHTML = html;
};

getProvinces();
