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
        return "index";
    }

    @RequestMapping(value = Urls.GAME)
    public String unity(HttpServletResponse response){
        return "unity";
    }

}