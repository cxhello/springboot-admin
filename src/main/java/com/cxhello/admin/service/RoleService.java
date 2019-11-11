package com.cxhello.admin.service;

import com.cxhello.admin.entity.Role;
import com.github.pagehelper.PageInfo;

/**
 * @author CaiXiaoHui
 * @create 2019/11/11 20:43
 */
public interface RoleService {

    PageInfo<Role> findAllByLike(String searchText, PageInfo pageInfo);

    Role selectRoleById(Integer id);

    int delete(Integer id);

    int saveOrUpdate(Role role);
}
