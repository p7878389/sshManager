function treeData(rows) {
    var nodes = [];
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        if (!exists(rows, row.parentId)) {
            nodes.push({
                id: row.resourceId,
                text: row.name
            });
        }
    }

    var toDo = [];
    for (var i = 0; i < nodes.length; i++) {
        toDo.push(nodes[i]);
    }
    while (toDo.length) {
        var node = toDo.shift();
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            if (row.parentId == node.id) {
                var child = {
                    id: row.resourceId,
                    href: row.url,
                    text: row.name
                };
                if (node.nodes) {
                    node.nodes.push(child);
                } else {
                    node.nodes = [child];
                }
                toDo.push(child);
            }
        }
    }
    return nodes;
}
function exists(rows, parentId) {
    for (var i = 0; i < rows.length; i++) {
        if (rows[i].resourceId == parentId)
            return true;
    }
    return false;
}