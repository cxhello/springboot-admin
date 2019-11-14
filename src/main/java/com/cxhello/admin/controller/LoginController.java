package com.cxhello.admin.controller;

import com.cxhello.admin.entity.User;
import com.cxhello.admin.service.UserService;
import com.cxhello.admin.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author CaiXiaoHui
 * @create 2019/11/14 20:32
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String jumpLogin(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Model model, String username, String password){
        String md5Password = MD5Utils.md5(password);
        User user = userService.login(username,md5Password);
        boolean flag = user==null ? true : false;
        if (flag) {
            model.addAttribute("message","用户不存在或者密码不正确！");
            return "login";
        }
        return "redirect:" + "/index";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "redirect:" + "/login";
    }

}
