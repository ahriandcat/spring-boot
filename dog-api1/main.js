const btn = document.querySelector("#btn");
const imageEl = document.querySelector(".image img");

btn.addEventListener("click", function () {
  getRadom();
});

async function getRadom() {
  try {
    //b1 : goi api
    let res = await axios.get("https://dog.ceo/api/breeds/image/random");
    //b2 : lay ket qua tra ve tu api va hien thi
    imageEl.src = res.data.message;
  } catch (error) {
    console.log(error);
  }
}
