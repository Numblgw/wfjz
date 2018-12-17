package com.numb.wfjz.common.config;

import com.numb.wfjz.common.enums.RoleEnum;
import com.numb.wfjz.pojo.User;
import com.numb.wfjz.pojo.UserRole;
import com.numb.wfjz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName ShiroRealm
 * @Description 自定义的Realm实现类
 * @Author Numblgw
 * @Date 2018/12/6 14:07
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 从principalCollection中获取登录信息，得到认证实体
        User user = (User) principalCollection.getPrimaryPrincipal();
        log.info(user.getUsername() + "授权开始");
        // 从得到的认证实体中获取用户的角色权限数据，或者通过查询数据库得到用户相应的角色、权限数据
        UserRole userRole = userService.getUserRoleById(user.getId());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        userRole.getRoleIdList().forEach(roleId -> {
            String role = RoleEnum.findByCode(roleId, RoleEnum.USER).toString();
            authorizationInfo.addRole(role);
            log.info(user.getUsername() + "具有" + role + "角色");
        });
        // 将用户的角色、权限数据封装到 SimpleAuthorizationInfo 中，返回给Shiro
        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.login(token.getUsername());
        if (null == user) {
            throw new UnknownAccountException();
        }
        // 认证的实体信息，可以使用用户名，也可以使用实体对象
        // 认证的证书，也就是认证的密码
        Object credentials = user.getPassword();
        // 盐，使用用户名做盐
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        String realmName = getName();
        return new SimpleAuthenticationInfo(user, credentials, credentialsSalt, realmName);
    }
}
