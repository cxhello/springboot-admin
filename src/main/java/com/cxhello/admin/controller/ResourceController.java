package com.cxhello.admin.controller;

import com.cxhello.admin.entity.Resource;
import com.cxhello.admin.service.ResourceService;
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

import java.util.List;

/**
 * @author CaiXiaoHui
 * @create 2019/11/9 23:42
 */
@Controller
@RequestMapping("/admin/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = { "/", "/index" })
    public String index() {
        return "admin/resource/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<Resource> list(@RequestParam(value = "searchText",required = false) String searchText, PageInfo pageInfo){
        return resourceService.findAllByLike(searchText,pageInfo);
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "admin/resource/form";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Resource resource = resourceService.selectResourceById(id);
        Resource parentResource = null;
        if(resource.getParentId()!=null){
            parentResource = resourceService.selectResourceById(resource.getParentId());
        }
        if(parentResource!=null){
            resource.setParentResource(parentResource);
        }
        List<Resource> resourceList = resourceService.findAll();
        model.addAttribute("resource",resource);
        model.addAttribute("resourceList",resourceList);
        return "admin/resource/form";
    }

    @RequestMapping(value = "/edit")
    @ResponseBody
    public ResultData edit(Resource resource){
        try {
            resourceService.saveOrUpdate(resource);
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
            resourceService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getFailResult();
        }
        return ResultUtils.getSuccessResult();
    }
}
