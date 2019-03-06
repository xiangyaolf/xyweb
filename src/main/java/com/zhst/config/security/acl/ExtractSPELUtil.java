//package com.zhst.config.security.acl;
//
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.util.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
///**
// * 抽取Spel表达式里面的权限信息
// */
//public class ExtractSPELUtil {
//
//    private static final String permitAll = "permitAll";
//    private static final String denyAll = "denyAll";
//    private static final String anonymous = "anonymous";
//    private static final String anonymous_attr = "ROLE_ANONYMOUS";
//    private static final String authenticated = "authenticated";
//    private static final String fullyAuthenticated = "fullyAuthenticated";
//    private static final String rememberMe = "rememberMe";
//
//
//    public static List<SecurityConfig> authorityInfo(Collection<ConfigAttribute> configAttributes) {
//        if (CollectionUtils.isEmpty(configAttributes)) {
//            return Collections.emptyList();
//        }
//        List<SecurityConfig> wrapperConfigAttr = new ArrayList<>();
//        for (ConfigAttribute configAttribute : configAttributes) {
//            String authorityString = configAttribute.toString();
//            //匿名访问--默认是开启了ROLE_ANONYMOUS--会存放在Authentication里面
//            if (anonymous.equals(authorityString)) {
//                SecurityConfig securityConfig = new SecurityConfig(anonymous_attr);
//                wrapperConfigAttr.add(securityConfig);
//            } else if (permitAll.equals(authorityString)) {
//                //permitAll
//                SecurityConfig securityConfig = new SecurityConfig(anonymous_attr);
//                wrapperConfigAttr.add(securityConfig);
//            } else if (authenticated.equals(configAttribute)) {
//                // authenticated
//                SecurityConfig securityConfig = new SecurityConfig(authenticated);
//                wrapperConfigAttr.add(securityConfig);
//            } else {
//                String authorityTrim = authorityString.substring(authorityString.indexOf("("), authorityString.lastIndexOf(")"));
//                String[] authorityArr = authorityTrim.split(",");
//                for (int i = 0; i < authorityArr.length; i++) {
//                    String authority = authorityArr[i];
//                    SecurityConfig securityConfig = new SecurityConfig(authority);
//                    wrapperConfigAttr.add(securityConfig);
//                }
//            }
//        }
//        return wrapperConfigAttr;
//    }
//}
