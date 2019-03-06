//package com.zhst.config.security.acl;
//
//import org.springframework.security.access.AccessDecisionVoter;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * 自定义投票器
// */
//@Service
//public class CustomVoter implements AccessDecisionVoter<Object> {
//
//    @Override
//    public boolean supports(ConfigAttribute attribute) {
//        return true;
//    }
//
//    @Override
//    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
//        //用户所拥有的权限
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        if (CollectionUtils.isEmpty(configAttributes)) {
//            //弃权
//            return ACCESS_ABSTAIN;
//        } else {
//            List<String> requre_roles = new ArrayList<>();
//            for (ConfigAttribute configAttribute : configAttributes) {
//                String attribute = configAttribute.getAttribute();
//                requre_roles.add(attribute);
//            }
//            for (GrantedAuthority authority : authorities) {
//                String role = authority.getAuthority();
//
//            }
//        }
//        return 0;
//    }
//
//    @Override
//    public boolean supports(Class clazz) {
//        return true;
//    }
//}
