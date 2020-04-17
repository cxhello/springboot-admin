package com.cxhello.admin.dao;

import com.cxhello.admin.entity.Resource;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author cxhello
 * @create 2019/11/12 20:32
 */
public interface ResourceDao extends Mapper<Resource> {
    List<Resource> findAllByLikeName(@Param("searchText") String searchText);
}
