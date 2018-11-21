package com.yxmall.platform.common.security.permission;

import com.yxmall.platform.common.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 权限认证
 * @link https://blog.csdn.net/u012373815/article/details/54633046/
 * @author: qing.wang.o
 * @create: 2018-11-04 14:30
 **/
@Component
@Slf4j
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * decide 方法是判定是否拥有权限的决策方法，
     *
     * @param authentication   UserDetailsServiceImpl 循环添加到 GrantedAuthority 对象中的权限信息集合.
     * @param object           包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     * @param configAttributes 为MySecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
     *                         此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，
     *                         用来判定用户是否有此权限。如果不在权限表中则放行。
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtils.isEmpty(configAttributes)){
            return;
        }
        //用户拥有权限集合
        List<String> authorityList = authentication.getAuthorities().stream().map(ga -> ((GrantedAuthority) ga).getAuthority()).collect(Collectors.toList());
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()){
            ConfigAttribute configAttribute = iterator.next();
            //需要的权限
            String needPerm = configAttribute.getAttribute();
            if (authorityList.contains(needPerm.trim())){
                return;
            }
        }
        throw  new AccessDeniedException("很遗憾，你没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
