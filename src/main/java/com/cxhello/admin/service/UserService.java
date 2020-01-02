package com.cxhello.admin.service;

import com.cxhello.admin.entity.Role;
import com.cxhello.admin.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author CaiXiaoHui
 * @create 2019/11/10 12:55
 */
public interface UserService {

    /**
     * 根据关键字分页查询
     * @param searchText
     * @param pageInfo
     * @return
     */
    PageInfo<User> findAllByLike(String searchText,PageInfo pageInfo);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User selectUserById(Integer id);

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 增加或者修改用户
     * @param user
     * @return
     */
    int saveOrUpdate(User user);

    /**
     * 登录
     * @param username
     * @param md5Password
     * @return
     */
    User login(String username, String md5Password);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User selectUserByUserName(String userName);

    /**
     * 查询用户的角色
     * @param id
     * @return
     */
    List<Role> selectUserRoles(Integer id);

    /**
     * 修改用户密码
     * @param user
     * @param oldPassword
     * @param password1
     * @param password2
     */
    void updatePwd(User user, String oldPassword, String password1, String password2);

    /**
     * 给用户分配角色
     * @param id
     * @param roleIds
     */
    void grant(Integer id, String[] roleIds);
}
