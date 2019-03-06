package com.zhst.config.security;

import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;
import com.zhst.dao.system.UserMapper;
import com.zhst.model.system.CustomUser;
import com.zhst.model.system.SysMenu;
import com.zhst.utils.BuildTree;
import com.zhst.utils.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义UserDetail
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map<String, String> userInfo = userMapper.queryUserByUsername(s);
        CustomUser user = new CustomUser();
        if (userInfo != null) {
            user.setUsername(userInfo.get("username"));
            user.setPassword(userInfo.get("pwd"));
        }
        //加载GrantedAuthority
        List<String> authoritys = userMapper.loadAuthorityByUsername(s);
        if (!CollectionUtils.isEmpty(authoritys)) {
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            for (String authority : authoritys) {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
                grantedAuthorityList.add(simpleGrantedAuthority);
            }
            user.setAuthorities(grantedAuthorityList);
        }
        //加载菜单等
        List<SysMenu> sysMenus = userMapper.loadMenueByUsername(s);
        List<Tree<SysMenu>> trees = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysMenus)) {
            for (SysMenu sysMenu : sysMenus) {
                Tree<SysMenu> tree = new Tree<>();
                tree.setNode(sysMenu);
                tree.setId(sysMenu.getId());
                tree.setParentId(sysMenu.getParentId());
                tree.setText(sysMenu.getName());
                tree.setParentText(sysMenu.getParentName());
                tree.setIcon(sysMenu.getIcon());
                tree.setUrl(sysMenu.getUrl());
                trees.add(tree);
            }
            List<Tree<SysMenu>> build = BuildTree.build(trees);
            if (!CollectionUtils.isEmpty(build)) {
                List<Tree<SysMenu>> filterMenuTree = getMenuTrees(build);
                user.setMenus(filterMenuTree);
            }
        }
        return user;
    }

    private List<Tree<SysMenu>> getMenuTrees(List<Tree<SysMenu>> build) {
        return build.stream().filter(sysMenuTree -> {
            Integer parentId = sysMenuTree.getParentId();
            if (parentId == null || parentId == 0) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
