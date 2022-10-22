function checkLogin() {
    $.ajax({
        type: "get",
        url: "login",
        success: function (body) {
        },
        error: function (body) {
            location.assign("blog_login.html");
        },
    });
}

