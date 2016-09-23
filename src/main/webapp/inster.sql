
INSERT INTO `resource` (`resourceId`, `name`, `parentId`, `permission`, `priority`, `state`, `type`, `url`) VALUES
	(1, '系统菜单', 0, NULL, NULL, 0, 1, NULL),
	(2, '用户管理', 1, NULL, NULL, 0, 1, '../admin/user/userList.html');


INSERT INTO `user` (`userId`, `passWord`, `salt`, `state`, `userName`) VALUES
	(1, '000000', '33333333', 0, '彭晓辉'),
	(3, '000000', '222', 0, 'pxh');
