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

    /**
     * 根据关键字分页查询角色
     * @param searchText
     * @param pageInfo
     * @return
     */
    PageInfo<Role> findAllByLike(String searchText, PageInfo pageInfo);

    /**
     * 根据ID查询角色
     * @param id
     * @return
     */
    Role selectRoleById(Integer id);

    /**
     * 根据ID删除角色
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 增加或者修改角色
     * @param role
     * @return
     */
    int saveOrUpdate(Role role);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();

    /**
     * 查询角色的资源
     * @param id
     * @return
     */
    List<Resource> selectRoleResources(Integer id);

    /**
     * 给角色分配资源
     * @param id
     * @param resourceIds
     */
    void grant(Integer id, String[] resourceIds);
}
