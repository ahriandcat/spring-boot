//lay thong tin id tren url
let param = new URLSearchParams(window.location.search);
const addressEl = document.getElementById("address");

let id = param.get("id");
console.log(id);

let imagePath = [];
//lay thong tin user
const getUser = async (id) => {
  let url = "/api/v1/users/" + id;
  try {
    let resUser = await axios.get(url);
    console.log(resUser.data);
    $("#fullname").val(resUser.data.name);
    $("#email").val(resUser.data.email);
    $("#phone").val(resUser.data.phone);
    $("#address").val(resUser.data.address);
    $("#avatar-preview").val(resUser.data.avatar);
  } catch (e) {
    console.log(e);
  }
};

const getProvinces = async () => {
  try {
    let res = await axios.get("https://provinces.open-api.vn/api/p/");
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
async function init() {
  await getProvinces();
  await getUser(id);
}

init();

//render ra danh sach img
let imgItem = document.querySelector(".image-container");
const getFile = async (id) => {
  let url = "/api/v1/users/" + id + "/files";
  try {
    let res = await axios.get(url);
    console.log(res.data);
    imagePath = res.data;
    renderImg(res.data);
    // renderImg(res.data);
  } catch (e) {
    console.log(e);
  }
};

const renderImg = (arr) => {
  let img = "";
  arr.forEach((image) => {
    img += `
    <div class="image-item">
    <img
                    src="${image}"
                    alt="ảnh"
                  />
                  </div>`;
  });
  imgItem.innerHTML = img;
  $(".image-item").click(function () {
    $("#btn-delete-image").prop("disabled", false);
    $(".image-item").each(function () {
      $(this).removeClass("border-primary");
      $(this).removeClass("selected");
    });
    $(this).addClass("border-primary");
    $(this).addClass("selected");
  });
  //xoa anh
  $("#btn-delete-image").click(async function () {
    let url = $(".selected").children("img").attr("src");
    console.log(url);
    try {
      let res = await axios.delete(url);
      for (let i = 0; i < imagePath.length; i++) {
        if (url.includes(imagePath[i])) {
          imagePath.splice(i, 1);
        }
      }
      renderImg(imagePath);
    } catch (e) {
      console.log(e);
    }
  });
};

$("#btn-modal-image").click(function () {
  getFile(id);
});

//upload file
const uploadImage = async (image) => {
  const formData = new FormData();
  formData.append("file", image);
  let newImagePath = await axios.post(
    `/api/v1/users/${id}/upload-file`,
    formData
  );
  imagePath.push(newImagePath.data);
  renderImg(imagePath);
};

$("#avatar").change(function (e) {
  uploadImage(e.target.files[0]);
});
