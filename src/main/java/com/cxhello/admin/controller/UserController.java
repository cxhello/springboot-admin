package com.cxhello.admin.controller;

import com.cxhello.admin.entity.Role;
import com.cxhello.admin.entity.User;
import com.cxhello.admin.service.RoleService;
import com.cxhello.admin.service.UserService;
import com.cxhello.admin.utils.ResultData;
import com.cxhello.admin.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CaiXiaoHui
 * @create 2019/11/9 22:47
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = { "/", "/index" })
    public String index() {
        return "admin/user/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<User> list(@RequestParam(value = "searchText",required = false) String searchText,PageInfo pageInfo){
        return userService.findAllByLike(searchText,pageInfo);
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        return "admin/user/form";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        User user = userService.selectUserById(id);
        model.addAttribute("user",user);
        return "admin/user/form";
    }

    @RequestMapping(value = "/edit")
    @ResponseBody
    public ResultData edit(User user){
        try {
            userService.saveOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getMsg("系统异常,请稍后重试!");
        }
        return ResultUtils.getMsg("更新成功");
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResultData delete(@PathVariable("id") Integer id){
        try {
            userService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getFailResult();
        }
        return ResultUtils.getSuccessResult();
    }

    @RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
    public String grant(@PathVariable Integer id, Model model) {
        User user = userService.selectUserById(id);
        model.addAttribute("user", user);
        //用户拥有的角色
        List<Role> userRoleList = userService.selectUserRoles(id);
        List<Integer> roleIds = new ArrayList<Integer>();
        for (Role role : userRoleList) {
            roleIds.add(role.getId());
        }
        model.addAttribute("roleIds", roleIds);
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList", roleList);
        return "admin/user/grant";
    }

    @RequestMapping(value = "/updatePwd", method = RequestMethod.GET)
    public String updatePwd() {
        return "admin/user/updatePwd";
    }

    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public ResultData updatePwd(String oldPassword, String password1, String password2){
        try {
            Subject subject = SecurityUtils.getSubject();
            Object principal = subject.getPrincipal();
            if(principal== null){
                return ResultUtils.getMsg("您尚未登录");
            }
            userService.updatePwd((User)principal,oldPassword,password1,password2);
            return ResultUtils.getSuccessResult();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getResult(400,e.getMessage());
        }
    }

}
