var pagerService = {

    pager: function (param, url, tempId, tableId) {
        var pageService = new Service(url);
        pageService.page(param, {success: this.pageCallBack}, tempId, tableId);
    },

    pageCallBack: function (data, tempId, tableId) {
        data = data.object;
        var $html = template(tempId, data);
        $("#" + tableId).html("");
        $("#" + tableId).append($html);
        $("#totalPage").html(data.totalPage);
        $("#currentPage").html(data.currentPage);
        $("#totalCount").html(data.totalCount);
    },

    param: function (pageParam) {
        this.pager(pageParam.queryParam, pageParam.url, pageParam.tempId, pageParam.tableId);
    },

    queryPage: function (pageParam) {
        this.param(pageParam);
    },

    firstPage: function (pageParam) {
        if (pageParam.currentPage == 1) {
            $.scojs_message("已经是第一页了！", $.scojs_message.TYPE_WARNING);
            return;
        }
        this.param(pageParam);
    },

    //下一页
    nextPage: function (pageParam) {
        var currentPage = pageParam.currentPage;
        var totalPage = pageParam.totalPage;
        if (totalPage > currentPage) {
            currentPage = (currentPage + 1).toString();
            pageParam.queryParam.currentPage = currentPage;
            this.param(pageParam);
        } else {
            $.scojs_message("已经是最后一页了！", $.scojs_message.TYPE_WARNING);
        }
    },

    //上一页
    previousPage: function (pageParam) {
        var currentPage = pageParam.currentPage;
        if (currentPage > 1) {
            pageParam.queryParam.currentPage = (currentPage - 1).toString();
            this.param(pageParam);
        } else {
            $.scojs_message("已经是第一页了！", $.scojs_message.TYPE_WARNING);
        }
    },

    //最后一页
    lastPage: function (pageParam) {
        var currentPage = pageParam.currentPage;
        var totalPage = pageParam.totalPage;
        if (currentPage == totalPage) {
            $.scojs_message("已经是最后一页了！", $.scojs_message.TYPE_WARNING);
        }
        pageParam.queryParam.currentPage = totalPage;
        this.param(pageParam);
    }
}