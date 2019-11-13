package com.cxhello.admin.service;

import com.cxhello.admin.entity.Resource;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author CaiXiaoHui
 * @create 2019/11/12 20:34
 */
public interface ResourceService {
    PageInfo<Resource> findAllByLike(String searchText, PageInfo pageInfo);

    Resource selectResourceById(Integer id);

    int delete(Integer id);

    int saveOrUpdate(Resource resource);

    List<Resource> findAll();
}
