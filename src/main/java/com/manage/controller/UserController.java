package com.manage.controller;

import com.manage.entity.User;
import com.manage.service.UserService;
import com.manage.util.BaseResult;
import com.manage.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 用户信息
 */

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResult> findUser(@PathVariable int id) {
        User user = userService.findById( id );
        BaseResult result = new BaseResult();
        result.setObject( user );
        return ResponseEntity.ok().body( result );
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(path = "updateUser", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> updateUser(@RequestBody User user) {
        BaseResult result = userService.saveOrUpdate( user );
        return ResponseEntity.ok().body( result );
    }

    /**
     * 用户分页查询
     *
     * @param user
     * @param page
     * @return
     */
    @RequestMapping(path = "/pageUser", method = RequestMethod.GET)
    public ResponseEntity<BaseResult> pageUser(User user, Page page) {
        page = userService.pageQuery( page, user );
        BaseResult baseResult = new BaseResult();
        baseResult.setObject( page );
        return ResponseEntity.ok().body( baseResult );
    }
}
