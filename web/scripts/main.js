;(function () {

  $('#userServlet').on('click', function () {

    $.ajax({
      url: "/user/getUsers",
      method: "get",
      data: {
        name: '张三', age: 12, gender: 'male'
      }
    })
      .then((function (res) {
        console.log('success', res);
      }))
      .catch(function (e) {
        console.log('err', e);
      })
  });
})();