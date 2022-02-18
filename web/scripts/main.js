;(function () {

  $('#usersServlet').on('click', function () {

    $.ajax({
      url: "/users/getInfo",
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

  $('#demoServlet').on('click', function () {

    $.ajax({
      url: "/demo/getInfo",
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