/**
 * Created by Administrator on 2016/9/2.
 */
function dialogData(title, massage,callBack, type) {
    if (typeof(type) == 'undefined') {
        BootstrapDialog.show({
            title: title,
            message: function () {
                return $(massage);
            },
            buttons: [{
                label: '保存',
                icon: 'glyphicon glyphicon-check',
                cssClass: 'btn-success',
                action: function (dialog) {
                    callBack();
                    dialog.close();
                }
            }, {
                label: '关闭',
                icon: 'glyphicon glyphicon-remove',
                cssClass: 'btn-warning',
                action: function (dialog) {
                    dialog.close();
                }
            }]
        });
    } else {
        BootstrapDialog.show({
            title: title,
            type: type,
            message: function () {
                return $(massage);
            },
            buttons: [{
                label: '保存',
                icon: 'glyphicon glyphicon-check',
                cssClass: 'btn-success',
                action: function () {
                }
            }, {
                label: '关闭',
                icon: 'glyphicon glyphicon-remove',
                cssClass: 'btn-warning',
                action: function (dialog) {
                    dialog.close();
                }
            }]
        });
    }

}
function dialogMsg(msg) {
    BootstrapDialog.show({
        message: msg
    });

}
