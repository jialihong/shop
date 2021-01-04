package com.amity.authentication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Amity on 2021/1/4 14:53
 */
@RestController
@RequestMapping("/admin")
public class AdminTestController {

    @RequestMapping("/home")
    public String productInfo(){
        return " admin home page ";
    }
}
