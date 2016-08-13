package com.manage.controller;

import com.manage.entity.User;
import com.manage.service.UserService;
import com.manage.util.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
     * @param id
     * @return
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BaseResult> findUser(@PathVariable int id) {
        User user = userService.findById(id);
        BaseResult result = new BaseResult();
        result.setObject(user);
        return ResponseEntity.ok().body(result);
    }
}
