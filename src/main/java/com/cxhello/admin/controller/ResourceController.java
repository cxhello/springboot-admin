package com.cxhello.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CaiXiaoHui
 * @create 2019/11/9 23:42
 */
@Controller
public class ResourceController {

    @RequestMapping(value = { "/admin/resource/", "/admin/resource/index" })
    public String index() {
        return "admin/resource/index";
    }

    @RequestMapping(value = "/admin/resource/add")
    public String add() {
        return "admin/resource/form";
    }
}
