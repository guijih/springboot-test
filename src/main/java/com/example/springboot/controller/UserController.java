package com.example.springboot.controller;

import com.example.springboot.eneity.User;
import com.example.springboot.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.service.UserService;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 14:59
 */
@RestController
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    @Resource
    private RedisUtils redisUtils;


    @GetMapping("/users")
    public List<User> lists() {


        redisUtils.set("test", "王伟");

        log.info("infor" + "2");

        log.debug("debug" + "2");

        log.warn("warn" + "2");

        log.error("error" + "2");
        return userService.getUsers();
    }

    @GetMapping("/test")
    public String test() {

        log.info("infor" + "1111111111");

        log.debug("debug" + "1111111111");

        log.warn("warn" + "1111111111111111");

        log.error("error" + "1111111111");


        return "test";
    }

}