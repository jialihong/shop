package com.amity.account.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Amity on 2020/12/24 17:01
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public void demo() {
        System.out.println("demodemodemodemodemo");
    }
}
