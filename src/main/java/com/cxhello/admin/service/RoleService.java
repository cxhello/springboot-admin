package com.cxhello.admin.service;

import com.cxhello.admin.entity.Resource;
import com.cxhello.admin.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author CaiXiaoHui
 * @create 2019/11/11 20:43
 */
public interface RoleService {

    PageInfo<Role> findAllByLike(String searchText, PageInfo pageInfo);

    Role selectRoleById(Integer id);

    int delete(Integer id);

    int saveOrUpdate(Role role);

    List<Role> findAll();

    List<Resource> selectRoleResources(Integer id);
}
