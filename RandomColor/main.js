// $("button").click(function () {
//   $.ajax({
//     type: "GET",
//     url: "http://localhost:8080/random-color?type=4",
//     success: function (response) {
//       console.log(response);
//     },
//     error: function (error) {
//       console.log(error);
//     },
//   });
// });

// const btn = document.querySelector("button");
// btn.addEventListener("click", async function () {
//   let weight = $("#weight").val();
//   let height = $("#height").val();
//   let url =
//     "http://localhost:8080/bmi-cal1?weight=" + weight + "&height=" + height;
//   try {
//     let res = await axios.get(url);
//     console.log(res);
//     $("#input").val(res.data);
//   } catch (e) {
//     alert(e.response.data.message);
//   }
// });

$("#weight").on("change", function () {
  let weight2 = $("#weight").val();
  if (weight2 <= 0) {
    $("#hidden").removeClass("hidden");
    $("#hidden").css("color", "red");
  } else {
    $("#hidden").addClass("hidden");
  }
});

const btn = document.querySelector("button");
btn.addEventListener("click", async function () {
  let weight2 = $("#weight").val();
  let height2 = $("#height").val();

  try {
    let res = await axios.post("http://localhost:8080/bmi-cal2", {
      weight: weight2,
      height: height2,
    });
    console.log(res);
    $("#input").val(res.data);
  } catch (e) {
    alert(e.response.data.message);
  }
});
