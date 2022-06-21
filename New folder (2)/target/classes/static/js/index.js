// Khai báo mảng user ban đầu để hứng dữ liệu từ phía backend
let userListEl = document.querySelector("tbody");
// Định nghĩa API URL
const API_URL = "/api/v1";

// Định nghĩa API lấy danh sách user (bao gồm cả tìm kiếm user)
// Nếu term != "" --> tìm kiếm user
// Ngược lại --> lấy danh sách user
function getUsersAPI(term = "") {
  let url = `${API_URL}/users`;
  if (term) {
    url = `${API_URL}/users/search?name=${term}`;
  }

  return axios.get(url);
}

// Function dùng để render dữ liệu bảng
function renderUser(arr) {
  // Viết code tại đây

  userListEl.innerHTML = "";
  let html = "";
  for (let i = 0; i < arr.length; i++) {
    const t = arr[i];
    html += `<tr>
                  <td>${t.id}</td>
                  <td>${t.name}</td>
                  <td>${t.email}</td>
                  <td>${t.phone}</td>
                  <td>${t.address}</td>
                  <td>
                    <a href="/detail/{t.id}" class="btn btn-success"
                      >Xem chi tiết</a
                    >
                    <button class="btn btn-danger">Xóa</button>
                  </td>
                </tr>`;
  }
  userListEl.innerHTML = html;
}

// Gọi API và hiển thị ra dữ liệu
async function getUsers(term = "") {
  try {
    // 1. Gọi API lấy danh sách users
    // 2. Lưu danh sách users từ API trả về vào biến users (để phục vụ chức năng thêm, xóa, hiển thị danh sách user)
    // 3. Render dữ liệu ra ngoài giao diện
    let res = await getUsersAPI(term);
    users = res.data;
    renderUser(users);
  } catch (error) {
    console.log(error);
  }
}

$("#search").change(function () {
  let val = $(this).val();
  console.log(val);
  getUsers(val);
});
