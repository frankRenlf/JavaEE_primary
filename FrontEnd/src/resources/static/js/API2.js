var addTaskButton = document.querySelector(".nav button");
var func = function toDo() {
  // 1. 获取到任务内容的输入框
  var input = document.querySelector(".nav input"); // 2. 获取到输入框内容
  var taskContent = input.value; // 3. 根据内容新建一个元素节点
  var row = document.createElement("div");
  row.className = "row";
  var checkbox = document.createElement("input");
  checkbox.type = "checkbox";
  var span = document.createElement("span");
  span.innerHTML = taskContent;
  var button = document.createElement("button");
  button.innerHTML = "删除";
  row.appendChild(checkbox);
  row.appendChild(span);
  row.appendChild(button); // 4. 把新节点插入到 todo 中
  var todo = document.querySelector(".todo");
  todo.appendChild(row);
};
addTaskButton.onclick = func;
// addTaskButton.onclick = function () {
//   // 上方的部分不变...
//   // 5. 给 checkbox 注册点击事件
//   checkbox.onclick = function () {
//     //
//     var row = this.parentNode; // 注意! 是先触发 checked 为 true, 然后再调用 onclick 函数
//     if (this.checked) {
//       var target = document.querySelector(".done");
//     } else {
//       var target = document.querySelector(".todo");
//     }
//     target.appendChild(row);
//   };
// };
// addTaskButton.onclick = function () {
//   // 上方的部分不变...
//   // 6. 给删除按钮注册点击事件
//   button.onclick = function () {
//     var row = this.parentNode;
//     var grandParent = row.parentNode;
//     grandParent.removeChild(row);
//   };
// };
