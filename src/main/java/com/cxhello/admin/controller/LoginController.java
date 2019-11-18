package com.cxhello.admin.controller;

import com.cxhello.admin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

    /*@RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Model model, String username, String password){
        String md5Password = MD5Utils.md5(password);
        User user = userService.login(username,md5Password);
        boolean flag = user==null ? true : false;
        if (flag) {
            model.addAttribute("message","用户不存在或者密码不正确！");
            return "login";
        }
        return "redirect:" + "/index";
    }*/

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Model model, String username, String password){
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            return "redirect:" + "/index";
        } catch (AuthenticationException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "redirect:" + "/login";
    }

}
