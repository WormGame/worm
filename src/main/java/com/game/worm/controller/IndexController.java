package com.game.worm.controller;

import com.game.worm.etc.config.UDPServerRunner;
import com.game.worm.etc.define.Urls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    private UDPServerRunner udpServerRunner;
    @RequestMapping({Urls.index_blank,Urls.index})
    public String index(HttpServletRequest req) {
        udpServerRunner.run();
        return "index";
    }

    @RequestMapping(value = Urls.GAME)
    public String unity(HttpServletResponse response){
        return "unity";
    }

    @PostMapping("/fileUpload")
    public List<String> upload(@RequestPart("file") List<MultipartFile> files) throws Exception {
        List<String> list = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalfileName = file.getOriginalFilename();
            File dest = new File("C:/temp/" + originalfileName);
            file.transferTo(dest);
        }
        return list;
    }
}