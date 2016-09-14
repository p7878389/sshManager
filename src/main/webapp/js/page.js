function
pager(param, url, tempId, tableId) {
   var pageService = new Service(url);
    pageService.page(param, {success: pageCallBack}, tempId, tableId);
}

function pageCallBack(data, tempId, tableId) {
    data = data.object;
    var $html = template(tempId, data);
    $("#"+tableId).html("");
    $("#" + tableId).append($html);
    $("#totalPage").html(data.totalPage);
    $("#currentPage").html(data.currentPage);
    $("#totalCount").html(data.totalCount);
}
