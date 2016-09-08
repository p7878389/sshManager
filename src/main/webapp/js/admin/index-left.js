$(function () {

    // function treeData(rows) {
    //     debugger;
    //     var nodes = [];
    //     for (var i = 0; i < rows.length; i++) {
    //         var row = rows[i];
    //         if (!exists(rows, row.parentId)) {
    //             nodes.push({
    //                 id: row.resourceId,
    //                 text: row.resourceName
    //             });
    //         }
    //     }
    //
    //     var toDo = [];
    //     for (var i = 0; i < nodes.length; i++) {
    //         toDo.push(nodes[i]);
    //     }
    //     while (toDo.length) {
    //         var node = toDo.shift();
    //         for (var i = 0; i < rows.length; i++) {
    //             var row = rows[i];
    //             if (row.parentId == node.id) {
    //                 var child = {
    //                     id: row.resourceId,
    //                     href: row.resourceUrl,
    //                     text: row.resourceName
    //                 };
    //                 if (node.nodes) {
    //                     node.nodes.push(child);
    //                 } else {
    //                     node.nodes = [child];
    //                 }
    //                 toDo.push(child);
    //             }
    //         }
    //     }
    //     return nodes;
    // }
    //
    // function exists(rows, parentId) {
    //     for (var i = 0; i < rows.length; i++) {
    //         if (rows[i].resourceId == parentId)
    //             return true;
    //     }
    //     return false;
    // }
    
    function treeCallback(data) {
        var nodes = treeData(data.object);
        var options = {
            bootstrap2: false,
            showTags: true,
            levels: 5,
            data: nodes
        };
        $("#treeview").treeview(options);
        //节点单击事件
        $("#treeview").on("nodeSelected", function (event, node) {
            var url = node.href;
            if (url == "" || url == null) {
                return;
            }
            $("#rigth", parent.document).load(url);

        });
    }

    function initMenu(userId) {
        treeService = new Service("../resource/initMenu");
        treeService.getById(userId, {success: treeCallback});
    }

    var userId = sessionStorage.getItem("userId");
    if (userId == null) {
        window.location.href = "../admin/login.html";
    }

    initMenu(userId);
});
