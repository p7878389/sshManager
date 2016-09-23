var diaObj;
var userList = {
    //分页参数
    pageParam: function () {
        var userName = $("#userName").val();
        var state = $("#state").val();
        var salt = $("#salt").val();
        var pageParam = {"userName": userName, "state": state, "salt": salt};
        var param = {};
        param.queryParam = pageParam;
        param.url = "../user/pageUser";
        param.tempId = "userPageTemp";
        param.tableId = "userListTab";
        return param;
    },

    queryUser: function () {
        var param = this.pageParam();
        pagerService.queryPage(param);
    },

    firstPage: function () {
        var param = this.pageParam();
        var currentPage = Number($("#currentPage").html());
        param.currentPage = currentPage
        pagerService.firstPage(param);
    },

    //下一页
    nextPage: function () {
        var param = this.pageParam();
        var totalPage = Number($("#totalPage").html());
        var currentPage = Number($("#currentPage").html());
        param.totalPage = totalPage;
        param.currentPage = currentPage;
        pagerService.nextPage(param);
    },

    //上一页
    previousPage: function () {
        var param = this.pageParam();
        var currentPage = Number($("#currentPage").html());
        param.currentPage = currentPage;
        pagerService.previousPage(param);
    },

    //最后一页
    lastPage: function () {
        var param = this.pageParam();
        var totalPage = Number($("#totalPage").html());
        var currentPage = Number($("#currentPage").html());
        param.currentPage = currentPage;
        param.totalPage = totalPage;
        pagerService.lastPage(param);
    },

    //用戶刪除
    userDelete: function (id) {
        var userDelete = new Service('../user');
        userDelete.remove(id, {success: this.userDeleteCallBack});
    },
    userDeleteCallBack: function (data) {
        if (data.errorCode == 0) {
            userList.queryUser();
        } else {
            $.scojs_message(data.msg, $.scojs_message.TYPE_ERROR);
        }
    },

    userUpdateModal: function (userId) {
        var userUpdateModal = new Service('../user');
        userUpdateModal.getById(userId, {success: userUpdateModalCallBack});
        function userUpdateModalCallBack(data) {
            var $html = template('userUpdateTemp', data.object);
            var dialog = new bootDialog('用户修改', $html);
            dialog.htmlElementDialog(updateUser);
        }
    }
};

function updateUser() {
    var userId = $("#updateUserId").val();
    var userName = $("#updateUserName").val();
    var passWord = $("#updatePassWord").val();
    var salt = $("#updateSalt").val();
    var state = $("#updateState").val();
    var param = {"passWord": passWord, "salt": salt, "userId": userId, "state": state, "userName": userName};
    var updateUser = new Service("../user/updateUser");
    updateUser.add(param, {success: updateUserCallBack});
    function updateUserCallBack(data) {
        $.scojs_message('用户信息修改成功', $.scojs_message.TYPE_OK);
    }
}


//分页查询
$("#querUser").click(function () {
    userList.queryUser();
});

//用户新增modal窗口
$("#userAdd").click(function () {
    var dialog = new bootDialog('用户修改', '../admin/user/userAdd.html');
    dialog.loadHtmlDialog();
});

$(function () {
    userList.firstPage();
});