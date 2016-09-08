package com.test.service;

import com.manage.entity.User;
import com.manage.service.UserService;
import com.manage.vo.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 类名：UserServiceTest <br/>
 * 功能说明： <br/>
 * 修改历史： <br/>
 * 1.[2016/9/6  11:08]创建类 by pxh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void pageQueryUser() {
        Page<User> page = new Page<>();
        page.setPageSize( "1" );
        page.setCurrentPage( "2" );
        User user = new User();
        page = userService.pageQuery( page, user );
        Assert.assertNotNull( page );
//        System.out.println( JsonUtil.INSTANCE.objectToJson( page ) );
    }
}
