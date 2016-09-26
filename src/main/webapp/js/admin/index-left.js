var tree = {
    treeCallback: function (data) {
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
    },

    initMenu: function () {
        var userId = sessionStorage.getItem("userId");
        if (userId == null) {
            window.location.href = "/admin/login.html";
            return ;
        }
        var treeService = new Service("/resource/initMenu");
        treeService.getById(userId, {success: this.treeCallback});
    }
};

$(function () {
    tree.initMenu();
});

