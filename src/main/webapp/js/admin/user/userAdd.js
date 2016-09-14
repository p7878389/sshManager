$("#saveUser").click(function () {
    var $form = $("#userAddForm");
    var userName = $form.find('[id="userName"]').val();
    if (userName == "") {
        $.scojs_message('用户名不能为空', $.scojs_message.TYPE_WARNING);
        return;
    }

    var passWord = $form.find('[id="passWord"]').val();
    if (passWord == "") {
        $.scojs_message('密码不能为空', $.scojs_message.TYPE_WARNING);
        return;
    }

    var salt = $form.find('[id="salt"]').val();
    var state = $form.find('[id="state"]').val();

    var param = {"userName": userName, "passWord": passWord, "salt": salt, "state": state};
    var userAddService = new Service("../user/saveUser");
    userAddService.add(param, {success: userAddCallBack});
});

function userAddCallBack(data) {
    if (data.errorCode == 0) {
        dialogObj.close();
        dialogMsg('提示', '用户新增成功');
        var $form = $("#userAddForm");
        var passWord = $form.find('[id="passWord"]').val('');
        var userName = $form.find('[id="userName"]').val('');
        var salt = $form.find('[id="salt"]').val('');
        var state = $form.find('[id="state"]').val('')
        userList.firstPage();
    } else {
        $.scojs_message(data.msg, $.scojs_message.TYPE_ERROR);
    }
}
