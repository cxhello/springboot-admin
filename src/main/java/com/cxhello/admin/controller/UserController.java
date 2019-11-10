package com.cxhello.admin.controller;

import com.cxhello.admin.entity.User;
import com.cxhello.admin.service.UserService;
import com.cxhello.admin.utils.ResultData;
import com.cxhello.admin.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CaiXiaoHui
 * @create 2019/11/9 22:47
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = { "/admin/user/", "/admin/user/index" })
    public String index() {
        return "admin/user/index";
    }

    @RequestMapping("/admin/user/list")
    @ResponseBody
    public PageInfo<User> list(@RequestParam(value = "searchText",required = false) String searchText,PageInfo pageInfo){
        return userService.findAllByLike(searchText,pageInfo);
    }

    @RequestMapping(value = "/admin/user/add")
    public String add(Model model) {
        return "admin/user/form";
    }

    @RequestMapping(value = "/admin/user/edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        User user = userService.selectUserById(id);
        model.addAttribute("user",user);
        return "admin/user/form";
    }

    @RequestMapping(value = "/admin/user/edit")
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

    @RequestMapping("/admin/user/delete/{id}")
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
}
