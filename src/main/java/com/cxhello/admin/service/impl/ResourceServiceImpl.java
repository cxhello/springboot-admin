package com.cxhello.admin.service.impl;

import com.cxhello.admin.dao.ResourceDao;
import com.cxhello.admin.entity.Resource;
import com.cxhello.admin.service.ResourceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author CaiXiaoHui
 * @create 2019/11/12 20:34
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public PageInfo<Resource> findAllByLike(String searchText, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<Resource> resourceList = resourceDao.findAllByLikeName(searchText);
        PageInfo<Resource> page = new PageInfo<>(resourceList);
        return page;
    }

    @Override
    public Resource selectResourceById(Integer id) {
        Resource resource = new Resource();
        resource.setId(id);
        return resourceDao.selectByPrimaryKey(resource);
    }

    @Override
    public int delete(Integer id) {
        Resource resource = new Resource();
        resource.setId(id);
        return resourceDao.delete(resource);
    }

    @Override
    public int saveOrUpdate(Resource resource) {
        int count = 0;
        if(resource.getId()!=null){
            count = resourceDao.updateByPrimaryKeySelective(resource);
        }else{
            resource.setCreateTime(new Date());
            resource.setUpdateTime(new Date());
            count = resourceDao.insertSelective(resource);
        }
        return count;
    }
}
