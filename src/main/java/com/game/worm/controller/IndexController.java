package com.game.worm.controller;

import com.game.worm.utils.Urls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class IndexController {

    @RequestMapping({Urls.index_blank,Urls.index})
    public String index(HttpServletRequest req) {
        return Urls.index;
    }

    @RequestMapping(value = "unity")
    public String unity(HttpServletResponse response){
        return "unity";
    }

    @RequestMapping(Urls.signup)
    public String signup(){
        return Urls.signup;
    }

    @RequestMapping(Urls.KEY_DRAW)
    public String draw(){
        return Urls.KEY_DRAW;
    }

}