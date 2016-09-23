$(function () {
    var userName = sessionStorage.getItem("userName");
    $("#navUserName").html(userName);
});


$("#userInfoDialog").bind("click", function () {
    var userId = sessionStorage.getItem("userId");
    var userInfo = new Service("../user");
    userInfo.getById(userId, {success: findUserCallback});
});

function findUserCallback(data) {
    var $html = template('personalCenter', data.object);
    var dialog = new bootDialog('个人中心', $html);
    dialog.dialogData(updateUser);
}

function updateUser() {
    var userId = sessionStorage.getItem("userId");
    var personService = new Service("../user/updateUser");
    var passWord = $("#passWord").val();
    var userName = $("#userName").val();
    var salt = $("#salt").val();
    var state = $("#state").val();
    param = {"passWord": passWord, "salt": salt, "userId": userId, "userName": userName, "state": state};
    personService.add(param, updateUserCallBack, updateUserErrorCallBack);
}

function updateUserCallBack(data) {
    $.scojs_message('用户信息修改成功', $.scojs_message.TYPE_OK);
}

function updateUserErrorCallBack(data) {
    $.scojs_message(data.msg, $.scojs_message.TYPE_OK);
}

$("#logout").bind("click", function () {
    BootstrapDialog.show({
        title: '注销',
        message: '确定退出登录吗？',
        type: BootstrapDialog.TYPE_DANGER,
        buttons: [{
            label: '确定',
            icon: 'glyphicon glyphicon-check',
            cssClass: 'btn-success',
            action: function (dialog) {
                logout = new Service("../login/logout");
                logout.add({}, {success: logoutCallback});
            }
        }, {
            label: '关闭',
            icon: 'glyphicon glyphicon-remove',
            cssClass: 'btn-warning',
            action: function (dialog) {
                dialog.close();
            }
        }]
    });
})

function logoutCallback(data) {
    if (data.errorCode == 0) {
        window.location.href = "../admin/login.html"
    } else {
        $.scojs_message(data.msg, $.scojs_message.TYPE_ERROR);
    }
}
