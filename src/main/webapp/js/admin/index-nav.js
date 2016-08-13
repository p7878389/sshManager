$(function () {

    var userName = sessionStorage.getItem("userName");
    $("#navUserName").html(userName);

    $("#userInfoDialog").bind("click", function () {
        var userId = sessionStorage.getItem("userId");
        userInfo = new Service("../user");
        userInfo.getById(userId, {success: findUserCallback});
    });

    function findUserCallback(data) {
        if (data.errorCode == 0) {
            var $html = template('personalCenter', data.object);
            $html = $html.toString();
            BootstrapDialog.show({
                title: '个人中心',
                message: function () {
                    return $($html);
                },
                buttons: [{
                    label: '保存',
                    icon: 'glyphicon glyphicon-check',
                    cssClass: 'btn-success',
                    action: function () {

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
        }else{
        }
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
    });

    function logoutCallback(data) {
        if (data.errorCode == 0) {
            window.location.href = "../admin/login.html"
        } else {
            $.scojs_message(data.msg, $.scojs_message.TYPE_ERROR);
        }
    }
});