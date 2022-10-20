function checkLogin() {
  $.ajax({
    type: "get",
    url: "login",
    success: function (body) {},
    error: function (body) {
      location.assign("blog_login.html");
    },
  });
}
function getUserInfo() {
  $.ajax({
    type: "get",
    url: "userinfo",
    success: function (body) {
      console.log(typeof body);
      console.log(body);
      let username = document.querySelector(".card h3");
      let number = document.querySelector(".number");
      username.innerHTML = body.username;
      number.innerHTML = body.password;
    },
  });
}
