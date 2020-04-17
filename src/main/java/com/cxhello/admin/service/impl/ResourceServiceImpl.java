package com.cxhello.admin.service.impl;

import com.cxhello.admin.dao.ResourceDao;
import com.cxhello.admin.entity.Resource;
import com.cxhello.admin.entity.Role;
import com.cxhello.admin.service.ResourceService;
import com.cxhello.admin.service.RoleService;
import com.cxhello.admin.vo.ZtreeView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author cxhello
 * @create 2019/11/12 20:34
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    @Lazy
    private RoleService roleService;

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
            resource.setUpdateTime(new Date());
            count = resourceDao.updateByPrimaryKeySelective(resource);
        }else{
            resource.setCreateTime(new Date());
            resource.setUpdateTime(new Date());
            count = resourceDao.insertSelective(resource);
        }
        return count;
    }

    @Override
    public List<Resource> findAll() {
        return resourceDao.selectAll();
    }

    @Override
    public List<ZtreeView> getPermissionTreeByRole(Integer roleId) {
        List<Resource> resourceList = resourceDao.selectAll();
        List<Resource> allResourceList = getParentResource(resourceList);
        Role role = roleService.selectRoleById(roleId);
        List<Resource> roleResourceList = roleService.selectRoleResources(roleId);
        List<Resource> allRoleResourceList = getParentResource(roleResourceList);
        if (allRoleResourceList != null && allRoleResourceList.size()>0) {
            role.setResourceList(allRoleResourceList);
        }
        List<ZtreeView> ztreeViewList = new ArrayList<>();
        ztreeViewList.add(0,new ZtreeView(0L, null, "系统菜单", true));
        for (Resource resource : allResourceList) {
            ZtreeView ztreeView = new ZtreeView();
            ztreeView.setId(Long.valueOf(resource.getId()));
            if (resource.getParentResource() == null) {
                ztreeView.setPId(0L);
            } else {
                ztreeView.setPId(Long.valueOf(resource.getParentResource().getId()));
            }
            ztreeView.setName(resource.getName());
            if (allRoleResourceList != null && allRoleResourceList.contains(resource)) {
                ztreeView.setChecked(true);
            }
            ztreeViewList.add(ztreeView);
        }
        return ztreeViewList;
    }

    private List<Resource> getParentResource(List<Resource> resourceList){
        for (Resource resource : resourceList) {
            Resource parentResource = null;
            if(resource.getParentId()!=null){
                parentResource = selectResourceById(resource.getParentId());
            }
            if(parentResource!=null){
                resource.setParentResource(parentResource);
            }
        }
        return resourceList;
    }
}
