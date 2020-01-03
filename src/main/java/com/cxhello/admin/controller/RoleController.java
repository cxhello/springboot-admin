package com.cxhello.admin.controller;

import com.cxhello.admin.entity.Role;
import com.cxhello.admin.service.RoleService;
import com.cxhello.admin.utils.ResultData;
import com.cxhello.admin.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CaiXiaoHui
 * @create 2019/11/9 22:50
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = { "/", "/index" })
    public String index() {
        return "admin/role/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Role> list(@RequestParam(value = "searchText",required = false) String searchText, PageInfo pageInfo){
        return roleService.findAllByLike(searchText,pageInfo);
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "admin/role/form";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Role role = roleService.selectRoleById(id);
        model.addAttribute("role",role);
        return "admin/role/form";
    }

    @RequestMapping(value = "/edit")
    @ResponseBody
    public ResultData edit(Role role){
        try {
            roleService.saveOrUpdate(role);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultUtils.getMsg("系统异常,请稍后重试!");
        }
        return ResultUtils.getMsg("更新成功");
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResultData delete(@PathVariable("id") Integer id){
        try {
            roleService.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultUtils.getFailResult();
        }
        return ResultUtils.getSuccessResult();
    }
}
