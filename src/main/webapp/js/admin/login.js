$(function () {
    $("#signError").hide();
    $('#loginForm')
        .bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                live: "submitted",
                fields: {
                    userName: {
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            }
                        }
                    },
                    passWord: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            }
                        }
                    }
                }
            }
        ).on('success.form.bv', function (e) {
        e.preventDefault();
        var $form = $(e.target);
        var bv = $form.data('bootstrapValidator');
        var loginService = new Service("../login/loginIn");
        loginService.add({
            "userName": $("#userName").val(),
            "passWord": $("#passWord").val()
        }, {success: loginCallback}, "#accountLogin");
    });
    //登录回调函数
    function loginCallback(data) {
        if (data.errorCode == 0) {
            sessionStorage.setItem("userName", data.object.userName);
            sessionStorage.setItem("userId", data.object.userId);
            window.location.href = "../admin/index-home.html" + "?_" + (new Date().valueOf());
            ;
        } else {
            $.scojs_message(data.msg, $.scojs_message.TYPE_WARNING);
        }
    }
});