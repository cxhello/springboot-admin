package com.cxhello.admin.dao;

import com.cxhello.admin.entity.Resource;
import com.cxhello.admin.entity.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author CaiXiaoHui
 * @create 2019/11/11 20:44
 */
public interface RoleDao extends Mapper<Role> {
    List<Role> findAllByLikeName(@Param("searchText") String searchText);

    List<Resource> selectRoleResources(@Param("id")Integer id);
}
