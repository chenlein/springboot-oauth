package com.chenlei.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author: 陈磊
 * @Date: 2018/6/28
 * @Description:
 */

@RestController
public class UserInfoController {

    @RequestMapping("/oauth/me")
    public Principal userInfo (Principal principal) {
        return principal;
    }

}
