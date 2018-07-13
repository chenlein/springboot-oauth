package com.chenlei.client_4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 陈磊
 * @Date: 2018/6/28
 * @Description:
 */

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @RequestMapping("/")
    public String getResource() {
        return "I am client-4!!!";
    }

    @RequestMapping("/web")
    public String checkWebSecurity() {
        return "I am client-4, now check web security!!!";
    }

    @PreAuthorize("#oauth2.hasMatchingScope('read')")
    @RequestMapping("/method")
    public String checkMethodSecurity() {
        return "I am client-4, now check method security!!!";
    }

    @RequestMapping("/cse/web")
    public String checkCustomWebSecurity() {
        return "I am client-4, now check custom web security!!!";
    }

    @PreAuthorize("@cse.permitAll(authentication, 'customSecurityExpression')")
    @RequestMapping("/cse/method")
    public String checkCustomMethodSecurity() {
        return "I am client-4, now check custom method security!!!";
    }

}
