package com.amity.authentication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Amity on 2021/1/4 10:18
 */

@RequestMapping("/app")
@RestController
public class AppController {

    @RequestMapping("/hello")
    public String home() {
        return "Hello ,spring security!";
    }
}
