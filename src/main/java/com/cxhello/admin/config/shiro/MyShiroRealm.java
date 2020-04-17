package com.cxhello.admin.config.shiro;

import com.cxhello.admin.entity.Resource;
import com.cxhello.admin.entity.Role;
import com.cxhello.admin.entity.User;
import com.cxhello.admin.service.RoleService;
import com.cxhello.admin.service.UserService;
import com.cxhello.admin.utils.MD5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author cxhello
 * @create 2019/11/18 15:14
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User queryUser = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = userService.selectUserByUserName(queryUser.getUserName());
        Set<String> shiroPermissions = new HashSet<>();
        Set<String> roleSet = new HashSet<String>();
        List<Role> userRoles = userService.selectUserRoles(user.getId());
        if(userRoles!=null && userRoles.size()>0){
            for (Role role : userRoles) {
                List<Resource> roleResources = roleService.selectRoleResources(role.getId());
                if(roleResources != null && roleResources.size()>0){
                    for (Resource resource : roleResources) {
                        shiroPermissions.add(resource.getSourceKey());
                    }

                }
                roleSet.add(role.getRoleKey());
            }
        }
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(shiroPermissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        User user = userService.selectUserByUserName(username);

        String password = new String((char[]) authenticationToken.getCredentials());

        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号不存在");
        }
        // 密码错误
        if (!MD5Utils.md5(password).equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        // 账号锁定
        if (user.getLocked() == 1) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());

        return info;
    }
}
