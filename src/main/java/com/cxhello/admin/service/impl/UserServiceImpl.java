package com.cxhello.admin.service.impl;

import com.cxhello.admin.dao.UserDao;
import com.cxhello.admin.entity.Role;
import com.cxhello.admin.entity.User;
import com.cxhello.admin.service.UserService;
import com.cxhello.admin.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

    @Override
    public void updatePwd(User user, String oldPassword, String password1, String password2) {
        Assert.notNull(user, "用户不能为空");
        Assert.notNull(oldPassword, "原始密码不能为空");
        Assert.notNull(password1, "新密码不能为空");
        Assert.notNull(password2, "重复密码不能为空");
        User queryUser = new User();
        queryUser.setUserName(user.getUserName());
        User existUser = userDao.selectOne(queryUser);
        Assert.notNull(existUser, "用户不存在");
        Assert.isTrue(user.getPassword().equals(MD5Utils.md5(oldPassword)), "原始密码不正确");
        Assert.isTrue(password1.equals(password2), "两次密码不一致");
        existUser.setPassword(MD5Utils.md5(password2));
        userDao.updateByPrimaryKeySelective(existUser);
    }

    @Override
    @Transactional
    public void grant(Integer id, String[] roleIds) {
        User user = selectUserById(id);
        Assert.notNull(user, "用户不存在");
        Assert.state(!"admin".equals(user.getUserName()),"超级管理员用户不能修改管理角色");
        List<Role> userRoles = selectUserRoles(id);
        if(userRoles != null && userRoles.size() >0){
            userDao.deleteUserRoles(id);
        }
        if(roleIds != null && roleIds.length > 0){
            for (String roleId : roleIds) {
                Integer rid = Integer.parseInt(roleId);
                userDao.grant(user.getId(),rid);
            }
        }
    }
}
