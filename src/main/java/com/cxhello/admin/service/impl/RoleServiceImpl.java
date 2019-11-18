package com.cxhello.admin.service.impl;

import com.cxhello.admin.dao.RoleDao;
import com.cxhello.admin.entity.Role;
import com.cxhello.admin.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author CaiXiaoHui
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
}
