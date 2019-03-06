package com.zhst.model.system;

import com.zhst.utils.Tree;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义用户
 */
public class CustomUser implements UserDetails {

    private String username;

    private String password;
    /**
     * 对应权限
     */
    List<GrantedAuthority> authorities = new ArrayList<>();

    /**
     * 菜单
     *
     * @return
     */
    List<Tree<SysMenu>> menus = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<Tree<SysMenu>> getMenus() {
        return menus;
    }

    public void setMenus(List<Tree<SysMenu>> menus) {
        this.menus = menus;
    }
}
