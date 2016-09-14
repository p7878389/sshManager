var userList = {
    //分页参数
    pageParam: function () {
        var userName = $("#userName").val();
        var state = $("#state").val();
        var salt = $("#salt").val();
        var param = {"userName": userName, "state": state, "salt": salt, "pageSize": "1"};
        return param;
    },

    firstPage: function () {
        var param = this.pageParam();
        var currentPage = Number($("#currentPage").html());
        if (currentPage == 1) {
            $.scojs_message("已经是第一页了！", $.scojs_message.TYPE_WARNING);
        }
        pager(param, '../user/pageUser', 'userTableTemp', 'userListTab');
    },

    //下一页
    nextPage: function () {
        var param = this.pageParam();
        var totalPage = Number($("#totalPage").html());
        var currentPage = Number($("#currentPage").html());
        if (totalPage > currentPage) {
            param.currentPage = (currentPage + 1).toString();
            pager(param, '../user/pageUser', 'userTableTemp', 'userListTab');
        } else {
            //dialogMsg("警告", "已经是最后一页了");
            $.scojs_message("已经是最后一页了！", $.scojs_message.TYPE_WARNING);
        }
    },

    //上一页
    previousPage: function () {
        var param = this.pageParam();
        var currentPage = Number($("#currentPage").html());
        if (currentPage > 1) {
            param.currentPage = (currentPage - 1).toString();
            pager(param, '../user/pageUser', 'userTableTemp', 'userListTab');
        } else {
            $.scojs_message("已经是第一页了！", $.scojs_message.TYPE_WARNING);
            //dialogMsg("警告", "已经是第一页了");
        }
    },

    //最后一页
    lastPage: function () {
        var param = this.pageParam();
        var totalPage = Number($("#totalPage").html());
        var currentPage = Number($("#currentPage").html());
        if (currentPage == totalPage) {
            $.scojs_message("已经是最后一页了！", $.scojs_message.TYPE_WARNING);
        }
        pager(param, '../user/pageUser', 'userTableTemp', 'userListTab');
        param.currentPage = currentPage;
    }
};

$("#querUser").click(function () {
    userList.firstPage();
});

var dialogObj;

//用户新增modal窗口
$("#userAdd").click(function () {
    dialogObj = addOrUpdateDialog('用户新增', '../admin/user/userAdd.html');
    dialogObj.open();
});

//用戶刪除
function userDelete(id) {
    userDelete = new Service('../user');
    userDelete.remove(id, {success: userDeleteCallBack});

    function userDeleteCallBack(data) {
        if (data.errorCode == 0) {
            userList.firstPage();
        } else {
            $.scojs_message(data.msg, $.scojs_message.TYPE_ERROR);
        }
    }
}

$(function () {
    userList.firstPage();
});