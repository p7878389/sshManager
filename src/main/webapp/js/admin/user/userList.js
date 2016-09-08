function firstPage() {
    var param = pageParam();
    pager(param, '../user/pageUser', 'userTableTemp', 'userListTab');
}

function nextPage() {
    var param = pageParam();
    var totalPage = Number($("#totalPage").html());
    var currentPage = Number($("#currentPage").html());
    if (totalPage > currentPage) {
        param.currentPage = (currentPage + 1).toString();
        pager(param, '../user/pageUser', 'userTableTemp', 'userListTab');
    } else {
        dialogMsg("警告","已经是最后一页了");
    }
}

function previousPage() {
    var param = pageParam();
    var currentPage = Number($("#currentPage").html());
    if (currentPage > 1) {
        param.currentPage = (currentPage - 1).toString();
        pager(param, '../user/pageUser', 'userTableTemp', 'userListTab');
    } else {
        dialogMsg("警告","已经是第一页了");
    }
}

function lastPage() {
    var param = pageParam();
    var currentPage = $("#totalPage").html();
    param.currentPage = currentPage;
    pager(param, '../user/pageUser', 'userTableTemp', 'userListTab');
}

function pageParam() {
    var userName = $("#userName").val();
    var state = $("#state").val();
    var salt = $("#salt").val();
    var param = {"userName": userName, "state": state, "salt": salt, "pageSize": "1"};

    return param;
}

$("#querUser").click(function() {
    $("#querUser").attr("disabled",true);
    debugger;
    firstPage();
    $("#querUser").attr("disabled",false);
});

$(function () {
    firstPage();
});