package com.cxhello.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CaiXiaoHui
 * @create 2019/11/9 22:14
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
