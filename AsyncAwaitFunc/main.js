let money = 35;

const buyIphone = () => {
  return new Promise(function (resolve, reject) {
    if (money >= 33) {
      resolve("Mua iphone thanh cong");
    } else {
      reject("ko du tien mua, di lam di");
    }
  });
};

const buyAirpod = () => {
  return new Promise(function (resolve, reject) {
    if (money >= 33 + 5) {
      resolve("Mua them airpod thanh cong");
    } else {
      reject("ko du tien mua airpod");
    }
  });
};

// async function buy() {

// }

const buy = async () => {
  try {
    let res = await buyIphone();
    console.log(res);
    let res1 = await buyAirpod();
    console.log(res1);
  } catch (e) {
    console.log(e);
  }
  return "di ve";
};

buy()
  .then((res) => {
    console.log(res);
  })
  .catch((error) => {
    console.log(error);
  });
