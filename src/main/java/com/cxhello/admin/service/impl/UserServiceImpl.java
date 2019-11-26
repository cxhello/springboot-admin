package com.cxhello.admin.service.impl;

import com.cxhello.admin.dao.UserDao;
import com.cxhello.admin.entity.Role;
import com.cxhello.admin.entity.User;
import com.cxhello.admin.service.UserService;
import com.cxhello.admin.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author CaiXiaoHui
 * @create 2019/11/10 12:55
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<User> findAllByLike(String searchText, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        List<User> userList = userDao.findAllByLikeNickName(searchText);
        PageInfo<User> page = new PageInfo<>(userList);
        return page;
    }

    @Override
    public User selectUserById(Integer id) {
        User user = new User();
        user.setId(id);
        return userDao.selectByPrimaryKey(user);
    }

    @Override
    public int delete(Integer id) {
        User user = new User();
        user.setId(id);
        return userDao.deleteByPrimaryKey(user);
    }

    @Override
    public int saveOrUpdate(User user) {
        int count = 0;
        if(user.getId()!=null){
            user.setUpdateTime(new Date());
            count = userDao.updateByPrimaryKeySelective(user);
        }else{
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setDeleteStatus(0);
            user.setPassword(MD5Utils.md5("111111"));
            count = userDao.insertSelective(user);
        }
        return count;
    }

    @Override
    public User login(String username, String md5Password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(md5Password);
        return userDao.selectOne(user);
    }

    @Override
    public User selectUserByUserName(String userName) {
        User user = new User();
        user.setUserName(userName);
        return userDao.selectOne(user);
    }

    @Override
    public List<Role> selectUserRoles(Integer id) {
        return userDao.selectUserRoles(id);
    }
}
