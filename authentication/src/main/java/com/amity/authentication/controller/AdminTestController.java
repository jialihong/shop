package com.amity.authentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Amity on 2021/1/4 14:53
 */
@RestController
@RequestMapping("/admin")
public class AdminTestController {

//    @PreAuthorize("hasRole('admin')")
    @RequestMapping("/home")
    public String productInfo(){
        return " admin home page ";
    }
}
