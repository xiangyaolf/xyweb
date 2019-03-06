package com.zhst.config.freemaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 加载classpath下面的TLD
 */
public class ClassPathTldsLoader {

    //freemaker的配置类
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;


    private static List<String> classPathTlds = new ArrayList<>();

    static {
        //security tld
        classPathTlds.add("/taglib/security.tld");
    }

    public ClassPathTldsLoader() {

    }

    @PostConstruct
    public void loadClassPathTlds() {
        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(classPathTlds);
    }
}
