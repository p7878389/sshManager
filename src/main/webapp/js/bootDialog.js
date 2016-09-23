function bootDialog(title, message, type) {
    this.title = title;
    this.message = message;
    this.type = type;
}
bootDialog.prototype = {
    constructor: bootDialog,

    dialogData: function (callBack) {
        if (typeof(this.type) == 'undefined') {
            BootstrapDialog.show({
                title: this.title,
                message: this.message,
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
                title: this.title,
                type: this.type,
                message: function () {
                    return $(this.message);
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
        }
    },
    htmlElementDialog: function (callBack) {
        return dialogObj = BootstrapDialog.show({
            title: this.title,
            message: this.message,
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
    },

    loadHtmlDialog: function () {
        return dialogObj = BootstrapDialog.show({
            title: this.title,
            message: $('<div></div>').load(this.message)
        });
    },

    dialogMsg: function () {
        BootstrapDialog.show({
            title: this.title,
            message: this.message
        });
    }

};
/*function dialogData(title, massage, callBack, type) {
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

 function eleMentDialog(title, message) {
 return dialogObj = BootstrapDialog.show({
 title: title,
 message: function () {
 return $(message);
 }
 });
 }

 function loadHtmlDialog(title, html) {
 return dialogObj = BootstrapDialog.show({
 title: title,
 message: $('<div></div>').load(html)
 });
 }

 function dialogMsg(title, msg) {
 BootstrapDialog.show({
 title: title,
 message: msg
 });
 }*/

