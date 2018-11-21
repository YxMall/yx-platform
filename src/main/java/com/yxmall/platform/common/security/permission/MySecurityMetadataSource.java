package com.yxmall.platform.common.security.permission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxmall.platform.modules.system.entity.SysMenu;
import com.yxmall.platform.modules.system.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description: 权限资源管理器
 * @author: qing.wang.o
 * @create: 2018-11-04 14:50
 **/
@Slf4j
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysMenuService sysMenuService;

    private HashMap<String, Collection<ConfigAttribute>> configAttributeMap = null;

    /**
     * 加载权限表所有权限
     */
    public void loadResourceDefine() {
        configAttributeMap = new HashMap<>();
        List<SysMenu> menus = sysMenuService.list(new QueryWrapper<SysMenu>().lambda().isNotNull(SysMenu::getPath));
        Collection<ConfigAttribute> configAttributes;
        ConfigAttribute cfg;
        for (SysMenu sysMenu : menus) {
            if (StringUtils.isNotBlank(sysMenu.getPerms()) && StringUtils.isNotBlank(sysMenu.getPath())) {
                configAttributes = new ArrayList<>();
                cfg = new SecurityConfig(sysMenu.getPerms());
                //作为MyAccessDecisionManager第三个参数
                configAttributes.add(cfg);
                //path 作为key
                configAttributeMap.put(sysMenu.getPath(), configAttributes);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (CollectionUtils.isEmpty(configAttributeMap)) {
            loadResourceDefine();
        }
        //object 中包含用户请求的request 信息
        String url = ((FilterInvocation) object).getRequestUrl();
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<String> iterator = configAttributeMap.keySet().iterator();
        while (iterator.hasNext()){
            String resURL = iterator.next();
            //存在restful url
            if (StringUtils.isNotBlank(resURL)&&pathMatcher.match(resURL,url)) {
                return configAttributeMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
