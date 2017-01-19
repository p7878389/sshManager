/*var userList = {
 //分页参数
 pageParam: function () {
 var userName = $("#userName").val();
 var state = $("#state").val();
 var salt = $("#salt").val();
 var pageParam = {"userName": userName, "state": state, "salt": salt};
 var param = {};
 param.queryParam = pageParam;
 param.url = "/user/pageUser";
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
 };*/

/*
var userList1 = function () {
};
userList1.prototype = {
    pageParam: function () {
        var userName = $("#userName").val();
        var state = $("#state").val();
        var salt = $("#salt").val();
        var pageParam = {"userName": userName, "state": state, "salt": salt};
        var param = {};
        param.queryParam = pageParam;
        param.url = "/user/pageUser";
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

var userList = new userList1();

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
});*/

var dataTable=function () {
    var tableInit = new Object();
    tableInit.init=function () {
        $('#tb_departments').bootstrapTable({
            url: '/user/pageUser',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            //toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            //sortOrder: "asc",                   //排序方式
            //queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "userId",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'userName',
                title: '用户名称'
            }, {
                field: 'salt',
                title: '盐'
            }, {
                field: 'state',
                title: '状态'
            }]
        });
    }
}
