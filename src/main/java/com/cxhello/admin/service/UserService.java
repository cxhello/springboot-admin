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

    PageInfo<User> findAllByLike(String searchText,PageInfo pageInfo);

    User selectUserById(Integer id);

    int delete(Integer id);

    int saveOrUpdate(User user);

    User login(String username, String md5Password);

    User selectUserByUserName(String userName);

    List<Role> selectUserRoles(Integer id);

    void updatePwd(User user, String oldPassword, String password1, String password2);
}
