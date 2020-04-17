package com.cxhello.admin.service;

import com.cxhello.admin.entity.Resource;
import com.cxhello.admin.vo.ZtreeView;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author cxhello
 * @create 2019/11/12 20:34
 */
public interface ResourceService {

    /**
     * 根据关键字分页查询资源
     * @param searchText
     * @param pageInfo
     * @return
     */
    PageInfo<Resource> findAllByLike(String searchText, PageInfo pageInfo);

    /**
     * 根据ID查询资源
     * @param id
     * @return
     */
    Resource selectResourceById(Integer id);

    /**
     * 根据ID删除资源
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 增加或者修改资源
     * @param resource
     * @return
     */
    int saveOrUpdate(Resource resource);

    /**
     * 查询所有资源
     * @return
     */
    List<Resource> findAll();

    /**
     * 获取角色的权限树
     * @param roleId
     * @return
     */
    List<ZtreeView> getPermissionTreeByRole(Integer roleId);
}
