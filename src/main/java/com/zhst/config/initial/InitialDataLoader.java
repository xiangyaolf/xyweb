//package com.zhst.config.initial;
//
//import com.zhst.dao.monitor.produce_report.OrgMappingMapper;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.ServletContextAware;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletContext;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class InitialDataLoader implements InitializingBean, ServletContextAware {
//
//    /*初始化组织映射数据*/
//    public static Map<String, String> orgMappingData = new HashMap<>();
//
//    @Resource
//    private OrgMappingMapper orgMappingMapper;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        List<Map<String, String>> orgMapping = orgMappingMapper.queryMapping();
//        if (orgMapping != null) {
//            for (Map<String, String> item : orgMapping) {
//                String hd_orgcode = item.get("hd_orgcode");
//                String sc_orgcode = item.get("sc_orgcode");
//                orgMappingData.put(sc_orgcode, hd_orgcode);
//            }
//        }
//
//    }
//
//    @Override
//    public void setServletContext(ServletContext servletContext) {
//
//    }
//}
