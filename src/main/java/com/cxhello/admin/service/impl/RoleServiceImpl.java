package com.cxhello.admin.service.impl;

import com.cxhello.admin.dao.RoleDao;
import com.cxhello.admin.entity.Resource;
import com.cxhello.admin.entity.Role;
import com.cxhello.admin.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @author cxhello
 * @create 2019/11/11 20:43
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public PageInfo<Role> findAllByLike(String searchText, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<Role> roleList = roleDao.findAllByLikeName(searchText);
        PageInfo<Role> page = new PageInfo<>(roleList);
        return page;
    }

    @Override
    public Role selectRoleById(Integer id) {
        Role role = new Role();
        role.setId(id);
        return roleDao.selectByPrimaryKey(role);
    }

    @Override
    public int delete(Integer id) {
        Role role = new Role();
        role.setId(id);
        return roleDao.deleteByPrimaryKey(role);
    }

    @Override
    public int saveOrUpdate(Role role) {
        int count = 0;
        if(role.getId()!=null){
            role.setUpdateTime(new Date());
            count = roleDao.updateByPrimaryKeySelective(role);
        }else{
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());
            count = roleDao.insertSelective(role);
        }
        return count;
    }

    @Override
    public List<Role> findAll() {
        return roleDao.selectAll();
    }

    @Override
    public List<Resource> selectRoleResources(Integer id) {
        return roleDao.selectRoleResources(id);
    }

    @Override
    @Transactional
    public void grant(Integer id, String[] resourceIds) {
        Role role = selectRoleById(id);
        Assert.notNull(role, "角色不存在");
        Assert.state(!"administrator".equals(role.getRoleKey()),"超级管理员角色不能进行资源分配");
        List<Resource> resourceList = selectRoleResources(id);
        if (resourceList != null && resourceList.size() > 0) {
            roleDao.deleteRoleResources(id);
        }
        if (resourceIds != null && resourceIds.length > 0) {
            for (String resourceId : resourceIds) {
                if ("".equals(resourceId) || "0".equals(resourceId)) {
                    continue;
                }
                Integer rid = Integer.parseInt(resourceId);
                roleDao.grant(role.getId(),rid);
            }
        }
    }
}
