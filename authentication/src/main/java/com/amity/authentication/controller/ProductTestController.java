package com.amity.authentication.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Amity on 2021/1/4 14:52
 */
@RestController
@RequestMapping("/product")
public class ProductTestController {

    @RequestMapping("/info")
    public String productInfo(){
        return " some product info ";
    }

    @GetMapping("getCurrentUser")
    public String getCurrentUser() {

        String currentUser = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails) {
            currentUser = ((UserDetails) principal).getUsername();
        }else {
            currentUser = principal.toString();
        }

        return "current user is: " + currentUser;
    }
}
