//package com.zhst.config.security.acl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.AccessDecisionVoter;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.access.vote.AbstractAccessDecisionManager;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.List;
//
//import static org.springframework.security.access.AccessDecisionVoter.ACCESS_GRANTED;
//
///**
// * 自定义 AccessDecisionManager
// */
//@Service
//public class CustomAccessDecisionManager extends AbstractAccessDecisionManager {
//
//    @Autowired
//    public CustomAccessDecisionManager(List<AccessDecisionVoter<?>> decisionVoters) {
//        super(decisionVoters);
//    }
//
//    @Override
//    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
//        int grant = 0;
//        List<SecurityConfig> securityConfigs = ExtractSPELUtil.authorityInfo(configAttributes);
//        for (AccessDecisionVoter voter : getDecisionVoters()) {
//            int result = voter.vote(authentication, object, securityConfigs);
//            if (logger.isDebugEnabled()) {
//                logger.debug("Voter: " + voter + ", returned: " + result);
//            }
//            if (result == ACCESS_GRANTED) {
//                grant++;
//            }
//        }
//        // 只要有一票通过就表示可以访问
//        if (grant > 0) {
//            return;
//        } else {
//            //没有任何满足要求的权限
//            throw new AccessDeniedException(messages.getMessage(
//                    "AbstractAccessDecisionManager.accessDenied", "Access is denied"));
//        }
//    }
//}
