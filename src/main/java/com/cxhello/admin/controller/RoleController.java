package com.cxhello.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CaiXiaoHui
 * @create 2019/11/9 22:50
 */
@Controller
public class RoleController {

    @RequestMapping(value = { "/admin/role/", "/admin/role/index" })
    public String index() {
        return "admin/role/index";
    }

    @RequestMapping(value = "/admin/role/add")
    public String add() {
        return "admin/role/form";
    }
}
