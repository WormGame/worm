package com.game.worm.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/unity/Build/build.data.br", "/unity/Build/build.framework.js.br"
        , "/unity/Build/build.wasm.br"})	//Servlet Mapping 자동 설정
public class WasmServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.addHeader("Content-Encoding","br");	// MIME TYPE
        resp.setContentType("application/javascript");
    }
}