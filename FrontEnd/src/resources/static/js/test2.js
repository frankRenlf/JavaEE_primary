var submit = document.querySelector(".submit");
submit.onclick = function () {
  // 1. 获取到编辑框内容
  var edits = document.querySelectorAll(".edit");
  var from = edits[0].value;
  var to = edits[1].value;
  var message = edits[2].value;
  console.log(from + "," + to + "," + message);
  if (from == "" || to == "" || message == "") {
    return;
  }
  // 2. 构造 html 元素
  var row = document.createElement("div");
  row.className = "row";
  row.innerHTML = from + "对" + to + "说: " + message;
  // 3. 把构造好的元素添加进去
  var container = document.querySelector(".container");
  container.appendChild(row);
  // 4. 同时清理之前输入框的内容
  for (var i = 0; i < 3; i++) {
    edits[i].value = "";
  }
};
